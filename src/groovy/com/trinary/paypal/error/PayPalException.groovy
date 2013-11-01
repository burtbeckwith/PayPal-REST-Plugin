package com.trinary.paypal.error

class PayPalException extends Exception {
	protected PayPalError errorMap
	
	PayPalException(String message, PayPalError errorMap) {
		super(message)
		this.errorMap = errorMap
	}
}
