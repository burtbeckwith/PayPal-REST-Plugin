package com.trinary.paypal.payment

import java.util.Map

import com.trinary.Convertable

class Amount implements Convertable {
    protected Currency currency
    protected Double total
    protected Details details

    public Amount() {}

    public Amount(Currency currency, double total, Details details) {
        this.currency = currency
        this.total = total
        this.details = details
    }

    public Amount(Map map) {
        this.currency = map["currency"]
        this.total = map["total"]
        this.details = map["details"]
    }

    @Override
    public Map buildMap() {
        Double total = this.total ?: ((details.subtotal ?: 0.0) + (details.tax ?: 0.0) + (details.fee ?: 0.0) + (details.shipping ?: 0.0))

        return [
            currency: currency.toString(),
            total: String.format('%.2f', total),
            details: details.buildMap()
        ].findAll {key, value -> value != null}
    }
}