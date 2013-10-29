package com.trinary.paypal

class PayPalConfig {
	protected static boolean sandbox = false
	protected static String clientId
	protected static String secret
	
	public static void setClientId(String newClientId) {
		clientId = newClientId
	}
	
	public static void setSecret(String newSecret) {
		secret = newSecret
	}
	
	public static String getClientId() {
		return clientId
	}
	
	public static String getSecret() {
		return secret
	} 
	
	public static void enableSandbox() {
		sandbox = true
	}
	
	public static void disableSandbox() {
		sandbox = false
	}
	
	public static boolean isSandbox() {
		return sandbox
	}
}
