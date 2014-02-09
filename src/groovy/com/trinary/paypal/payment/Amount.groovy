package com.trinary.paypal.payment

import com.trinary.Convertable
import com.trinary.paypal.payment.Currency

class Amount implements Convertable {
    protected Currency currency
    Double total
    protected Details details = new Details()

    Amount() {}

    Amount(Currency currency, Double total, Details details) {
        this.currency = currency
        this.total = total
        this.details = details
    }

    Amount(Map map) {
        this.currency = map["currency"] ?: currency
        this.total = map["total"] ?: total
        this.details = map["details"] ?: details
    }

    Details getDetails() {
        return details
    }

    void setSubtotal(Double subtotal) {
        details.setSubtotal(subtotal)
    }

    Double getTotal() {
        return total ?: ((details.subtotal ?: 0.0) + (details.tax ?: 0.0) + (details.fee ?: 0.0) + (details.shipping ?: 0.0))
    }

    Map buildMap() {
        return [
            currency: currency.toString(),
            total: String.format('%.2f', getTotal()),
            details: details?.buildMap()
        ].findAll {key, value -> value != null}
    }
}
