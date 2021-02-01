/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.api

import com.google.gson.annotations.SerializedName

/**
 * The transaction type types.
 * @see com.akurateco.sdk.model.response.gettransactiondetails.AkuratecoTransaction
 *
 * @property transactionType the transaction type value.
 */
enum class AkuratecoTransactionType(val transactionType: String) {
    /**
     * 3DS transaction type.
     */
    @SerializedName("3DS")
    SECURE_3D("3DS"),

    /**
     * SALE transaction type.
     */
    @SerializedName("SALE")
    SALE("SALE"),

    /**
     * AUTH transaction type.
     */
    @SerializedName("AUTH")
    AUTH("AUTH"),

    /**
     * CAPTURE transaction type.
     */
    @SerializedName("CAPTURE")
    CAPTURE("CAPTURE"),

    /**
     * REVERSAL transaction type.
     */
    @SerializedName("REVERSAL")
    REVERSAL("REVERSAL"),

    /**
     * REFUND transaction type.
     */
    @SerializedName("REFUND")
    REFUND("REFUND"),

    /**
     * CHARGEBACK transaction type.
     */
    @SerializedName("CHARGEBACK")
    CHARGEBACK("CHARGEBACK"),
}
