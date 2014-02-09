package com.trinary.paypal.payment.payer

class BillingAddress extends PayerAddress {
    BillingAddress() {}

    BillingAddress(String line1, String line2, String city, String countryCode, String postalCode, String state, String phone) {
        super(line1, line2, city, countryCode, postalCode, state, phone)
    }

    BillingAddress(Map map) {
        super(map)
    }

    Map buildMap() {
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
