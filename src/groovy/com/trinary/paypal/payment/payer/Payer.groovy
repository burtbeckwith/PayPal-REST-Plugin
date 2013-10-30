package com.trinary.paypal.payment.payer

import java.util.Map

import com.trinary.Convertable
import com.trinary.paypal.payment.PaymentMethod

abstract class Payer implements Convertable {
    protected PaymentMethod paymentMethod
	protected PayerInfo payerInfo
	
	public PayerInfo getPayerInfo() {
		return payerInfo
	}
}