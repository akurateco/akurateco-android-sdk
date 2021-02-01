/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.akurateco.sdk.core.AkuratecoCredential
import com.akurateco.sdk.feature.deserializer.AkuratecoSaleDeserializer
import com.akurateco.sdk.feature.service.AkuratecoRecurringSaleService
import com.akurateco.sdk.feature.service.AkuratecoSaleService
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoOption
import com.akurateco.sdk.model.request.card.AkuratecoCard
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.akurateco.sdk.model.request.options.AkuratecoRecurringOptions
import com.akurateco.sdk.model.request.options.AkuratecoSaleOptions
import com.akurateco.sdk.model.request.order.AkuratecoOrder
import com.akurateco.sdk.model.request.order.AkuratecoSaleOrder
import com.akurateco.sdk.model.request.payer.AkuratecoPayer
import com.akurateco.sdk.model.response.sale.AkuratecoSaleCallback
import com.akurateco.sdk.model.response.sale.AkuratecoSaleResponse
import com.akurateco.sdk.toolbox.AkuratecoAmountFormatter
import com.akurateco.sdk.toolbox.AkuratecoHashUtil
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the RECURRING_SALE operation.
 * @see AkuratecoRecurringSaleService
 * @see AkuratecoSaleDeserializer
 * @see AkuratecoSaleCallback
 * @see AkuratecoSaleResponse
 */
object AkuratecoRecurringSaleAdapter : AkuratecoBaseAdapter<AkuratecoRecurringSaleService>() {

    private val akuratecoAmountFormatter = AkuratecoAmountFormatter()

    override fun provideServiceClass(): Class<AkuratecoRecurringSaleService> =
        AkuratecoRecurringSaleService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<AkuratecoSaleResponse>(),
            AkuratecoSaleDeserializer()
        )
    }

    /**
     * Executes the [AkuratecoRecurringSaleService.recurringSale] request.
     *
     * @param order the [AkuratecoOrder].
     * @param options the [AkuratecoRecurringOptions].
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param auth indicates that transaction must be only authenticated, but not captured.
     * @param callback the [AkuratecoSaleCallback].
     */
    fun execute(
        @NonNull
        order: AkuratecoOrder,
        @NonNull
        options: AkuratecoRecurringOptions,
        @NonNull
        @Size(max = AkuratecoValidation.Text.REGULAR)
        payerEmail: String,
        @NonNull
        @Size(min = AkuratecoValidation.Card.CARD_NUMBER_MIN, max = AkuratecoValidation.Card.CARD_NUMBER_MAX)
        cardNumber: String,
        @NonNull
        auth: Boolean,
        @NonNull
        callback: AkuratecoSaleCallback
    ) {
        val hash = AkuratecoHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber
        )

        execute(order, options, hash, auth, callback)
    }

    /**
     * Executes the [AkuratecoRecurringSaleService.recurringSale] request.
     *
     * @param order the [AkuratecoOrder].
     * @param options the [AkuratecoRecurringOptions].
     * @param hash special signature to validate your request to payment platform.
     * @param auth indicates that transaction must be only authenticated, but not captured.
     * @param callback the [AkuratecoSaleCallback].
     * @see com.akurateco.sdk.toolbox.AkuratecoHashUtil
     */
    fun execute(
        @NonNull
        order: AkuratecoOrder,
        @NonNull
        options: AkuratecoRecurringOptions,
        @NonNull
        hash: String,
        @NonNull
        auth: Boolean,
        @NonNull
        callback: AkuratecoSaleCallback
    ) {
        service.recurringSale(
            url = AkuratecoCredential.paymentUrl(),
            action = AkuratecoAction.RECURRING_SALE.action,
            clientKey = AkuratecoCredential.clientKey(),
            orderId = order.id,
            orderAmount = akuratecoAmountFormatter.amountFormat(order.amount),
            orderDescription = order.description,
            recurringFirstTransactionId = options.firstTransactionId,
            recurringToken = options.token,
            auth = AkuratecoOption.map(auth).option,
            hash = hash
        ).akuratecoEnqueue(callback)
    }
}
