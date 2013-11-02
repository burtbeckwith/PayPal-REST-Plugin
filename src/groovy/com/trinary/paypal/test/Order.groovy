package com.trinary.paypal.test

import com.trinary.paypal.*
import com.trinary.paypal.payment.*

class Order implements Orderable {
	protected Currency currency
	protected String description
	protected String paymentId
	protected String transactionId
	protected Double taxRate
	protected ArrayList<OrderItem> items = new ArrayList<OrderItem>()
	
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
	public String getTransactionId() {
		return transactionId
	}

	@Override
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId
	}
	
	@Override
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId
	}

	@Override
	public ArrayList<Payable> getItems() {
		return items
	}
}
