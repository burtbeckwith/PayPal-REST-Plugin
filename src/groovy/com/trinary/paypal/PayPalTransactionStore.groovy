package com.trinary.paypal

class PayPalTransactionStore {
	protected static List<Orderable> transactions = []

	static Orderable getTransaction(String transactionId) {
		Orderable found = transactions.find { Orderable orderable -> orderable.transactionId == transactionId }
		if (found) {
			transactions.remove(found)
		}

		return found
	}

	static void storeTransaction(Orderable orderable) {
		transactions.add(orderable)
	}
}
