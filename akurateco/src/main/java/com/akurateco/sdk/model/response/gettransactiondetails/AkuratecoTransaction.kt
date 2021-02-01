/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.gettransactiondetails

import androidx.annotation.FloatRange
import androidx.annotation.NonNull
import com.akurateco.sdk.model.api.AkuratecoTransactionStatus
import com.akurateco.sdk.model.api.AkuratecoTransactionType
import com.akurateco.sdk.toolbox.AkuratecoValidation
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 * The transaction data holder.
 * @see AkuratecoGetTransactionDetailsSuccess
 *
 * @property date transaction date.
 * @property type transaction type (sale, 3ds, auth, capture, chargeback, reversal, refund).
 * @property status transaction status (0-fail, 1-success).
 * @property amount transaction amount.
 */
data class AkuratecoTransaction(
    @NonNull
    @SerializedName("date")
    val date: Date,
    @NonNull
    @SerializedName("type")
    val type: AkuratecoTransactionType,
    @NonNull
    @SerializedName("status")
    val status: AkuratecoTransactionStatus,
    @NonNull
    @SerializedName("amount")
    @FloatRange(from = AkuratecoValidation.Amount.VALUE_MIN)
    val amount: Double,
) : Serializable

/* Response example:
{
    "transactions": [
    {
        "date": "2012-01-01 01:10:25",
        "type": "AUTH",
        "status": "1",
        "amount": "1.95"
    },
    {
        "date": "2012-01-01 01:11:30",
        "type": "CAPTURE",
        "status": "1",
        "amount": "1.95"
    },
    {
        "date": "2012-02-06 10:25:06",
        "type": "REFUND",
        "status": "1",
        "amount": "1.95"
    }
    ]
}
*/
