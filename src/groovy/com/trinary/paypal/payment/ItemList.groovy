package com.trinary.paypal.payment

import com.trinary.Convertable
import com.trinary.paypal.payment.payer.ShippingAddress

class ItemList implements Convertable {
    protected List<Item> items = []
    protected ShippingAddress shippingAddress

    void addItem(Item item) {
        items.add(item)
    }

    void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress
    }

    Double getTotal() {
        items.sum { Item item -> item.price * item.quantity }
    }

    Map buildMap() {
        return [
            items: items.collect { Item item ->
                item?.buildMap()
            },
            shipping_address: shippingAddress?.buildMap()
        ].findAll {key, value -> value != null}
    }
}
