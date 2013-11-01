package com.trinary.paypal.test

import com.trinary.paypal.*
import com.trinary.paypal.error.*
import com.trinary.paypal.rest.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class TestService {
	PaymentService paymentService

    def test() {
        PayPalConfig.setClientId("ASH4ehCXBQFejoWM4-VhA1m3OveRc6Gx0Wf2fRJSTw_kc70s5CDccdnyjBaU")
		PayPalConfig.setSecret("EE13ZxA47-hbtRI7nuzdaXy407gUJtbSyCCBM4LYnJdlbFFkwW1XSPxBTUve")
		PayPalConfig.enableSandbox()
		
		Order order = new Order(
			currency: Currency.USD,
			taxRate: 0.0825
		)
		
		order.items.add(new OrderItem(
			currency: Currency.USD,
			taxRate: 0.0825,
			price: 25.00,
			description: "Silk Pink Panties",
			quantity: 2
		))
		
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
		
		try {
			paymentService.payWithCreditCard(order, creditCard)
		} catch (PayPalException e) {
			log.error("Payment failed!", e)
		} catch (PayPalPaymentDeclinedException e) {
			log.error("Payment declined!", e)
		}
		
		try {
			paymentService.payWithPayPal(order, "http://localhost/completePayPal", "http://localhost/cancelPayPal")
		} catch (PayPalException e) {
			log.error("Payment failed!", e)
		}
    }
}
