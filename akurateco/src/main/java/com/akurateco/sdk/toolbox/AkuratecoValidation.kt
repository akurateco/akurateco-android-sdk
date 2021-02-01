/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.toolbox

internal object AkuratecoValidation {

    object Text {
        const val CHANNEL_ID = 16L
        const val UUID = 36L
        const val COUNTRY = 2L
        const val CURRENCY = 3L

        const val IP_MIN = 7L
        const val IP_MAX = 45L

        const val SHORT = 32L
        const val REGULAR = 255L
        const val LONG = 1024L
    }

    object Card {
        const val CARD_NUMBER_MIN = 12L
        const val CARD_NUMBER_MAX = 19L

        const val CVV_MIN = 3L
        const val CVV_MAX = 4L

        const val MONTH_MIN = 1L
        const val MONTH_MAX = 12L
    }

    object Amount {
        const val VALUE_MIN = 0.00
    }
}
