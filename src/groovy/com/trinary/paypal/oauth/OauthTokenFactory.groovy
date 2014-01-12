package com.trinary.paypal.oauth

import org.apache.log4j.Logger

import com.budjb.requestbuilder.RequestBuilder
import com.budjb.requestbuilder.UriBuilder
import com.budjb.requestbuilder.ResponseStatusException

import com.trinary.paypal.*
import com.trinary.paypal.error.*
import com.trinary.paypal.error.exception.*

class OauthTokenFactory {
    protected static OauthAccessToken oauthAccessToken = null

    protected static Logger log = Logger.getLogger(OauthTokenFactory.class)

    public static OauthAccessToken generateAccessToken() {
        // Encode access token
        String oauth2Token = "${PayPalConfig.clientId}:${PayPalConfig.secret}".getBytes().encodeBase64()
        long started = new Date().getTime()

        // If token is expired, then get a new one
        if (!oauthAccessToken || oauthAccessToken.isExpired()) {
            try {
                Map json = new RequestBuilder().post {
                    uri = UriBuilder.build {
                        base = PayPalConfig.sandbox ? "https://api.sandbox.paypal.com/v1" : "https://api.paypal.com/v1"
                        path = ["oauth2", "token"]
                    }
                    accept = "application/json"
                    contentType = "application/x-www-form-urlencoded"
                    headers = [
                        "Authorization": "Basic ${oauth2Token}"
                    ]
                    form = [
                        "grant_type": "client_credentials"
                    ]
                    ignoreInvalidSSL = true
                    debug = true
                }

                log.info("Response: ${json}")

                oauthAccessToken = new OauthAccessToken(json["access_token"], json["token_type"], new Date((started + json["expires_in"]) * 1000))
            } catch (ResponseStatusException e) {
	            log.error("Error creating access token | ${e.getClass()} | ${e.getContent()} | ${e.getMessage()}", e)
				throw new PayPalException("Error creating access token.", PayPalError.createFromResponse(e.getContent()))
	        } catch (Exception e) {
	            log.error("Error creating access token | ${e.getClass()} | ${e.getMessage()}", e)
				throw new PayPalException("Error creating access token.")
	        }
        }

        return oauthAccessToken
    }
}