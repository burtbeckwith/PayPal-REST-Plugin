package com.trinary.paypal.oauth

class OauthAccessToken {
    protected String value
    protected String type
    protected Date   expires

    OauthAccessToken() {}

    OauthAccessToken(String value, String type, Date expires) {
        this.value = value
        this.type = type
        this.expires = expires
    }

    boolean isExpired() {
        return new Date() > expires
    }

    String toString() {
        return "${type} ${value}"
    }
}
