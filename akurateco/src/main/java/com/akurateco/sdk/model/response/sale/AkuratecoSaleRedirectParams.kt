/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.sale

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The 3DS SALE redirect data holder. These values are required to proceed with the 3DSecure Payment flow.
 * @see AkuratecoSale3ds
 *
 * @property paymentRequisites the PaReq message from the DIBS server response.
 * @property md (Merchant Data) the reference number for the transaction. This parameter will be included in the
 * request to the TermUrl so that the merchant will be able to identify the transaction.
 * @property termUrl the URL (at the merchant's web site) that the authenticating bank should send the consumer back
 * to upon completion of the authentication.
 */
data class AkuratecoSaleRedirectParams(
    @NonNull
    @SerializedName("PaReq")
    val paymentRequisites: String,
    @NonNull
    @SerializedName("MD")
    val md: String,
    @NonNull
    @SerializedName("TermUrl")
    val termUrl: String,
) : Serializable
