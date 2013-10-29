package com.trinary.paypal.oauth

class OauthAccessToken {
    protected String value
    protected String type
    protected Date   expires
	
	public OauthAccessToken() {}

    public OauthAccessToken(String value, String type, Date expires) {
        this.value = value
        this.type = type
        this.expires = expires
    }

    public boolean isExpired() {
        return new Date() > expires
    }

    public String toString() {
        return "${type} ${value}"
    }
}
