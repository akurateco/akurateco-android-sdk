/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.payer

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.toolbox.AkuratecoValidation
import java.io.Serializable

/**
 * The required payer data holder.
 * @see com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter
 * @see AkuratecoPayerOptions
 *
 * @property firstName customer’s name. String up to 32 characters.
 * @property lastName customer’s surname. String up to 32 characters.
 * @property address customer’s address. String up to 255 characters.
 * @property country customer’s country. 2-letter code.
 * @property city customer’s city. String up to 32 characters.
 * @property zip ZIP-code of the Customer. String up to 32 characters.
 * @property email customer’s email. String up to 256 characters.
 * @property phone customer’s phone. String up to 32 characters.
 * @property ip IP-address of the Customer. XXX.XXX.XXX.XXX.
 * @property options the optional [AkuratecoPayerOptions].
 */
data class AkuratecoPayer(
    @NonNull
    @Size(max = AkuratecoValidation.Text.SHORT)
    val firstName: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.SHORT)
    val lastName: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.REGULAR)
    val address: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.COUNTRY)
    val country: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.SHORT)
    val city: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.SHORT)
    val zip: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.REGULAR)
    val email: String,
    @NonNull
    @Size(max = AkuratecoValidation.Text.SHORT)
    val phone: String,
    @NonNull
    @Size(min = AkuratecoValidation.Text.IP_MIN, max = AkuratecoValidation.Text.IP_MAX)
    val ip: String,
    @Nullable
    val options: AkuratecoPayerOptions? = null,
) : Serializable
