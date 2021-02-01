/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.toolbox

import java.text.NumberFormat
import java.util.*

/**
 * The amount values formatter. All amount value should be in the format: XXXX.XX (without leading zeros).
 */
internal class AkuratecoAmountFormatter {

    companion object {
        private const val INTEGER_DIGITS_MIN = 1
        private const val FRACTION_DIGITS = 2
    }

    private val AMOUNT_FORMAT = NumberFormat.getNumberInstance(Locale.US).apply {
        isGroupingUsed = false
        isParseIntegerOnly = false

        minimumFractionDigits = FRACTION_DIGITS
        maximumFractionDigits = FRACTION_DIGITS
        minimumIntegerDigits = INTEGER_DIGITS_MIN
    }

    /**
     * Validate and format the amount value.
     *
     * @param amount the double value.
     */
    fun amountFormat(amount: Double): String = AMOUNT_FORMAT.format(amount)
}
