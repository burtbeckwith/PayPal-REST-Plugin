package com.trinary.paypal.test

import com.trinary.paypal.PayPalConfig
import com.trinary.paypal.PaymentService
import com.trinary.paypal.error.exception.PayPalException
import com.trinary.paypal.payment.CreditCard
import com.trinary.paypal.payment.CreditCardType
import com.trinary.paypal.payment.Currency
import com.trinary.paypal.payment.payer.BillingAddress

class TestService {

	static transactional = false

	PaymentService paymentService

	def testPayPal() {
		// Configure the PayPal plugin.  This can also be done in the Grails config.
		PayPalConfig.setClientId("ASH4ehCXBQFejoWM4-VhA1m3OveRc6Gx0Wf2fRJSTw_kc70s5CDccdnyjBaU")
		PayPalConfig.setSecret("EE13ZxA47-hbtRI7nuzdaXy407gUJtbSyCCBM4LYnJdlbFFkwW1XSPxBTUve")
		PayPalConfig.enableSandbox()

		// Create an object that uses the Payable interface.
		Order order = new Order(
			currency: Currency.USD,
			taxRate: 0.0825
		)

		// Add items with price, description, quantity.
		order.items.add(new OrderItem(
			price: 25.00,
			description: "Kaito Action Figure",
			quantity: 2
		))

		// To pay with PayPal, pass the order and two urls.
		try {
			return paymentService.payWithPayPal(order, "test", "completePayPal", "cancelPayPal")
		} catch (PayPalException e) {
			log.error("Payment failed!", e)
		} catch (Exception e) {
			log.error("Unexpected exception", e)
		}
	}

	def testCredit() {
		// Configure the PayPal plugin.  This can also be done in the Grails config.
		PayPalConfig.setClientId("ASH4ehCXBQFejoWM4-VhA1m3OveRc6Gx0Wf2fRJSTw_kc70s5CDccdnyjBaU")
		PayPalConfig.setSecret("EE13ZxA47-hbtRI7nuzdaXy407gUJtbSyCCBM4LYnJdlbFFkwW1XSPxBTUve")
		PayPalConfig.enableSandbox()

		// Create an object that uses the Payable interface.
		Order order = new Order(
			currency: Currency.USD,
			taxRate: 0.0825
		)

		// Add items with price, description, quantity.
		order.items.add(new OrderItem(
			price: 25.00,
			description: "Kaito Action Figure",
			quantity: 2
		))

		// Create a credit card object.
		CreditCard creditCard = new CreditCard(
			firstName: "Hatsune",
			lastName: "Miku",
			number: "4417119669820331",
			expireMonth: 11,
			expireYear: 2018,
			type: CreditCardType.VISA,
			cvv2: "874",
			billingAddress: new BillingAddress(
				line1: "1 Vocaloid St",
				city: "San Antonio",
				state: "TX",
				postalCode: "78224",
				countryCode: "US",
				phone: "210-445-5422"
			)
		)

		// To pay with a credit card, pass the order and the credit card object.
		try {
			return paymentService.payWithCreditCard(order, creditCard)
		} catch (PayPalException e) {
			log.error("Payment failed!", e)
		} catch (Exception e) {
			log.error("Unexpected exception", e)
		}
	}
}
