/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.sale

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoRedirectMethod
import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus
import com.akurateco.sdk.model.response.base.result.IDetailsAkuratecoResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 * The SALE 3DS result of the [AkuratecoSaleResult].
 * @see AkuratecoSaleResponse
 *
 * @property redirectUrl URL to which the Merchant should redirect the Customer.
 * @property redirectParams the [AkuratecoSaleRedirectParams].
 * @property redirectMethod the method of transferring parameters (POST/GET).
 */
data class AkuratecoSale3ds(
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
    @SerializedName("trans_date")
    override val transactionDate: Date,
    @Nullable
    @SerializedName("descriptor")
    override val descriptor: String?,
    @NonNull
    @SerializedName("amount")
    override val orderAmount: Double,
    @NonNull
    @SerializedName("currency")
    override val orderCurrency: String,
    @NonNull
    @SerializedName("redirect_url")
    val redirectUrl: String,
    @NonNull
    @SerializedName("redirect_params")
    val redirectParams: AkuratecoSaleRedirectParams,
    @NonNull
    @SerializedName("redirect_method")
    val redirectMethod: AkuratecoRedirectMethod,
) : IDetailsAkuratecoResult, Serializable
