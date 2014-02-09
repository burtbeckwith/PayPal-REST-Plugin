package com.trinary.paypal.test

import com.trinary.paypal.Payable
import com.trinary.paypal.payment.Currency

class OrderItem implements Payable {
	protected String description
	protected Double price
	protected Double taxRate
	protected Integer quantity
	protected Currency currency

	Double getPrice() {
		return price
	}

	Double getTaxRate() {
		return taxRate
	}

	Integer getQuantity() {
		return quantity
	}

	Currency getCurrency() {
		return currency
	}

	String getDescription() {
		return description
	}

	String getPaymentId() {
		return null
	}

	String getTransactionId() {
		return null
	}

	void setPaymentId(String paymentId) {
		this.paymentId = paymentId
	}

	void setTransactionId(String transactionId) {}

	List<Payable> getItems() {
		return []
	}
}
