package com.trinary.paypal.payment

import java.util.Map;

import com.trinary.Convertable

class Details implements Convertable {
    protected Double shipping
    protected Double subtotal
    protected Double tax
    protected Double fee
	
	public Details() {}
	
	public Details(Double shipping, Double subtotal, Double tax, Double fee) {
		this.shipping = shipping
		this.subtotal = subtotal
		this.tax = tax
		this.fee = fee
	}

	public Details(Map map) {
		this.shipping = map["shipping"]
		this.subtotal = map["subtotal"]
		this.tax = map["tax"]
		this.fee = map["fee"]
	}
		
	protected void setShipping(Double shipping) {
		this.shipping = shipping
	}
	
	protected void setSubtotal(Double subTotal) {
		this.subtotal = subtotal
	}
	
	protected void setTax(Double tax) {
		this.tax = tax
	}
	
	protected void setFee(Double fee) {
		this.fee = fee
	}

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