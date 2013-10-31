package com.trinary.paypal.payment

import java.util.Map;

import com.trinary.Convertable

class RedirectUrls implements Convertable {
    protected String returnUrl
    protected String cancelUrl

    public RedirectUrls() {}

    public RedirectUrls(String returnUrl, String cancelUrl) {
        this.returnUrl = returnUrl
        this.cancelUrl = cancelUrl
    }

    public RedirectUrls(Map map) {
        this.returnUrl = map["returnUrl"] ?: returnUrl
        this.cancelUrl = map["cancelUrl"] ?: cancelUrl
    }

    @Override
    public Map buildMap() {
        return [
            return_url: returnUrl,
            cancel_url: cancelUrl
        ].findAll {key, value -> value != null}
    }
}