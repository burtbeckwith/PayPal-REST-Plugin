package com.trinary.paypal.rest

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.budjb.requestbuilder.RequestBuilder
import com.budjb.requestbuilder.ResponseStatusException
import com.budjb.requestbuilder.UriBuilder
import com.trinary.Convertable
import com.trinary.paypal.PayPalConfig
import com.trinary.paypal.error.PayPalError
import com.trinary.paypal.error.exception.PayPalException
import com.trinary.paypal.oauth.OauthAccessToken
import com.trinary.paypal.oauth.OauthTokenFactory
import com.trinary.paypal.payment.Intent
import com.trinary.paypal.payment.RedirectUrls
import com.trinary.paypal.payment.Transaction
import com.trinary.paypal.payment.payer.Payer

class PaymentRequest implements Convertable {
    protected Intent intent
    protected Payer payer
    protected RedirectUrls redirectUrls
    protected List<Transaction> transactions = []

    protected static Logger log = LoggerFactory.getLogger(this)

    PaymentRequest() {}

    PaymentRequest(Intent intent, Payer payer, List<Transaction> transactions, RedirectUrls redirectUrls) {
        this.intent = intent
        this.payer = payer
        this.transactions = transactions ?: []
        this.redirectUrls = redirectUrls
    }

    PaymentRequest(Map map) {
        this.intent       = map["intent"]
        this.payer        = map["payer"]
        this.transactions = map["transactions"] ?: []
        this.redirectUrls = map["redirectUrls"]
    }

    void addTransaction(Transaction transaction) {
        transactions.add(transaction)
    }

    void setIntent(Intent intent) {
        this.intent = intent
    }

    PaymentResponse pay() throws PayPalException {
        OauthAccessToken accessToken = OauthTokenFactory.generateAccessToken()

        if (!accessToken) {
            log.error("Failed to create access token!")
            throw new PayPalException("An error has occurred.  Please contact administrator.  Your card has not been charged.")
        }

        try {
            Map json = new RequestBuilder().post {
                uri = UriBuilder.build {
                    base = PayPalConfig.sandbox ? "https://api.sandbox.paypal.com/v1" : "https://api.paypal.com/v1"
                    path = ["payments", "payment"]
                }
                accept = "application/json"
                contentType = "application/json"
                headers = [
                    "Authorization": "${accessToken}"
                ]
                body = buildMap()
                ignoreInvalidSSL = true
                debug = true
            }

            log.info("Response: ${json}")

            return PaymentResponse.createFromResponse(json)
        } catch (ResponseStatusException e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getContent()} | ${e.getMessage()}", e)
            throw new PayPalException("Error running payment request.", PayPalError.createFromResponse(e.getContent()))
        } catch (Exception e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getMessage()}", e)
            throw new PayPalException("An error has occurred.  Please contact administrator.  Your card has not been charged.")
        }

        return null
    }

    PaymentResponse execute(String paymentId, String payerId) {
        OauthAccessToken accessToken = OauthTokenFactory.generateAccessToken()

        if (!accessToken) {
            log.error("Failed to create access token!")
            return null
        }

        try {
            Map json = new RequestBuilder().post {
                uri = UriBuilder.build {
                    base = PayPalConfig.sandbox ? "https://api.sandbox.paypal.com/v1" : "https://api.paypal.com/v1"
                    path = ["payments", "payment", paymentId, "execute"]
                }
                accept = "application/json"
                contentType = "application/json"
                headers = [
                    "Authorization": "${accessToken}"
                ]
                body = [
                    "payer_id": payerId
                ]
                ignoreInvalidSSL = true
                debug = true
            }

            log.info("Response: ${json}")

            return PaymentResponse.createFromResponse(json)
        } catch (ResponseStatusException e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getContent()} | ${e.getMessage()}", e)
            throw new PayPalException("Error running payment request.", new PayPalError(e.getContent()))
        } catch (Exception e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getMessage()}", e)
            throw new PayPalException("An error has occurred.  Please contact administrator.  Your card has not been charged.")
        }

        return null
    }

    Map buildMap() {
        return [
            intent: intent.toString(),
            redirect_urls: redirectUrls?.buildMap(),
            payer: payer?.buildMap(),
            transactions: transactions?.collect { Transaction transaction ->
                transaction?.buildMap()
            }
        ].findAll {key, value -> value != null}
    }
}
