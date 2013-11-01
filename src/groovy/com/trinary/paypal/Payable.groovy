package com.trinary.paypal

import com.trinary.paypal.payment.*

interface Payable {
    Double getPrice()
    Double getTaxRate()
	Integer getQuantity()
    Currency getCurrency()
    String getDescription()
	
	String getPaymentId()
	void setPaymentId(String paymentId)
	
	ArrayList<Payable> getItems()
}
