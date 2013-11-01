package com.trinary.paypal.test

import com.trinary.paypal.*
import com.trinary.paypal.payment.*


class OrderItem implements Payable {
	String description
	Double price
	Double taxRate
	Integer quantity
	Currency currency

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
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId		
	}

	@Override
	public ArrayList<Payable> getItems() {
		return []
	}

}
