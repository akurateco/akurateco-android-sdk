/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.deserializer

import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResult
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidResult
import com.akurateco.sdk.model.response.gettransactionstatus.AkuratecoGetTransactionStatusResponse
import com.akurateco.sdk.model.response.gettransactionstatus.AkuratecoGetTransactionStatusResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionStatusAdapter].
 * @see AkuratecoGetTransactionStatusResult
 * @see AkuratecoGetTransactionStatusResponse
 */
class AkuratecoGetTransactionStatusDeserializer :
    AkuratecoBaseDeserializer<AkuratecoGetTransactionStatusResult, AkuratecoGetTransactionStatusResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): AkuratecoResponse.Result<AkuratecoGetTransactionStatusResult> {
        val getTransactionStatusResult = AkuratecoGetTransactionStatusResult.Success(context.parse(jsonObject))
        return AkuratecoResponse.Result(getTransactionStatusResult)
    }
}
