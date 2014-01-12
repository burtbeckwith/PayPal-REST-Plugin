package com.trinary.paypal.rest

import java.util.Map

import org.apache.log4j.Logger

import com.budjb.requestbuilder.*
import com.budjb.requestbuilder.httpexception.*

import com.trinary.Convertable
import com.trinary.paypal.*
import com.trinary.paypal.error.*
import com.trinary.paypal.error.exception.PayPalException
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class PaymentRequest implements Convertable {
    protected Intent intent
    protected Payer payer
    protected RedirectUrls redirectUrls
    protected ArrayList<Transaction> transactions = new ArrayList<Transaction>()

    protected static Logger log = Logger.getLogger(PaymentRequest.class)

	public PaymentRequest() {}

    public PaymentRequest(Intent intent, Payer payer, ArrayList<Transaction> transactions, RedirectUrls redirectUrls) {
        this.intent = intent
        this.payer = payer
        this.transactions = transactions ?: new ArrayList<Transaction>()
        this.redirectUrls = redirectUrls
    }

    public PaymentRequest(Map map) {
        this.intent       = map["intent"]
        this.payer        = map["payer"]
        this.transactions = map["transactions"] ?: new ArrayList<Transaction>()
        this.redirectUrls = map["redirectUrls"]
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction)
    }

    public void setIntent(Intent intent) {
        this.intent = intent
    }

    public PaymentResponse pay() throws PayPalException {
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
                body = this.buildMap()
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
	
	public PaymentResponse execute(String paymentId, String payerId) {
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

    @Override
    public Map buildMap() {
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