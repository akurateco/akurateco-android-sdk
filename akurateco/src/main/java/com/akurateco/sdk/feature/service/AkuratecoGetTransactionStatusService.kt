/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.service

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.akurateco.sdk.model.response.gettransactionstatus.AkuratecoGetTransactionStatusResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * GET_TRANS_STATUS Service for the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionStatusAdapter].
 * @see AkuratecoGetTransactionStatusResponse
 *
 * Gets order status from Payment Platform.
 */
interface AkuratecoGetTransactionStatusService {

    /**
     * Perform GET_TRANS_STATUS request.
     *
     * @param url the [com.akurateco.sdk.core.AkuratecoCredential.PAYMENT_URL].
     * @param action the [com.akurateco.sdk.model.api.AkuratecoAction.GET_TRANS_STATUS].
     * @param clientKey unique key [com.akurateco.sdk.core.AkuratecoCredential.CLIENT_KEY]. UUID format value.
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to Payment Platform.
     * @see com.akurateco.sdk.toolbox.AkuratecoHashUtil
     * @return the [Call] by [AkuratecoGetTransactionStatusResponse].
     */
    @FormUrlEncoded
    @POST
    fun getTransactionStatus(
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
        @NonNull
        @Field("hash")
        hash: String,
    ): Call<AkuratecoGetTransactionStatusResponse>
}
