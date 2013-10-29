package com.trinary.paypal.payment.payer

enum PayerAddressType {
    RESIDENTIAL ("residential"),
    BUSINESS    ("business"),
    MAILBOX     ("mailbox")

    protected String value

    public PayerAddressType(String value) {
        this.value = value
    }

    public String toString() {
        return value
    }
}
