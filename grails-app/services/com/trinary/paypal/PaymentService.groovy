package com.trinary.paypal

import com.trinary.paypal.rest.*
import com.trinary.paypal.error.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class PaymentService {
    PaymentResponse payWithCreditCard(Payable order, CreditCard creditCard) throws PayPalException, PayPalPaymentDeclinedException {
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
			throw new PayPalPaymentDeclinedException("Payment was declined!")
		} else {
			throw new PayPalException("Pay by credit card failed!", paymentRequest.errors)
		}
		
		return paymentResponse
    }
	
	PaymentResponse payWithPayPal(Payable order, String returnUrl, String cancelUrl) throws PayPalException {
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
			Currency currency = payable.currency
			
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
		} else if (paymentResponse && paymentResponse.state != "created") {
			throw new PayPalException("Failed to create payment!", null)
		} else {
			throw new PayPalException("Pay by PayPal failed!", paymentRequest.errors)
		}
		
		return paymentResponse
	}
	
	PaymentResponse executePayPalPayment(Payable order, String payerId) throws PayPalException {
		PaymentRequest paymentRequest = new PaymentRequest()
		PaymentResponse paymentResponse = paymentRequest.execute(order.paymentId, payerId)
		
		if (!paymentResponse) {
			throw new PayPalException("Execute PayPal payment failed!", paymentRequest.errors)
		}

		return paymentResponse
	}
}
