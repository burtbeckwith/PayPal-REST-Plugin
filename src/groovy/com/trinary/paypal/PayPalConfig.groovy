package com.trinary.paypal

class PayPalConfig {
	protected static boolean sandbox = false
	static String clientId
	static String secret

	static void enableSandbox() {
		sandbox = true
	}

	static void disableSandbox() {
		sandbox = false
	}

	static boolean isSandbox() {
		return sandbox
	}
}
