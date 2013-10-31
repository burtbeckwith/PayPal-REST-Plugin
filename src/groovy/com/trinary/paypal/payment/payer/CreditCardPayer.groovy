package com.trinary.paypal.payment.payer

import java.util.ArrayList
import java.util.Map

import com.trinary.Convertable
import com.trinary.paypal.payment.*

class CreditCardPayer extends Payer {
    protected ArrayList<FundingInstrument> fundingInstruments = new ArrayList<FundingInstrument>()
	protected PayerInfo payerInfo

    public CreditCardPayer() {
        paymentMethod = PaymentMethod.CREDIT_CARD
    }

    public void addFundingInstrument(FundingInstrument fundingInstrument) {
        fundingInstruments.add(fundingInstrument)
    }

    @Override
    public Map buildMap() {
        return [
            payment_method: paymentMethod.toString(),
            funding_instruments: fundingInstruments.collect { FundingInstrument fundingInstrument ->
                fundingInstrument?.buildMap()
            }
        ].findAll {key, value -> value != null}
    }
}