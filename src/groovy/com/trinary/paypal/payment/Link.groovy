package com.trinary.paypal.payment

class Link {
    protected String href
    protected String rel
    protected String method

    Link(String href, String rel, String method) {
        this.href   = href
        this.rel    = rel
        this.method = method
    }

    Link(Map map) {
        this.href   = map["href"] ?: href
        this.rel    = map["rel"] ?: rel
        this.method = map["method"] ?: method
    }
}
