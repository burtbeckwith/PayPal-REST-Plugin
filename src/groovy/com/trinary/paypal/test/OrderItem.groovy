package com.trinary.paypal.test

import com.trinary.paypal.*
import com.trinary.paypal.payment.*


class OrderItem implements Payable {
	protected String description
	protected Double price
	protected Double taxRate
	protected Integer quantity
	protected Currency currency

	@Override
	public Double getPrice() {
		return price
	}

	@Override
	public Double getTaxRate() {
		return taxRate
	}

	@Override
	public Integer getQuantity() {
		return quantity
	}

	@Override
	public Currency getCurrency() {
		return currency
	}

	@Override
	public String getDescription() {
		return description
	}

	@Override
	public String getPaymentId() {
		return null
	}
	
	@Override
	public String getTransactionId() {
		return null
	}

	@Override
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId		
	}
	
	@Override
	public void setTransactionId(String transactionId) {}

	@Override
	public ArrayList<Payable> getItems() {
		return []
	}

}
