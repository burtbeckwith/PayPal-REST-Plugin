package com.trinary.paypal.payment.payer

import java.util.Map;

import com.trinary.Convertable

class BillingAddress extends PayerAddress {
    public BillingAddress() {}

    public BillingAddress(String line1, String line2, String city, String countryCode, String postalCode, String state, String phone) {
        super(line1, line2, city, countryCode, postalCode, state, phone)
    }

    public BillingAddress(Map map) {
        super(map)
    }

    @Override
    public Map buildMap() {
        return [
            line1: line1,
            line2: line2,
            city: city,
            country_code: countryCode,
            postal_code: postalCode,
            state: state,
            phone: phone
        ].findAll {key, value -> value != null}
    }
}