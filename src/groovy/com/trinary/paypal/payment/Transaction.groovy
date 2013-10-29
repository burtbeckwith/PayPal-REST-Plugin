package com.trinary.paypal.payment

import java.util.Map

import com.trinary.Convertable

class Transaction implements Convertable {
    protected Amount amount
    protected String description
    protected ItemList itemList
    protected Map relatedResources = [:]

    public Transaction() {}

    public Transaction(Amount amount, ItemList itemList, String description, Map relatedResources) {
        this.amount = amount
        this.itemList = itemList
        this.description = description
        this.relatedResources = relatedResources
    }

    public Transaction(Map map) {
        this.amount = map["amount"]
        this.itemList = map["itemList"]
        this.description = map["description"]
        this.relatedResources = map["relatedResources"]
    }

    @Override
    public Map buildMap() {
        return [
            amount: amount.buildMap(),
            description: description,
            item_list: itemList?.buildMap()
        ].findAll {key, value -> value != null}
    }
}