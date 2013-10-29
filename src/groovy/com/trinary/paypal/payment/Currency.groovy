package com.trinary.paypal.payment

enum Currency {
    USD ("USD")

    protected String value

    public Currency(String value) {
        this.value = value
    }

    public String toString() {
        return value
    }
}
