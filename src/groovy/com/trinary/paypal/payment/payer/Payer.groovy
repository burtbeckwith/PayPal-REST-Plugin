package com.trinary.paypal.payment.payer

import com.trinary.Convertable
import com.trinary.paypal.payment.PaymentMethod

abstract class Payer implements Convertable {
	protected PaymentMethod paymentMethod
	protected PayerInfo payerInfo

	PayerInfo getPayerInfo() {
		return payerInfo
	}
}
