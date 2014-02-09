package com.trinary.paypal.payment

enum CreditCardType {
    MASTERCARD ("mastercard"),
    VISA       ("visa"),
    AMEX       ("amex")

    protected String value

    private CreditCardType(String value) {
        this.value = value
    }

    String toString() { value }
}
