package com.trinary.paypal.payment

enum PaymentMethod {
    CREDIT_CARD ("credit_card"),
    PAYPAL      ("paypal")

    String value

    public PaymentMethod(String value) {
        this.value = value
    }

    public String toString() {
        return value
    }
}