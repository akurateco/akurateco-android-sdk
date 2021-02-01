/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.base.error

import androidx.annotation.NonNull
import com.akurateco.sdk.model.api.AkuratecoResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The exact error data holder.
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 * @see com.akurateco.sdk.feature.deserializer.AkuratecoBaseDeserializer
 * @see com.akurateco.sdk.feature.adapter.AkuratecoBaseAdapter
 * @see com.akurateco.sdk.feature.adapter.AkuratecoBaseAdapter
 * @see AkuratecoError
 *
 * @property code error code.
 * @property message error message.
 */
data class AkuratecoExactError(
    @NonNull
    @SerializedName("error_code")
    val code: String,
    @NonNull
    @SerializedName("error_message")
    val message: String
) : Serializable

/* Exact error codes:
204002 - Enabled merchant mappings or MIDs not found.
204003 - Payment type not supported.
204004 - Payment method not supported.
204005 - Payment action not supported.
204006 - Payment system/brand not supported.
204007 - Day MID limit is not set or exceeded.
204008 - Day Merchant mapping limit is not set or exceeded.
204009 - Payment type not found.
204010 - Payment method not found.
204011 - Payment system/brand not found.
204012 - Payment currency not found.
204013 - Payment action not found.
204014 - Month MID limit is exceeded.
204015 - Week Merchant mapping limit is exceeded.
208001 - Payment not found.
208002 - Not acceptable to request the 3ds for payment not in 3ds status.
208003 - Not acceptable to request the capture for payment not in pending status.
208004 - Not acceptable to request the capture for amount bigger than auth amount.
208005 - Not acceptable to request the refund for payment not in settled or pending status.
208006 - Not acceptable to request the refund for amount bigger than payment amount.
208008 - Not acceptable to request the reversal for amount bigger than payment amount.
208009 - Not acceptable to request the reversal for partial amount.
208010 - Not acceptable to request the chargeback for amount bigger than payments amount.
400 - Duplicate request.
400 - Previous payment not completed.
*/
