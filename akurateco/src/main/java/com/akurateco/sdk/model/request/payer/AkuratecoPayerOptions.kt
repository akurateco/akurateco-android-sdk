/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.payer

import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import java.io.Serializable
import java.util.*

/**
 * The optional payer options data holder.
 * @see com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter
 * @see AkuratecoPayer
 *
 * @property middleName customer’s middle name. String up to 32 characters.
 * @property birthdate customer’s birthday. Format: yyyy-MM-dd, e.g. 1970-02-17.
 * @property address2 the adjoining road or locality of the сustomer’s address. String up to 255 characters.
 * @property state customer’s state. String up to 32 characters.
 */
data class AkuratecoPayerOptions(
    @Nullable
    @Size(max = AkuratecoValidation.Text.SHORT)
    val middleName: String? = null,
    @Nullable
    val birthdate: Date? = null,
    @Nullable
    @Size(max = AkuratecoValidation.Text.REGULAR)
    val address2: String? = null,
    @Nullable
    @Size(max = AkuratecoValidation.Text.SHORT)
    val state: String? = null,
) : Serializable
