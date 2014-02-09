package com.trinary.paypal.error.exception

import com.trinary.paypal.error.PayPalError

class PayPalException extends Exception {
	protected PayPalError map

	PayPalException(String message) {
		super(message)
	}

	PayPalException(String message, PayPalError map) {
		super(message)
		this.map = map
	}
}
