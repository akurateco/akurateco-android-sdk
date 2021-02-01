/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.options

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import java.io.Serializable

/**
 * The required recurring options for the [com.akurateco.sdk.feature.adapter.AkuratecoRecurringSaleAdapter].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter
 *
 * @property firstTransactionId transaction ID of the primary transaction in the Payment Platform. UUID format value.
 * @property token value obtained during the primary transaction. UUID format value.
 */
data class AkuratecoRecurringOptions(
    @NonNull
    @Size(AkuratecoValidation.Text.UUID)
    val firstTransactionId: String,
    @NonNull
    @Size(AkuratecoValidation.Text.UUID)
    val token: String,
) : Serializable
