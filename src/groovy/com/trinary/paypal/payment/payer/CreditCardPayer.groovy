package com.trinary.paypal.payment.payer

import com.trinary.paypal.payment.FundingInstrument
import com.trinary.paypal.payment.PaymentMethod

class CreditCardPayer extends Payer {
    protected List<FundingInstrument> fundingInstruments = []
    protected PayerInfo payerInfo

    CreditCardPayer() {
        paymentMethod = PaymentMethod.CREDIT_CARD
    }

    void addFundingInstrument(FundingInstrument fundingInstrument) {
        fundingInstruments.add(fundingInstrument)
    }

    Map buildMap() {
        return [
            payment_method: paymentMethod.toString(),
            funding_instruments: fundingInstruments.collect { FundingInstrument fundingInstrument ->
                fundingInstrument?.buildMap()
            }
        ].findAll {key, value -> value != null}
    }
}
