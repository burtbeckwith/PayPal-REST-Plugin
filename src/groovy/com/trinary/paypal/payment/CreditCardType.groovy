package com.trinary.paypal.payment

enum CreditCardType {
    MASTERCARD ("mastercard"),
    VISA       ("visa"),
    AMEX       ("amex")

    protected String value

    public CreditCardType(String value) {
        this.value = value
    }

    public String toString() {
        return value
    }
}
