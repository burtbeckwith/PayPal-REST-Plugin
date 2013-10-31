package com.trinary.paypal.payment

import java.util.Map;

import com.trinary.Convertable
import com.trinary.paypal.payment.payer.BillingAddress

class CreditCard extends FundingInstrument implements Convertable {
    protected String number
    protected String cvv2
    protected String firstName
    protected String lastName
    protected BillingAddress billingAddress

    public CreditCard() {}

    public CreditCard(String id, String payerId, String number, String cvv2, CreditCardType type, Integer expireMonth, Integer expireYear, String firstName, String lastName, BillingAddress billingAddress) {
        super(id, payerId, type, expireMonth, expireYear)
        this.number = number
        this.cvv2 = cvv2
        this.firstName = firstName
        this.lastName = lastName
        this.billingAddress = billingAddress
    }

    public CreditCard(Map map) {
        super(map)
        this.number = map["number"] ?: number
        this.cvv2 = map["cvv2"] ?: cvv2
        this.firstName = map["firstName"] ?: firstName
        this.lastName = map["lastName"] ?: lastName
        this.billingAddress = map["billingAddress"] ?: billingAddress
    }

    @Override
    public Map buildMap() {
        return [
            credit_card: [
                id: id,
                payer_id: payerId,
                number: number,
                type: type.toString(),
                expire_month: expireMonth,
                expire_year: expireYear,
                cvv2: cvv2,
                first_name: firstName,
                last_name: lastName,
                billing_address: billingAddress?.buildMap()
            ]
        ].findAll {key, value -> value != null}
    }
}
