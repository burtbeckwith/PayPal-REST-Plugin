package com.trinary.paypal.payment.payer

class ShippingAddress extends PayerAddress {
    String recipientName
    PayerAddressType type

    public ShippingAddress() {}

    public ShippingAddress(String line1, String line2, String city, String countryCode, String postalCode, String state, String phone, String recipientName, PayerAddressType type) {
        super(line1, line2, city, countryCode, postalCode, state, phone)
        this.recipientName = recipientName
        this.type = type
    }

    public ShippingAddress(Map map) {
        super(map)
        this.recipientName = map["recipientName"] ?: recipientName
        this.type          = map["type"] ?: type
    }

    public Map buildMap() {
        return [
            line1: line1,
            line2: line2,
            city: city,
            country_code: countryCode,
            postal_code: postalCode,
            state: state,
            phone: phone,
            recipient_name: recipientName,
            type: type
        ].findAll {key, value -> value != null}
    }
	
	public String toString() {
		return recipientName
			line1 +
			line2 +
			"${city}, ${state} ${postalCode}" +
			countryCode +
			type +
			phone
	}
}