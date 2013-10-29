package com.trinary.paypal.payment

import java.util.Map;

import com.trinary.Convertable

class Details implements Convertable {
    protected Double shipping
    protected Double subtotal
    protected Double tax
    protected Double fee

    @Override
    public Map buildMap() {
        return [
            shipping: String.format('%.2f', shipping ?: 0.0),
            subtotal: String.format('%.2f', subtotal ?: 0.0),
            tax: String.format('%.2f', tax ?: 0.0),
            fee: String.format('%.2f', fee ?: 0.0)
        ].findAll {key, value -> value != null}
    }
}