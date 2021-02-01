/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.api

import com.google.gson.annotations.SerializedName

/**
 * The transaction status types.
 * @see com.akurateco.sdk.model.response.gettransactiondetails.AkuratecoTransaction
 *
 * @property transactionStatus the transaction status value.
 */
enum class AkuratecoTransactionStatus(val transactionStatus: String) {
    /**
     * Failed or "0" status.
     */
    @SerializedName("fail")
    FAIL("fail"),

    /**
     * Success or "1" status.
     */
    @SerializedName("success")
    SUCCESS("success"),
}
