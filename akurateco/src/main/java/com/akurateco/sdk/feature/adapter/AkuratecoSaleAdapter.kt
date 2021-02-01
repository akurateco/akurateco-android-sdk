/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.core.AkuratecoCredential
import com.akurateco.sdk.feature.deserializer.AkuratecoSaleDeserializer
import com.akurateco.sdk.feature.service.AkuratecoSaleService
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoOption
import com.akurateco.sdk.model.request.card.AkuratecoCard
import com.akurateco.sdk.model.request.card.AkuratecoCardFormatter
import com.akurateco.sdk.model.request.options.AkuratecoSaleOptions
import com.akurateco.sdk.model.request.order.AkuratecoSaleOrder
import com.akurateco.sdk.model.request.payer.AkuratecoPayer
import com.akurateco.sdk.model.request.payer.AkuratecoPayerOptionsFormatter
import com.akurateco.sdk.model.response.sale.AkuratecoSaleCallback
import com.akurateco.sdk.model.response.sale.AkuratecoSaleResponse
import com.akurateco.sdk.toolbox.AkuratecoAmountFormatter
import com.akurateco.sdk.toolbox.AkuratecoHashUtil
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the SALE operation.
 * @see AkuratecoSaleService
 * @see AkuratecoSaleDeserializer
 * @see AkuratecoSaleCallback
 * @see AkuratecoSaleResponse
 */
object AkuratecoSaleAdapter : AkuratecoBaseAdapter<AkuratecoSaleService>() {

    private val akuratecoAmountFormatter = AkuratecoAmountFormatter()
    private val akuratecoCardFormatter = AkuratecoCardFormatter()
    private val akuratecoPayerOptionsFormatter = AkuratecoPayerOptionsFormatter()

    override fun provideServiceClass(): Class<AkuratecoSaleService> = AkuratecoSaleService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<AkuratecoSaleResponse>(),
            AkuratecoSaleDeserializer()
        )
    }

    /**
     * Executes the [AkuratecoSaleService.sale] request.
     *
     * @param order the [AkuratecoSaleOrder].
     * @param card the [AkuratecoCard].
     * @param payer the [AkuratecoPayer].
     * @param termUrl3ds URL to which Customer should be returned after 3D-Secure. String up to 1024 characters.
     * @param options the [AkuratecoSaleOptions]. Optional.
     * @param auth indicates that transaction must be only authenticated, but not captured.
     * @param callback the [AkuratecoSaleCallback].
     */
    fun execute(
        @NonNull
        order: AkuratecoSaleOrder,
        @NonNull
        card: AkuratecoCard,
        @NonNull
        payer: AkuratecoPayer,
        @NonNull
        @Size(max = AkuratecoValidation.Text.LONG)
        termUrl3ds: String,
        @Nullable
        options: AkuratecoSaleOptions? = null,
        @NonNull
        auth: Boolean,
        @NonNull
        callback: AkuratecoSaleCallback
    ) {
        val hash = AkuratecoHashUtil.hash(
            email = payer.email,
            cardNumber = card.number
        )
        val payerOptions = payer.options

        service.sale(
            url = AkuratecoCredential.paymentUrl(),
            action = AkuratecoAction.SALE.action,
            clientKey = AkuratecoCredential.clientKey(),
            orderId = order.id,
            orderAmount = akuratecoAmountFormatter.amountFormat(order.amount),
            orderCurrency = order.currency,
            orderDescription = order.description,
            cardNumber = card.number,
            cardExpireMonth = akuratecoCardFormatter.expireMonthFormat(card),
            cardExpireYear = akuratecoCardFormatter.expireYearFormat(card),
            cardCvv2 = card.cvv,
            payerFirstName = payer.firstName,
            payerLastName = payer.lastName,
            payerAddress = payer.address,
            payerCountry = payer.country,
            payerCity = payer.city,
            payerZip = payer.zip,
            payerEmail = payer.email,
            payerPhone = payer.phone,
            payerIp = payer.ip,
            termUrl3ds = termUrl3ds,
            hash = hash,
            auth = AkuratecoOption.map(auth).option,
            channelId = if (options?.channelId.isNullOrEmpty()) null else options?.channelId,
            recurringInit = options?.recurringInit?.let { AkuratecoOption.map(it).option },
            payerMiddleName = if (payerOptions?.middleName.isNullOrEmpty()) null else payerOptions?.middleName,
            payerAddress2 = if (payerOptions?.address2.isNullOrEmpty()) null else payerOptions?.address2,
            payerState = if (payerOptions?.state.isNullOrEmpty()) null else payerOptions?.state,
            payerBirthDate = akuratecoPayerOptionsFormatter.birthdateFormat(payerOptions)
        ).akuratecoEnqueue(callback)
    }
}
