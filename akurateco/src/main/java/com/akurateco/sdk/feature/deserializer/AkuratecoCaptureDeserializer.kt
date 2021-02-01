/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.deserializer

import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResponse
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResult
import com.akurateco.sdk.model.response.sale.AkuratecoSaleResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.akurateco.sdk.feature.adapter.AkuratecoCaptureAdapter].
 * @see AkuratecoCaptureResult
 * @see AkuratecoCaptureResponse
 */
class AkuratecoCaptureDeserializer : AkuratecoBaseDeserializer<AkuratecoCaptureResult, AkuratecoCaptureResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): AkuratecoResponse.Result<AkuratecoCaptureResult> {
        val result = jsonObject["result"].asString

        val captureResult = if (result.equals(AkuratecoResult.DECLINED.result, ignoreCase = true)) {
            AkuratecoCaptureResult.Decline(context.parse(jsonObject))
        } else {
            AkuratecoCaptureResult.Success(context.parse(jsonObject))
        }

        return AkuratecoResponse.Result(captureResult)
    }
}
