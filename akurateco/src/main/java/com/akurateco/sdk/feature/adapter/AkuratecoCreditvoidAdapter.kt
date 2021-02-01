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
import com.akurateco.sdk.feature.deserializer.AkuratecoCreditvoidDeserializer
import com.akurateco.sdk.feature.service.AkuratecoCaptureService
import com.akurateco.sdk.feature.service.AkuratecoCreditvoidService
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidCallback
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidResponse
import com.akurateco.sdk.toolbox.AkuratecoAmountFormatter
import com.akurateco.sdk.toolbox.AkuratecoHashUtil
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the CREDITVOID operation.
 * @see AkuratecoCreditvoidService
 * @see AkuratecoCreditvoidDeserializer
 * @see AkuratecoCreditvoidCallback
 * @see AkuratecoCreditvoidResponse
 */
object AkuratecoCreditvoidAdapter : AkuratecoBaseAdapter<AkuratecoCreditvoidService>() {

    private val akuratecoAmountFormatter = AkuratecoAmountFormatter()

    override fun provideServiceClass(): Class<AkuratecoCreditvoidService> = AkuratecoCreditvoidService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<AkuratecoCreditvoidResponse>(),
            AkuratecoCreditvoidDeserializer()
        )
    }

    /**
     * Executes the [AkuratecoCreditvoidService.creditvoid] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param callback the [AkuratecoCreditvoidCallback].
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
        callback: AkuratecoCreditvoidCallback
    ) {
        val hash = AkuratecoHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber,
            transactionId = transactionId
        )

        execute(transactionId, hash, amount, callback)
    }

    /**
     * Executes the [AkuratecoCreditvoidService.creditvoid] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to payment platform.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param callback the [AkuratecoCreditvoidCallback].
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
        callback: AkuratecoCreditvoidCallback
    ) {
        service.creditvoid(
            url = AkuratecoCredential.paymentUrl(),
            action = AkuratecoAction.CREDITVOID.action,
            clientKey = AkuratecoCredential.clientKey(),
            transactionId = transactionId,
            amount = amount?.let { akuratecoAmountFormatter.amountFormat(it) },
            hash = hash
        ).akuratecoEnqueue(callback)
    }
}
