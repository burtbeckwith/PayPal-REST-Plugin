package com.trinary.paypal.payment.payer

import java.util.Map

import com.trinary.Convertable

abstract class PayerAddress implements Convertable {
    protected String line1
    protected String line2
    protected String city
    protected String countryCode
    protected String postalCode
    protected String state
    protected String phone

    public PayerAddress() {}

    public PayerAddress(String line1, String line2, String city, String countryCode, String postalCode, String state, String phone) {
        this.line1       = line1
        this.line2       = line2
        this.city        = city
        this.countryCode = countryCode
        this.postalCode  = postalCode
        this.state       = state
        this.phone       = phone
    }

    public PayerAddress(Map map) {
        this.line1       = map["line1"]
        this.line2       = map["line2"]
        this.city        = map["city"]
        this.countryCode = map["countryCode"]
        this.postalCode  = map["postalCode"]
        this.state       = map["state"]
        this.phone       = map["phone"]
    }
}