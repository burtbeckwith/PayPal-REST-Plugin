package com.trinary.paypal.test

import com.trinary.paypal.PaymentService
import com.trinary.paypal.rest.PaymentResponse

class TestController {
    TestService testService
    PaymentService paymentService

    def testCredit() {
        [paymentResponse: testService.testCredit()]
    }

    def testPayPal() {
        PaymentResponse paymentResponse = testService.testPayPal()
        redirect(url: paymentResponse.approvalLink)
    }

    def cancelPayPal() {

    }

    def completePayPal() {
        [paymentResponse: paymentService.executePayPalPayment(params.transaction, params.PayerID)]
    }
}
