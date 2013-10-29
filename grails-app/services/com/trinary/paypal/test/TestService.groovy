package com.trinary.paypal.test

import com.trinary.paypal.*
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class TestService {

    def test() {
        PayPalConfig.setClientId("ASH4ehCXBQFejoWM4-VhA1m3OveRc6Gx0Wf2fRJSTw_kc70s5CDccdnyjBaU")
		PayPalConfig.setSecret("EE13ZxA47-hbtRI7nuzdaXy407gUJtbSyCCBM4LYnJdlbFFkwW1XSPxBTUve")
		PayPalConfig.enableSandbox()

        CreditCardPayer payer = new CreditCardPayer()
        CreditCard creditCard = new CreditCard([
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

        PaymentRequest payment = new PaymentRequest([
            intent: Intent.SALE,
            payer: payer
        ])
        payment.addTransaction(new Transaction([
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

        PaymentResponse paymentResponse = payment.pay()

        // Test PayPal Payment
        payment = new PaymentRequest([
            intent: Intent.SALE,
            payer: new PayPalPayer(),
            redirectUrls: new RedirectUrls([
                returnUrl: "http://localhost/return",
                cancelUrl: "http://localhost/cancel"
            ])
        ])
        payment.addTransaction(new Transaction([
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

        paymentResponse = payment.pay()
    }
}
