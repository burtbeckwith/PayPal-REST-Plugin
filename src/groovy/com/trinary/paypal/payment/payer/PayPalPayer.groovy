package com.trinary.paypal.payment.payer

import java.util.Map

import com.trinary.Convertable
import com.trinary.paypal.payment.PaymentMethod

class PayPalPayer extends Payer implements Convertable {

    public PayPalPayer() {
        paymentMethod = PaymentMethod.PAYPAL
    }

    public PayPalPayer(PayerInfo payerInfo) {
        paymentMethod = PaymentMethod.PAYPAL
        this.payerInfo = payerInfo
    }

    @Override
    public Map buildMap() {
        return [
            payment_method: paymentMethod,
            payer_info: payerInfo?.buildMap()
        ].findAll {key, value -> value != null}
    }
}