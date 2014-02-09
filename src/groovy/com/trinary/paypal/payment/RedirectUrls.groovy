package com.trinary.paypal.payment

import com.trinary.Convertable

class RedirectUrls implements Convertable {
    protected String returnUrl
    protected String cancelUrl

    RedirectUrls() {}

    RedirectUrls(String returnUrl, String cancelUrl) {
        this.returnUrl = returnUrl
        this.cancelUrl = cancelUrl
    }

    RedirectUrls(Map map) {
        this.returnUrl = map["returnUrl"] ?: returnUrl
        this.cancelUrl = map["cancelUrl"] ?: cancelUrl
    }

    Map buildMap() {
        return [
            return_url: returnUrl,
            cancel_url: cancelUrl
        ].findAll {key, value -> value != null}
    }
}
