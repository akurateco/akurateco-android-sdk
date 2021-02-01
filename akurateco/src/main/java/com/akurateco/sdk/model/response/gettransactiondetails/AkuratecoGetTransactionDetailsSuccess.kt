/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.gettransactiondetails

import androidx.annotation.NonNull
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus
import com.akurateco.sdk.model.response.base.result.IOrderAkuratecoResult
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The GET_TRANS_DETAILS success result of the [AkuratecoGetTransactionDetailsResult].
 * @see AkuratecoGetTransactionDetailsResponse
 * @see AkuratecoTransaction
 *
 * @property name payer name.
 * @property mail payer mail.
 * @property ip payer IP.
 * @property card payer card number. Format: XXXXXXXX****XXXX.
 * @property transactions the [AkuratecoTransaction] list.
 */
data class AkuratecoGetTransactionDetailsSuccess(
    @NonNull
    @SerializedName("action")
    override val action: AkuratecoAction,
    @NonNull
    @SerializedName("result")
    override val result: AkuratecoResult,
    @NonNull
    @SerializedName("status")
    override val status: AkuratecoStatus,
    @NonNull
    @SerializedName("order_id")
    override val orderId: String,
    @NonNull
    @SerializedName("trans_id")
    override val transactionId: String,
    @NonNull
    @SerializedName("name")
    val name: String,
    @NonNull
    @SerializedName("mail")
    val mail: String,
    @NonNull
    @SerializedName("ip")
    val ip: String,
    @SerializedName("amount")
    override val orderAmount: Double,
    @NonNull
    @SerializedName("currency")
    override val orderCurrency: String,
    @NonNull
    @SerializedName("card")
    val card: String,
    @NonNull
    @SerializedName("transactions")
    val transactions: List<AkuratecoTransaction>
) : IOrderAkuratecoResult, Serializable
