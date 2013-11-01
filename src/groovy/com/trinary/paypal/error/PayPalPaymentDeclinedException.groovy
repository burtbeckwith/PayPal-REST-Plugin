package com.trinary.paypal.error

class PayPalPaymentDeclinedException extends Exception {
	public PayPalPaymentDeclinedException(String message) {
		super(message)
	}
}
