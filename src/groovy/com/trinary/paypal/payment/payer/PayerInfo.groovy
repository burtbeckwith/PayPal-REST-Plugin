package com.trinary.paypal.payment.payer

import java.util.Map

import com.trinary.Convertable

class PayerInfo implements Convertable {
    protected String email
    protected String firstName
    protected String lastName
    protected String payerId
    protected String phone
    protected ShippingAddress shippingAddress

    public PayerInfo() {}

    public PayerInfo(String email, String firstName, String lastName, String payerId, String phone, ShippingAddress shippingAddress) {
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
        this.payerId = payerId
        this.phone = phone
        this.shippingAddress = shippingAddress
    }

    public PayerInfo(Map map) {
        this.email = map["email"] ?: email
        this.firstName = map["firstName"] ?: firstName
        this.lastName = map["lastName"] ?: lastName
        this.payerId = map["payerId"] ?: payerId
        this.phone = map["phone"] ?: phone
        this.shippingAddress = map["shippingAddress"] ?: shippingAddress
    }

    @Override
    public Map buildMap() {
        return [
            email: email,
            first_name: firstName,
            last_name: lastName,
            payer_id: payerId,
            phone: phone,
            shipping_address: shippingAddress?.buildMap()
        ].findAll {key, value -> value != null}
    }
}