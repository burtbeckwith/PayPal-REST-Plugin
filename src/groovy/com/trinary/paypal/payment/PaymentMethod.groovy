package com.trinary.paypal.payment

enum PaymentMethod {
    CREDIT_CARD ("credit_card"),
    PAYPAL      ("paypal")

    String value

    private PaymentMethod(String value) {
        this.value = value
    }

    String toString() { value }
}
