/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.adapter

import androidx.annotation.FloatRange
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.core.AkuratecoCredential
import com.akurateco.sdk.feature.deserializer.AkuratecoCaptureDeserializer
import com.akurateco.sdk.feature.service.AkuratecoCaptureService
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureCallback
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResponse
import com.akurateco.sdk.toolbox.AkuratecoAmountFormatter
import com.akurateco.sdk.toolbox.AkuratecoHashUtil
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the CAPTURE operation.
 * @see AkuratecoCaptureService
 * @see AkuratecoCaptureDeserializer
 * @see AkuratecoCaptureCallback
 * @see AkuratecoCaptureResponse
 */
object AkuratecoCaptureAdapter : AkuratecoBaseAdapter<AkuratecoCaptureService>() {

    private val akuratecoAmountFormatter = AkuratecoAmountFormatter()

    override fun provideServiceClass(): Class<AkuratecoCaptureService> = AkuratecoCaptureService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<AkuratecoCaptureResponse>(),
            AkuratecoCaptureDeserializer()
        )
    }

    /**
     * Executes the [AkuratecoCaptureService.capture] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param callback the [AkuratecoCaptureCallback].
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
        @Nullable
        @FloatRange(from = AkuratecoValidation.Amount.VALUE_MIN)
        amount: Double?,
        @NonNull
        callback: AkuratecoCaptureCallback
    ) {
        val hash = AkuratecoHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber,
            transactionId = transactionId
        )

        execute(transactionId, hash, amount, callback)
    }

    /**
     * Executes the [AkuratecoCaptureService.capture] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to payment platform.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param callback the [AkuratecoCaptureCallback].
     * @see com.akurateco.sdk.toolbox.AkuratecoHashUtil
     */
    fun execute(
        @NonNull
        @Size(AkuratecoValidation.Text.UUID)
        transactionId: String,
        @NonNull
        hash: String,
        @Nullable
        @FloatRange(from = AkuratecoValidation.Amount.VALUE_MIN)
        amount: Double?,
        @NonNull
        callback: AkuratecoCaptureCallback
    ) {
        service.capture(
            url = AkuratecoCredential.paymentUrl(),
            action = AkuratecoAction.CAPTURE.action,
            clientKey = AkuratecoCredential.clientKey(),
            transactionId = transactionId,
            amount = amount?.let { akuratecoAmountFormatter.amountFormat(it) },
            hash = hash
        ).akuratecoEnqueue(callback)
    }
}
