/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.service

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * CAPTURE Service for the [com.akurateco.sdk.feature.adapter.AkuratecoCaptureAdapter].
 * @see AkuratecoCaptureResponse
 *
 * CAPTURE request is used to submit previously authorized transaction (created by SALE request with
 * parameter auth = Y). Hold funds will be transferred to Merchants account.
 */
interface AkuratecoCaptureService {

    /**
     * Perform CAPTURE request.
     *
     * @param url the [com.akurateco.sdk.core.AkuratecoCredential.PAYMENT_URL].
     * @param action the [com.akurateco.sdk.model.api.AkuratecoAction.CAPTURE].
     * @param clientKey unique key [com.akurateco.sdk.core.AkuratecoCredential.CLIENT_KEY]. UUID format value.
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param hash special signature to validate your request to Payment Platform.
     * @see com.akurateco.sdk.toolbox.AkuratecoHashUtil
     * @return the [Call] by [AkuratecoCaptureResponse].
     */
    @FormUrlEncoded
    @POST
    fun capture(
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
    ): Call<AkuratecoCaptureResponse>
}
