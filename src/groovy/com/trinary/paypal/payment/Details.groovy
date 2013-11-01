package com.trinary.paypal.payment

import java.util.Map;

import com.trinary.Convertable

class Details implements Convertable {
    protected Double shipping = 0.0
    protected Double subtotal = 0.0
    protected Double tax = 0.0
    protected Double fee = 0.0
	
	protected Double taxRate = null
	
	public Details() {}
	
	public Details(Double shipping, Double subtotal, Double tax, Double fee) {
		this.shipping = shipping
		this.subtotal = subtotal
		this.tax = tax
		this.fee = fee
	}

	public Details(Map map) {
		this.shipping = map["shipping"] ?: shipping
		this.subtotal = map["subtotal"] ?: subtotal
		this.tax = map["tax"] ?: tax
		this.fee = map["fee"] ?: fee
	}
		
	public void setShipping(Double shipping) {
		this.shipping = shipping
	}
	
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal
	}
	
	public void setTax(Double tax) {
		this.tax = tax
	}
	
	public void setFee(Double fee) {
		this.fee = fee
	}
	
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate
	}
	
	public Double getShipping() {
		return shipping
	}
	
	public Double getSubtotal() {
		return subtotal
	}
	
	public Double getTax() {
		return taxRate ? (taxRate * subtotal) : (tax)
	}
	
	public Double getFee() {
		return fee
	}
	
	public Double getTaxRate() {
		return taxRate
	}
	
    @Override
    public Map buildMap() {
        return [
            shipping: String.format('%.2f', shipping ?: 0.0),
            subtotal: String.format('%.2f', subtotal ?: 0.0),
            tax: String.format('%.2f', getTax() ?: 0.0),
            fee: String.format('%.2f', fee ?: 0.0)
        ].findAll {key, value -> value != null}
    }
}