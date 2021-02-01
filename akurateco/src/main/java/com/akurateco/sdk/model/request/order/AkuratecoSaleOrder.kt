/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.order

import androidx.annotation.FloatRange
import androidx.annotation.NonNull
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import java.io.Serializable

/**
 * The sale order data holder.
 * @see com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter
 * @see IAkuratecoOrder
 *
 * @property currency the currency. 3-letter code.
 */
data class AkuratecoSaleOrder(
    @NonNull
    @Size(max = AkuratecoValidation.Text.REGULAR)
    override val id: String,
    @NonNull
    @FloatRange(from = AkuratecoValidation.Amount.VALUE_MIN)
    override val amount: Double,
    @NonNull
    @Size(max = AkuratecoValidation.Text.CURRENCY)
    val currency: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.LONG)
    override val description: String,
) : IAkuratecoOrder, Serializable
