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
 * The required order data holder.
 * @see com.akurateco.sdk.feature.adapter.AkuratecoRecurringSaleAdapter
 * @see IAkuratecoOrder
 */
data class AkuratecoOrder(
    @NonNull
    @Size(max = AkuratecoValidation.Text.REGULAR)
    override val id: String,
    @NonNull
    @FloatRange(from = AkuratecoValidation.Amount.VALUE_MIN)
    override val amount: Double,
    @NonNull
    @Size(max = AkuratecoValidation.Text.LONG)
    override val description: String,
) : IAkuratecoOrder, Serializable
