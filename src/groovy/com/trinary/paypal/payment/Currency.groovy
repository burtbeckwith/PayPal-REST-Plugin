package com.trinary.paypal.payment

enum Currency {
    USD ("USD")

    protected String value

    private Currency(String value) {
        this.value = value
    }

    String toString() { value }
}
