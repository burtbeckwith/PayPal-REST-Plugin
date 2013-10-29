package com.trinary.paypal.payment

import java.util.Map

import com.trinary.Convertable

class Item implements Convertable {
    protected Integer quantity
    protected String name
    protected Double price
    protected String sku

    @Override
    public Map buildMap() {
        return [
            quantity: quantity,
            name: name,
            price: String.format('%.2f', price),
            sku: sku
        ].findAll {key, value -> value != null}
    }
}