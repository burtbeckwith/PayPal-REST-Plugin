package com.trinary.paypal.payment

import com.trinary.Convertable

class CreditCardToken extends FundingInstrument implements Convertable {
    protected String last4

    CreditCardToken() {}

    CreditCardToken(String id, String payerId, CreditCardType type, String last4, Integer expireMonth, Integer expireYear) {
        super(id, payerId, type, expireMonth, expireYear)
        this.last4 = last4
    }

    CreditCardToken(Map map) {
        super(map)
        this.last4 = map["last4"] ?: last4
    }

    Map buildMap() {
        return [
            credit_card_token: [
                credit_card_id: id,
                payer_id: payerId,
                last4: last4,
                type: type.toString(),
                expire_month: expireMonth,
                expire_year: expireYear
            ]
        ].findAll {key, value -> value != null}
    }
}
