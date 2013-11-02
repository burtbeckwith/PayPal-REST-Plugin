package com.trinary.paypal

import com.trinary.paypal.rest.*
import com.trinary.paypal.error.*
import com.trinary.paypal.error.exception.PayPalException
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class PaymentService {
	def grailsLinkGenerator
	
    PaymentResponse payWithCreditCard(Orderable order, CreditCard creditCard) throws PayPalException {
		Double taxRate = order.getTaxRate()
		
		CreditCardPayer payer = new CreditCardPayer()
		payer.addFundingInstrument(creditCard)

		PaymentRequest paymentRequest = new PaymentRequest([
				intent: Intent.SALE,
				payer: payer
		])
		
		Transaction transaction = new Transaction([
			amount: new Amount([
				currency: order.currency
			]),
			taxRate: order.taxRate
		])
		
		order.items.each { Payable payable ->
			Double price = payable.price
			Integer quantity = payable.quantity
			String description = payable.description
			Currency currency = order.currency
			
			transaction.addItem(new Item([
				name: description,
				price: price,
				currency: currency,
				quantity: quantity
			]))
		}
		
		paymentRequest.addTransaction(transaction)

		PaymentResponse paymentResponse = paymentRequest.pay()
		
		if (paymentResponse && paymentResponse.state == "approved") {
			order.paymentId = paymentResponse.id
		} else if (paymentResponse && paymentResponse.state != "approved") {
			throw new PayPalException("Payment was declined!")
		}
		
		return paymentResponse
    }	
	
	PaymentResponse payWithPayPal(Orderable order, String controller, String completeAction, String cancelAction) throws PayPalException {
		order.transactionId = UUID.randomUUID()
		
		String returnUrl = grailsLinkGenerator.link(absolute: true, controller: controller, action: completeAction, params: [transaction: order.transactionId])
		String cancelUrl = grailsLinkGenerator.link(absolute: true, controller: controller, action: cancelAction)
		
		PaymentRequest paymentRequest = new PaymentRequest([
			intent: Intent.SALE,
			payer: new PayPalPayer(),
			redirectUrls: new RedirectUrls([
				returnUrl: returnUrl,
				cancelUrl: cancelUrl
			])
		])
		
		Transaction transaction = new Transaction([
			amount: new Amount([
				currency: order.currency
			]),
			taxRate: order.taxRate
		])
		
		order.items.each { Payable payable ->
			Double price = payable.price
			Integer quantity = payable.quantity
			String description = payable.description
			Currency currency = order.currency
			
			transaction.addItem(new Item([
				name: description,
				price: price,
				currency: currency,
				quantity: quantity
			]))
		}
		
		paymentRequest.addTransaction(transaction)

		PaymentResponse paymentResponse = paymentRequest.pay()
		
		if (paymentResponse && paymentResponse.state == "created") {
			order.paymentId = paymentResponse.id
			PayPalTransactionStore.storeTransaction(order)
		} else if (paymentResponse && paymentResponse.state != "created") {
			throw new PayPalException("Failed to create payment!")
		}
		
		return paymentResponse
	}
	
	PaymentResponse executePayPalPayment(String transactionId, String payerId) throws PayPalException {
		Orderable orderable = PayPalTransactionStore.getTransaction(transactionId)
		
		if (!orderable) {
			throw new PayPalException("Unable to find a transaction with id ${transactionId}")
		}
		
		PaymentRequest paymentRequest = new PaymentRequest()
		PaymentResponse paymentResponse = paymentRequest.execute(orderable.paymentId, payerId)

		return paymentResponse
	}
}
