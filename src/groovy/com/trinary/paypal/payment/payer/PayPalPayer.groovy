package com.trinary.paypal.payment.payer

import com.trinary.Convertable
import com.trinary.paypal.payment.PaymentMethod

class PayPalPayer extends Payer implements Convertable {

    PayPalPayer() {
        paymentMethod = PaymentMethod.PAYPAL
    }

    PayPalPayer(PayerInfo payerInfo) {
        paymentMethod = PaymentMethod.PAYPAL
        this.payerInfo = payerInfo
    }

    Map buildMap() {
        return [
            payment_method: paymentMethod,
            payer_info: payerInfo?.buildMap()
        ].findAll {key, value -> value != null}
    }
}
