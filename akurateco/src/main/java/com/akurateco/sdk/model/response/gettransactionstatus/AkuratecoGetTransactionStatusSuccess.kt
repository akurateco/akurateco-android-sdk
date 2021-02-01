/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.gettransactionstatus

import androidx.annotation.NonNull
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus
import com.akurateco.sdk.model.response.base.result.IAkuratecoResult
import com.akurateco.sdk.model.response.gettransactiondetails.AkuratecoGetTransactionDetailsResult
import com.akurateco.sdk.model.response.gettransactiondetails.AkuratecoTransaction
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The GET_TRANS_STATUS success result of the [AkuratecoGetTransactionStatusResult].
 * @see AkuratecoGetTransactionStatusResponse
 */
data class AkuratecoGetTransactionStatusSuccess(
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
    override val transactionId: String
) : IAkuratecoResult, Serializable
