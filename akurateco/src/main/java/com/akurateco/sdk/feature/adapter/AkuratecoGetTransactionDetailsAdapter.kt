/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.akurateco.sdk.core.AkuratecoCredential
import com.akurateco.sdk.feature.deserializer.AkuratecoGetTransactionDetailsDeserializer
import com.akurateco.sdk.feature.deserializer.AkuratecoGetTransactionStatusDeserializer
import com.akurateco.sdk.feature.service.AkuratecoGetTransactionDetailsService
import com.akurateco.sdk.feature.service.AkuratecoGetTransactionStatusService
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.akurateco.sdk.model.response.gettransactiondetails.AkuratecoGetTransactionDetailsCallback
import com.akurateco.sdk.model.response.gettransactiondetails.AkuratecoGetTransactionDetailsResponse
import com.akurateco.sdk.toolbox.AkuratecoHashUtil
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the GET_TRANS_DETAILS operation.
 * @see AkuratecoGetTransactionDetailsService
 * @see AkuratecoGetTransactionDetailsDeserializer
 * @see AkuratecoGetTransactionDetailsCallback
 * @see AkuratecoGetTransactionDetailsResponse
 */
object AkuratecoGetTransactionDetailsAdapter : AkuratecoBaseAdapter<AkuratecoGetTransactionDetailsService>() {

    override fun provideServiceClass(): Class<AkuratecoGetTransactionDetailsService> =
        AkuratecoGetTransactionDetailsService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<AkuratecoGetTransactionDetailsResponse>(),
            AkuratecoGetTransactionDetailsDeserializer()
        )
    }

    /**
     * Executes the [AkuratecoGetTransactionDetailsService.getTransactionDetails] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param callback the [AkuratecoGetTransactionDetailsCallback].
     */
    fun execute(
        @NonNull
        @Size(AkuratecoValidation.Text.UUID)
        transactionId: String,
        @NonNull
        @Size(max = AkuratecoValidation.Text.REGULAR)
        payerEmail: String,
        @NonNull
        @Size(min = AkuratecoValidation.Card.CARD_NUMBER_MIN, max = AkuratecoValidation.Card.CARD_NUMBER_MAX)
        cardNumber: String,
        @NonNull
        callback: AkuratecoGetTransactionDetailsCallback
    ) {
        val hash = AkuratecoHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber,
            transactionId = transactionId
        )

        execute(transactionId, hash, callback)
    }

    /**
     * Executes the [AkuratecoGetTransactionDetailsService.getTransactionDetails] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to payment platform.
     * @param callback the [AkuratecoGetTransactionDetailsCallback].
     */
    fun execute(
        @NonNull
        @Size(AkuratecoValidation.Text.UUID)
        transactionId: String,
        @NonNull
        hash: String,
        @NonNull
        callback: AkuratecoGetTransactionDetailsCallback
    ) {
        service.getTransactionDetails(
            url = AkuratecoCredential.paymentUrl(),
            action = AkuratecoAction.GET_TRANS_DETAILS.action,
            clientKey = AkuratecoCredential.clientKey(),
            transactionId = transactionId,
            hash = hash
        ).akuratecoEnqueue(callback)
    }
}
