package com.trinary.paypal.payment

enum Intent {
    SALE ("sale"),
    AUTHORIZE ("sale")

    protected String value

    public Intent(String value) {
        this.value = value
    }

    public String toString() {
        return value
    }
}