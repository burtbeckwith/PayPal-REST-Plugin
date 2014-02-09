package com.trinary.paypal.payment

import com.trinary.Convertable

class Details implements Convertable {
    Double shipping = 0
    Double subtotal = 0
    Double tax = 0
    Double fee = 0
    Double taxRate

    Details() {}

    Details(Double shipping, Double subtotal, Double tax, Double fee) {
        this.shipping = shipping
        this.subtotal = subtotal
        this.tax = tax
        this.fee = fee
    }

    Details(Map map) {
        this.shipping = map["shipping"] ?: shipping
        this.subtotal = map["subtotal"] ?: subtotal
        this.tax = map["tax"] ?: tax
        this.fee = map["fee"] ?: fee
    }

    Double getTax() {
        return taxRate ? (taxRate * subtotal) : tax
    }

    Map buildMap() {
        return [
            shipping: String.format('%.2f', shipping ?: 0.0),
            subtotal: String.format('%.2f', subtotal ?: 0.0),
            tax: String.format('%.2f', getTax() ?: 0.0),
            fee: String.format('%.2f', fee ?: 0.0)
        ].findAll {key, value -> value != null}
    }
}
