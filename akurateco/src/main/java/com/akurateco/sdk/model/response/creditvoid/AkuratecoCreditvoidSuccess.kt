/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.creditvoid

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus
import com.akurateco.sdk.model.response.base.result.IAkuratecoResult
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The CREDITVOID success result of the [AkuratecoCreditvoidResult].
 * @see AkuratecoCreditvoidResponse
 */
data class AkuratecoCreditvoidSuccess(
    @NonNull
    @SerializedName("action")
    override val action: AkuratecoAction,
    @NonNull
    @SerializedName("result")
    override val result: AkuratecoResult,
    @Nullable
    @SerializedName("status")
    override val status: AkuratecoStatus,
    @NonNull
    @SerializedName("order_id")
    override val orderId: String,
    @NonNull
    @SerializedName("trans_id")
    override val transactionId: String
) : IAkuratecoResult, Serializable
