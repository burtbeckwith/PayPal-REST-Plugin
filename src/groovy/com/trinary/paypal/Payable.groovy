package com.trinary.paypal

import java.util.ArrayList;

import com.trinary.paypal.payment.*
import com.trinary.paypal.test.OrderItem;

interface Payable {
    public Double getPrice()
    public Double getTaxRate()
	public Integer getQuantity()
    public Currency getCurrency()
    public String getDescription()
	
	public String getPaymentId()
	public void setPaymentId(String paymentId)
	
	public String getTransactionId()
	public void setTransactionId(String transactionId)
	
	public ArrayList<Payable> getItems()
}
