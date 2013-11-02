package com.trinary.paypal

import java.util.ArrayList;

import com.trinary.paypal.payment.*
import com.trinary.paypal.test.OrderItem;

interface Payable {
    public Double getPrice()
	public Integer getQuantity()
    public Currency getCurrency()
    public String getDescription()
}