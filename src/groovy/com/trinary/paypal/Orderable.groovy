package com.trinary.paypal

import com.trinary.paypal.payment.Currency

interface Orderable {
	Double getTaxRate()
	Currency getCurrency()
	String getDescription()

	String getPaymentId()
	void setPaymentId(String paymentId)

	String getTransactionId()
	void setTransactionId(String transactionId)

	List<Payable> getItems()
}
