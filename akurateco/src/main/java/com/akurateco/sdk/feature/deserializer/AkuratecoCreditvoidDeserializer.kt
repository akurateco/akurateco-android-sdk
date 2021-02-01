/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.deserializer

import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidResponse
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.akurateco.sdk.feature.adapter.AkuratecoCreditvoidAdapter].
 * @see AkuratecoCreditvoidResult
 * @see AkuratecoCreditvoidResponse
 */
class AkuratecoCreditvoidDeserializer :
    AkuratecoBaseDeserializer<AkuratecoCreditvoidResult, AkuratecoCreditvoidResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): AkuratecoResponse.Result<AkuratecoCreditvoidResult> {
        val creditvoidResult = AkuratecoCreditvoidResult.Success(context.parse(jsonObject))
        return AkuratecoResponse.Result(creditvoidResult)
    }
}
