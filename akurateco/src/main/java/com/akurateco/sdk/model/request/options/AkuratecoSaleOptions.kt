/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.options

import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import java.io.Serializable

/**
 * The optional sale options for the [com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoRecurringSaleAdapter
 *
 * @property channelId payment channel (Sub-account). String up to 16 characters.
 * @property recurringInit initialization of the transaction with possible following recurring.
 */
data class AkuratecoSaleOptions(
    @Nullable
    @Size(max = AkuratecoValidation.Text.CHANNEL_ID)
    val channelId: String? = null,
    @Nullable
    val recurringInit: Boolean? = null,
) : Serializable
