package com.trinary.paypal.payment

import java.util.Map

import com.trinary.Convertable
import com.trinary.paypal.payment.payer.ShippingAddress

class ItemList implements Convertable {
    protected ArrayList<Item> items = new ArrayList<Item>()
    protected ShippingAddress shippingAddress

    public void addItem(Item item) {
        items.add(item)
    }
	
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress
	}
	
	public Double getTotal() {
		double total = 0.0;
		items.each { Item item ->
			total += item.price * item.quantity
		}
		
		return total
	}

    @Override
    public Map buildMap() {
        return [
            items: items.collect { Item item ->
                item?.buildMap()
            },
            shipping_address: shippingAddress?.buildMap()
        ].findAll {key, value -> value != null}
    }
}