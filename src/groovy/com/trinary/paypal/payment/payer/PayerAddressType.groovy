package com.trinary.paypal.payment.payer

enum PayerAddressType {
    RESIDENTIAL ("residential"),
    BUSINESS    ("business"),
    MAILBOX     ("mailbox")

    protected String value

    private PayerAddressType(String value) {
        this.value = value
    }

    String toString() { value }
}
