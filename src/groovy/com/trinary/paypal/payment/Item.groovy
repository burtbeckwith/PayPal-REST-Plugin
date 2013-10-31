package com.trinary.paypal.payment

import java.util.Map

import com.trinary.Convertable

class Item implements Convertable {
    protected Integer quantity
    protected String name
    protected Double price
	protected Currency currency
    protected String sku
	
	public Item() {}
	
	public Item(Integer quantity, String name, Double price, Currency currency, String sku) {
		this.quantity = quantity
		this.name = name
		this.price = price
		this.sku = sku
		this.currency = currency
	}
	
	public Item(Map map) {
		this.quantity = map["quantity"] ?: quantity
		this.name = map["name"] ?: name
		this.price = map["price"] ?: price
		this.sku = map["sku"] ?: sku
		this.currency = map["currency"] ?: currency
	}

    @Override
    public Map buildMap() {
        return [
            quantity: quantity,
            name: name,
            price: String.format('%.2f', price),
            sku: sku,
			currency: currency.toString()
        ].findAll {key, value -> value != null}
    }
}