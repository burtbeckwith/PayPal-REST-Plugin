package com.trinary.paypal.test

import com.trinary.paypal.*
import com.trinary.paypal.payment.*

class Order implements Payable {
	Currency currency
	String description
	String paymentId
	Double taxRate
	ArrayList<OrderItem> items = new ArrayList<OrderItem>()
	
	@Override
	public Double getPrice() {
		Double total = 0.0
		items.each { OrderItem item ->
			total += item.price
		}
		
		return total
	}

	@Override
	public Double getTaxRate() {
		return taxRate
	}

	@Override
	public Integer getQuantity() {
		return 0
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
		return paymentId;
	}

	@Override
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId
	}

	@Override
	public ArrayList<Payable> getItems() {
		return items
	}

}
