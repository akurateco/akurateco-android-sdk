/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.akurateco.sdk.core.AkuratecoCredential
import com.akurateco.sdk.feature.deserializer.AkuratecoCaptureDeserializer
import com.akurateco.sdk.feature.deserializer.AkuratecoGetTransactionStatusDeserializer
import com.akurateco.sdk.feature.service.AkuratecoCaptureService
import com.akurateco.sdk.feature.service.AkuratecoGetTransactionStatusService
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.akurateco.sdk.model.response.gettransactionstatus.AkuratecoGetTransactionStatusCallback
import com.akurateco.sdk.model.response.gettransactionstatus.AkuratecoGetTransactionStatusResponse
import com.akurateco.sdk.toolbox.AkuratecoHashUtil
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the GET_TRANS_STATUS operation.
 * @see AkuratecoGetTransactionStatusService
 * @see AkuratecoGetTransactionStatusDeserializer
 * @see AkuratecoGetTransactionStatusCallback
 * @see AkuratecoGetTransactionStatusResponse
 */
object AkuratecoGetTransactionStatusAdapter : AkuratecoBaseAdapter<AkuratecoGetTransactionStatusService>() {

    override fun provideServiceClass(): Class<AkuratecoGetTransactionStatusService> =
        AkuratecoGetTransactionStatusService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<AkuratecoGetTransactionStatusResponse>(),
            AkuratecoGetTransactionStatusDeserializer()
        )
    }

    /**
     * Executes the [AkuratecoGetTransactionStatusService.getTransactionStatus] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param callback the [AkuratecoGetTransactionStatusCallback].
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
        callback: AkuratecoGetTransactionStatusCallback
    ) {
        val hash = AkuratecoHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber,
            transactionId = transactionId
        )

        execute(transactionId, hash, callback)
    }

    /**
     * Executes the [AkuratecoGetTransactionStatusService.getTransactionStatus] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to payment platform.
     * @param callback the [AkuratecoGetTransactionStatusCallback].
     */
    fun execute(
        @NonNull
        @Size(AkuratecoValidation.Text.UUID)
        transactionId: String,
        @NonNull
        hash: String,
        @NonNull
        callback: AkuratecoGetTransactionStatusCallback
    ) {
        service.getTransactionStatus(
            url = AkuratecoCredential.paymentUrl(),
            action = AkuratecoAction.GET_TRANS_STATUS.action,
            clientKey = AkuratecoCredential.clientKey(),
            transactionId = transactionId,
            hash = hash
        ).akuratecoEnqueue(callback)
    }
}
