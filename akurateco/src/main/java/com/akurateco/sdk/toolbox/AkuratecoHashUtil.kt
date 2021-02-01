/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.toolbox

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.akurateco.sdk.core.AkuratecoCredential
import com.akurateco.sdk.toolbox.AkuratecoValidation.Card
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Hash is signature rule used either to validate your requests to payment platform or to validate callback from
 * payment platform to your system.
 */
object AkuratecoHashUtil {

    /**
     * The returned has must be md5 encoded string calculated by rules:
     *
     * Hash is calculated by the formula (Initial calculation):
     * md5(strtoupper(strrev(email).CLIENT_PASS.strrev(substr(card_number,0,6).substr(card_number,-4))))
     *
     * Hash with the [transactionId] is calculated by the formula (Verify calculation):
     * md5(strtoupper(strrev(email).CLIENT_PASS.trans_id.strrev(substr(card_number,0,6).substr(card_number,-4)))
     *
     * @param email the payer email.
     * @param cardNumber the payer card number.
     * @param transactionId optional value. Used when the transaction ID is known.
     * @return the [md5] hash String.
     */
    fun hash(
        @NonNull
        @Size(max = AkuratecoValidation.Text.REGULAR)
        email: String,
        @NonNull
        @Size(min = Card.CARD_NUMBER_MIN, max = Card.CARD_NUMBER_MAX)
        cardNumber: String,
        @Nullable
        @Size(AkuratecoValidation.Text.UUID)
        transactionId: String? = null
    ): String {
        val emailHash = email.reversed()
        val clientPass = AkuratecoCredential.clientPass()

        val cardNumberHash1 = cardNumber.take(6)
        val cardNumberHash2 = cardNumber.takeLast(4)
        val cardNumberHash = cardNumberHash1.plus(cardNumberHash2).reversed()

        return md5(
            emailHash
                .plus(clientPass)
                .plus(transactionId.orEmpty())
                .plus(cardNumberHash)
                .toUpperCase(Locale.ROOT)
        )
    }

    private fun md5(text: String) = MessageDigest.getInstance("MD5").let { md5 ->
        BigInteger(1, md5.digest(text.toByteArray())).toString(16).padStart(32, '0')
    }
}
