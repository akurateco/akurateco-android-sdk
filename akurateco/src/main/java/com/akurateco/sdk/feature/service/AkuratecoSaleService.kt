/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.service

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.model.response.sale.AkuratecoSaleResponse
import com.akurateco.sdk.toolbox.AkuratecoValidation
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * SALE Service for the [com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter].
 * @see AkuratecoSaleResponse
 *
 * Payment Platform supports two main operation type: Single Message System (SMS) and Dual Message System (DMS).
 * SMS is represented by SALE transaction. It is used for authorization and capture at a time.
 *
 * This operation is commonly used for immediate payments. DMS is represented by AUTH and CAPTURE transactions.
 * AUTH  is used for authorization only, without capture. This operation used to hold the funds on card account
 * (for example to check card validity). SALE request is used to make both SALE and AUTH transactions.
 *
 * If you want to make AUTH transaction, you need to use parameter auth with value Y.
 *
 * If you want to send a payment for the specific sub-account (channel), you need to use channel_id,
 * that specified in your Payment Platform account settings.
 */
interface AkuratecoSaleService {

    /**
     * Perform SALE request.
     *
     * @param url the [com.akurateco.sdk.core.AkuratecoCredential.PAYMENT_URL].
     * @param action the [com.akurateco.sdk.model.api.AkuratecoAction.SALE].
     * @param clientKey unique key [com.akurateco.sdk.core.AkuratecoCredential.CLIENT_KEY]. UUID format value.
     * @param channelId payment channel (Sub-account). String up to 16 characters.
     * @param orderId transaction ID in the Merchants system. String up to 255 characters.
     * @param orderAmount the amount of the transaction. Numbers in the form XXXX.XX (without leading zeros).
     * @param orderCurrency the currency. 3-letter code.
     * @param orderDescription description of the transaction (product name). String up to 1024 characters.
     * @param cardNumber the credit card number.
     * @param cardExpireMonth the month of expiry of the credit card. Month in the form XX.
     * @param cardExpireYear the year of expiry of the credit card. Year in the form XXXX.
     * @param cardCvv2 the CVV/CVC2 credit card verification code. 3-4 symbols.
     * @param payerFirstName customer’s name. String up to 32 characters.
     * @param payerLastName customer’s surname. String up to 32 characters.
     * @param payerMiddleName customer’s middle name. String up to 32 characters.
     * @param payerBirthDate customer’s birthday. Format: yyyy-MM-dd, e.g. 1970-02-17.
     * @param payerAddress customer’s address. String up to 255 characters.
     * @param payerAddress2 the adjoining road or locality of the сustomer’s address. String up to 255 characters.
     * @param payerCountry customer’s country. 2-letter code.
     * @param payerState customer’s state. String up to 32 characters.
     * @param payerCity customer’s city. String up to 32 characters.
     * @param payerZip ZIP-code of the Customer. String up to 32 characters.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param payerPhone customer’s phone. String up to 32 characters.
     * @param payerIp IP-address of the Customer. XXX.XXX.XXX.XXX.
     * @param termUrl3ds URL to which Customer should be returned after 3D-Secure. String up to 1024 characters.
     * @param recurringInit initialization of the transaction with possible following recurring. Y or N (default N).
     * @param auth indicates that transaction must be only authenticated, but not captured. Y or N (default N).
     * @param hash special signature to validate your request to Payment Platform.
     * @see com.akurateco.sdk.toolbox.AkuratecoHashUtil
     * @return the [Call] by [AkuratecoSaleResponse].
     */
    @FormUrlEncoded
    @POST
    fun sale(
        @NonNull
        @Url
        url: String,
        @NonNull
        @Field("action")
        action: String,
        @NonNull
        @Size(AkuratecoValidation.Text.UUID)
        @Field("client_key")
        clientKey: String,
        @Nullable
        @Size(max = AkuratecoValidation.Text.CHANNEL_ID)
        @Field("channel_id")
        channelId: String?,
        @NonNull
        @Size(max = AkuratecoValidation.Text.REGULAR)
        @Field("order_id")
        orderId: String,
        @NonNull
        @Field("order_amount")
        orderAmount: String,
        @NonNull
        @Size(AkuratecoValidation.Text.CURRENCY)
        @Field("order_currency")
        orderCurrency: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.LONG)
        @Field("order_description")
        orderDescription: String,
        @NonNull
        @Size(min = AkuratecoValidation.Card.CARD_NUMBER_MIN, max = AkuratecoValidation.Card.CARD_NUMBER_MAX)
        @Field("card_number")
        cardNumber: String,
        @NonNull
        @Field("card_exp_month")
        cardExpireMonth: String,
        @NonNull
        @Field("card_exp_year")
        cardExpireYear: String,
        @NonNull
        @Size(min = AkuratecoValidation.Card.CVV_MIN, max = AkuratecoValidation.Card.CVV_MAX)
        @Field("card_cvv2")
        cardCvv2: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.SHORT)
        @Field("payer_first_name")
        payerFirstName: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.SHORT)
        @Field("payer_last_name")
        payerLastName: String,
        @Nullable
        @Size(max = AkuratecoValidation.Text.SHORT)
        @Field("payer_middle_name")
        payerMiddleName: String?,
        @Nullable
        @Field("payer_birth_date")
        payerBirthDate: String?,
        @NonNull
        @Size(max = AkuratecoValidation.Text.REGULAR)
        @Field("payer_address")
        payerAddress: String,
        @Nullable
        @Size(max = AkuratecoValidation.Text.REGULAR)
        @Field("payer_address2")
        payerAddress2: String?,
        @NonNull
        @Size(AkuratecoValidation.Text.COUNTRY)
        @Field("payer_country")
        payerCountry: String,
        @Nullable
        @Size(max = AkuratecoValidation.Text.SHORT)
        @Field("payer_state")
        payerState: String?,
        @NonNull
        @Size(max = AkuratecoValidation.Text.SHORT)
        @Field("payer_city")
        payerCity: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.SHORT)
        @Field("payer_zip")
        payerZip: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.REGULAR)
        @Field("payer_email")
        payerEmail: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.SHORT)
        @Field("payer_phone")
        payerPhone: String,
        @NonNull
        @Size(min = AkuratecoValidation.Text.IP_MIN, max = AkuratecoValidation.Text.IP_MAX)
        @Field("payer_ip")
        payerIp: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.LONG)
        @Field("term_url_3ds")
        termUrl3ds: String,
        @Nullable
        @Field("recurring_init")
        recurringInit: String?,
        @Nullable
        @Field("auth")
        auth: String?,
        @NonNull
        @Field("hash")
        hash: String,
    ): Call<AkuratecoSaleResponse>
}

/* Data example:
action : SALE
client_key : c2b8fb04-110f-11ea-bcd3-0242c0a85004
order_id : ORDER-12345
order_amount : 1.99
order_currency : USD
order_description : Product
card_number : 4111111111111111
card_exp_month : 01
card_exp_year : 2025
card_cvv2 : 000
payer_first_name : John
payer_last_name : Doe
payer_address : Big street
payer_country : US
payer_state : CA
payer_city : City
payer_zip : 123456
payer_email : doe@example.com
payer_phone : 199999999
payer_ip : 123.123.123.123
term_url_3ds : http://client.site.com/return.php
recurring_init : Y
hash : a1a6de416405ada72bb47a49176471dc

The hash above was calculated for CLIENT_PASS equal to 13a4822c5907ed235f3a068c76184fc3
*/

/* Sample curl request:
curl –d "action=SALE&client_key=c2b8fb04-110f-11ea-bcd3-0242c0a85004 &order_id=ORDER12345&order_amount=1.99
&order_currency=USD&order_description=Prod uct&card_number=4111111111111111&card_exp_month=01&card_exp_year=2025
&card_cvv2= 000&payer_first_name=John&payer_last_name=Doe&payer_address=BigStreet&payer_coun try=US&payer_state=CA
&payer_city=City&payer_zip=123456&payer_email=doe@example.c om&payer_phone=199999999&payer_ip=123.123.123.123
&hash="a1a6de416405ada72bb47a49 176471dc" https://test.apiurl.com -k
*/
