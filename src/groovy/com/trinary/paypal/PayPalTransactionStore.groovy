package com.trinary.paypal

class PayPalTransactionStore {
	protected static ArrayList<Orderable> transactions = new ArrayList<Orderable>()
	
	public static Orderable getTransaction(String transactionId) {
		Orderable found = transactions.find { Orderable orderable -> orderable.transactionId == transactionId }
		if (found) {
			transactions.remove(found)
		}
		
		return found
	}
	
	public static void storeTransaction(Orderable orderable) {
		transactions.add(orderable)
	}
}
