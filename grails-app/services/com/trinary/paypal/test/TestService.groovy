package com.trinary.paypal.test

import com.trinary.paypal.*
import com.trinary.paypal.error.*
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*
import com.trinary.paypal.rest.PaymentRequest;
import com.trinary.paypal.rest.PaymentResponse;

class TestService {

    def test() {
        PayPalConfig.setClientId("ASH4ehCXBQFejoWM4-VhA1m3OveRc6Gx0Wf2fRJSTw_kc70s5CDccdnyjBaU")
		PayPalConfig.setSecret("EE13ZxA47-hbtRI7nuzdaXy407gUJtbSyCCBM4LYnJdlbFFkwW1XSPxBTUve")
		PayPalConfig.enableSandbox()
		
		// Test a failure
		CreditCardPayer payer = new CreditCardPayer()
		CreditCard creditCard = new CreditCard([
			number: "1234567890",
			type: CreditCardType.VISA,
			expireMonth: 1111,
			expireYear: 18,
			cvv2: "874",
			firstName: "Joe",
			lastName: "Shopper",
			billingAddress: new BillingAddress([
				line1: "52 N Main St",
				city: "Johnstown",
				countryCode: "US",
				postalCode: "43210",
				state: "OH"
			])
		])

		payer.addFundingInstrument(creditCard)

		PaymentRequest paymentRequest = new PaymentRequest([
			intent: Intent.SALE,
			payer: payer
		])
		paymentRequest.addTransaction(new Transaction([
			amount: new Amount([
				currency: Currency.USD,
				details: new Details([
					subtotal: 7.41,
					tax: 0.03,
					shipping: 0.03
				])
			]),
			description: "Buying a fart gun"
		]))

		PaymentResponse paymentResponse = paymentRequest.pay()
		
		if (!paymentResponse) {
			log.error("An error occurred!")
			log.error(paymentRequest.errors.name)
			paymentRequest.errors.details.each { PayPalErrorDetail detail ->
				log.error(detail)
			}
		}

		// Test valid credit card
        payer = new CreditCardPayer()
        creditCard = new CreditCard([
            number: "4417119669820331",
            type: CreditCardType.VISA,
            expireMonth: 11,
            expireYear: 2018,
            cvv2: "874",
            firstName: "Joe",
            lastName: "Shopper",
            billingAddress: new BillingAddress([
                line1: "52 N Main St",
                city: "Johnstown",
                countryCode: "US",
                postalCode: "43210",
                state: "OH"
            ])
        ])

        payer.addFundingInstrument(creditCard)

        paymentRequest = new PaymentRequest([
            intent: Intent.SALE,
            payer: payer
        ])
        paymentRequest.addTransaction(new Transaction([
            amount: new Amount([
                currency: Currency.USD,
                details: new Details([
                    subtotal: 7.41,
                    tax: 0.03,
                    shipping: 0.03
                ])
            ]),
            description: "Buying a fart gun"
        ]))

        paymentResponse = paymentRequest.pay()
		
		if (!paymentResponse) {
			log.error("An error occurred!")
			log.error(paymentRequest.errors.name)
			paymentRequest.errors.details.each { PayPalErrorDetail detail ->
				log.error(detail)
			}
		}

        // Test PayPal Payment
        paymentRequest = new PaymentRequest([
            intent: Intent.SALE,
            payer: new PayPalPayer(),
            redirectUrls: new RedirectUrls([
                returnUrl: "http://localhost/return",
                cancelUrl: "http://localhost/cancel"
            ])
        ])
        paymentRequest.addTransaction(new Transaction([
            amount: new Amount([
                currency: Currency.USD,
                details: new Details([
                    subtotal: 7.41,
                    tax: 0.03,
                    shipping: 0.03
                ])
            ]),
            description: "Buying a fart gun"
        ]))

        paymentResponse = paymentRequest.pay()
		
		if (!paymentResponse) {
			log.error("An error occurred!")
			log.error(paymentRequest.errors.name)
			paymentRequest.errors.details.each { PayPalErrorDetail detail ->
				log.error(detail)
			}
		}
    }
}
