package com.trinary.paypal

import java.util.ArrayList;

import com.trinary.paypal.payment.*
import com.trinary.paypal.test.OrderItem;

interface Orderable {
    public Double getTaxRate()
    public Currency getCurrency()
    public String getDescription()
	
	public String getPaymentId()
	public void setPaymentId(String paymentId)
	
	public String getTransactionId()
	public void setTransactionId(String transactionId)
	
	public ArrayList<Payable> getItems()
}