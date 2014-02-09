package com.trinary.paypal.test

import com.trinary.paypal.Orderable
import com.trinary.paypal.Payable
import com.trinary.paypal.payment.Currency

class Order implements Orderable {
	protected Currency currency
	protected String description
	String paymentId
	String transactionId
	protected Double taxRate
	protected List<OrderItem> items = []

	Double getPrice() {
		items.sum { OrderItem item -> item.price }
	}

	Double getTaxRate() {
		return taxRate
	}

	Integer getQuantity() {
		return 0
	}

	Currency getCurrency() {
		return currency
	}

	String getDescription() {
		return description
	}

	List<Payable> getItems() {
		return items
	}
}
