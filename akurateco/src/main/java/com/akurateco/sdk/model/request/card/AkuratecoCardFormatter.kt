/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.card

import java.text.DecimalFormat

/**
 * The [AkuratecoCard] values formatter.
 */
internal class AkuratecoCardFormatter {

    private val EXPIRE_MONTH_FORMAT = DecimalFormat("00")

    /**
     * Validate and format the [AkuratecoCard.expireMonth].
     *
     * @param card the [AkuratecoCard].
     */
    fun expireMonthFormat(card: AkuratecoCard): String = EXPIRE_MONTH_FORMAT.format(card.expireMonth)

    /**
     * Validate and format the [AkuratecoCard.expireYear].
     *
     * @param card the [AkuratecoCard].
     */
    fun expireYearFormat(card: AkuratecoCard): String = card.expireYear.toString()
}
