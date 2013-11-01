package com.trinary.paypal

class PayPalTransactionStore {
	protected static ArrayList<Payable> transactions = new ArrayList<Payable>()
	
	public static Payable getTransaction(String transactionId) {
		Payable found = transactions.find { Payable payable -> payable.transactionId == transactionId }
		if (found) {
			transactions.remove(found)
		}
		
		return found
	}
	
	public static void storeTransaction(Payable payable) {
		transactions.add(payable)
	}
}
