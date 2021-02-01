/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.service

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * CREDITVOID Service for the [com.akurateco.sdk.feature.adapter.AkuratecoCreditvoidAdapter].
 * @see AkuratecoCreditvoidResponse
 *
 * CREDITVOID request is used to complete both REFUND and REVERSAL transactions.
 * REVERSAL transaction is used to cancel hold from funds on card account, previously authorized by AUTH transaction.
 * REFUND transaction is used to return funds to card account, previously submitted by SALE or CAPTURE transactions.
 */
interface AkuratecoCreditvoidService {

    /**
     * Perform CREDITVOID request.
     *
     * @param url the [com.akurateco.sdk.core.AkuratecoCredential.PAYMENT_URL].
     * @param action the [com.akurateco.sdk.model.api.AkuratecoAction.CREDITVOID].
     * @param clientKey unique key [com.akurateco.sdk.core.AkuratecoCredential.CLIENT_KEY]. UUID format value.
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param hash special signature to validate your request to Payment Platform.
     * @see com.akurateco.sdk.toolbox.AkuratecoHashUtil
     * @return the [Call] by [AkuratecoCreditvoidResponse].
     */
    @FormUrlEncoded
    @POST
    fun creditvoid(
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
        @NonNull
        @Size(AkuratecoValidation.Text.UUID)
        @Field("trans_id")
        transactionId: String,
        @Nullable
        @Field("amount")
        amount: String?,
        @NonNull
        @Field("hash")
        hash: String,
    ): Call<AkuratecoCreditvoidResponse>
}
