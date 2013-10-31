package com.trinary.paypal.payment

import java.util.Map

import com.trinary.Convertable

abstract class FundingInstrument implements Convertable {
    protected String id
    protected String payerId
    protected CreditCardType type
    protected Integer expireMonth
    protected Integer expireYear

    public FundingInstrument() {}

    public FundingInstrument(String id, String payerId, CreditCardType type, Integer expireMonth, Integer expireYear) {
        this.id = id
        this.payerId = id
        this.type = id
        this.expireMonth = expireMonth
        this.expireYear = year
    }

    public FundingInstrument(Map map) {
        this.id = map["id"] ?: id
        this.payerId = map["payerId"] ?: payerId
        this.type = map["type"] ?: type
        this.expireMonth = map["expireMonth"] ?: expireMonth
        this.expireYear = map["expireYear"] ?: expireYear
    }
}