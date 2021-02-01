/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.payer

import java.text.SimpleDateFormat
import java.util.*

/**
 * The [AkuratecoPayerOptions] values formatter.
 */
internal class AkuratecoPayerOptionsFormatter {

    private val BIRTHDATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    /**
     * Validate and format [AkuratecoPayerOptions.birthdate] value.
     *
     * @param payerOptions the [AkuratecoPayerOptions].
     */
    fun birthdateFormat(payerOptions: AkuratecoPayerOptions?): String? = payerOptions?.birthdate?.let {
        BIRTHDATE_FORMAT.format(it)
    }
}
