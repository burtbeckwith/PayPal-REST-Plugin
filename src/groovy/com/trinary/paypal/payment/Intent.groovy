package com.trinary.paypal.payment

enum Intent {
    SALE ("sale"),
    AUTHORIZE ("sale")

    protected String value

    private Intent(String value) {
        this.value = value
    }

    String toString() { value }
}
