package com.trinary.paypal.rest

import org.apache.log4j.Logger

import com.budjb.requestbuilder.*
import com.budjb.requestbuilder.httpexception.*
import com.trinary.Convertable
import com.trinary.paypal.*
import com.trinary.paypal.oauth.*
import com.trinary.paypal.payment.*
import com.trinary.paypal.payment.payer.*

class PaymentResponse {
    protected String id
    protected String state
    protected String createTime
    protected String updateTime

    protected Intent intent
    protected Payer payer
    protected ArrayList<Transaction> transactions = new ArrayList<Transaction>()
    protected ArrayList<Link> links = new ArrayList<Link>()

    protected static Logger log = Logger.getLogger(PaymentResponse.class)

    public PaymentResponse() {}

    static public PaymentResponse createFromResponse(Map responseMap) {
        PaymentResponse paymentResponse = new PaymentResponse()
        paymentResponse.id         = responseMap["id"]
        paymentResponse.state      = responseMap["state"]
        paymentResponse.createTime = responseMap["create_time"]
        paymentResponse.updateTime = responseMap["update_time"]
        paymentResponse.intent     = Intent.valueOf(responseMap["intent"].toUpperCase())

        // Convert links back to pogos
        responseMap["links"].each { link ->
            paymentResponse.links.add(new Link(link))
        }

        // Convert payer object back to pogo
        if (responseMap["payer"]["paymentMethod"] == "credit_card") {
            paymentResponse.payer = new CreditCardPayer()

            responseMap["payer"]["funding_instruments"].each { type, map ->
                if (type == "credit_card") {
                    ((CreditCardPayer)(paymentResponse.payer)).
                        addFundingInstrument(new CreditCard([
                            type: CreditCardType.valueOf(map["type"].toUpperCase()),
                            number: map["number"],
                            expireMonth: map["expire_month"],
                            expireYear: map["expire_year"],
                            firstName: map["first_name"],
                            lastName: map["last_name"],
                            billingAddress: new BillingAddress([
                                line1: map["billing_address"]["line1"],
                                line2: map["billing_address"]["line2"],
                                city: map["billing_address"]["city"],
                                state: map["billing_address"]["state"],
                                country_code: map["billing_address"]["country_code"],
                                postal_code: map["billing_address"]["postal_code"],
                                phone: map["billing_address"]["phone"]
                            ])
                    ]))
                } else if (type == "credit_card_token") {
                    ((CreditCardPayer)(paymentResponse.payer)).
                        addFundingInstrument(new CreditCard([
                            id: map["credit_card_id"],
                            type: CreditCardType.valueOf(map["type"].toUpperCase()),
                            last4: map["last4"],
                            expireMonth: map["expire_month"],
                            expireYear: map["expire_year"],
                            payerId: map["payer_id"]
                    ]))
                }
            }
        } else if (responseMap["payer"]["paymentMethod"] == "paypal") {
            paymentResponse.payer = new PayPalPayer()
        }

        // Convert transactions object back to pogo
        responseMap["transactions"].each { map ->
            paymentResponse.transactions.add(new Transaction([
                amount: new Amount([
                    total: map["amount"]["total"]?.toDouble(),
                    currency: Currency.valueOf(map["amount"]["currency"]),
                    details: new Details([
                        subtotal: map["amount"]["details"]["subtotal"]?.toDouble(),
                        tax: map["amount"]["details"]["tax"]?.toDouble(),
                        fee: map["amount"]["details"]["fee"]?.toDouble(),
                        shipping: map["amount"]["details"]["shipping"]?.toDouble()
                    ])
                ]),
                description: map["description"],
                relatedResources: map["relatedResources"]
            ]))
        }

        return paymentResponse
    }

    public PaymentResponse execute(String payerId) {
        OauthAccessToken accessToken = OauthTokenFactory.generateAccessToken()

        if (!accessToken) {
            log.error("Failed to create access token!")
            return null
        }

        try {
            Map json = new RequestBuilder().post {
                uri = UriBuilder.build {
                    base = PayPalConfig.sandbox ? "https://api.sandbox.paypal.com/v1" : "https://api.paypal.com/v1"
                    path = ["payments", "payment", id, "execute"]
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
        } catch (HttpBadRequestException e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getContent()} | ${e.getMessage()}", e)
        } catch (HttpUnauthorizedException e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getContent()} | ${e.getMessage()}", e)
        } catch (HttpNotAcceptableException e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getContent()} | ${e.getMessage()}", e)
        } catch (Exception e) {
            log.error("Error running payment request | ${e.getClass()} | ${e.getMessage()}", e)
        }

        return null
    }
}
