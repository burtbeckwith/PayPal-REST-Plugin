package com.trinary.paypal

import com.trinary.paypal.payment.Currency

interface Payable {
    Double getPrice()
    Integer getQuantity()
    Currency getCurrency()
    String getDescription()
}
