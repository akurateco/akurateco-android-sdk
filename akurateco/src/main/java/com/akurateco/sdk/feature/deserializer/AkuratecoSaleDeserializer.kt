/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.deserializer

import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.sale.AkuratecoSaleResponse
import com.akurateco.sdk.model.response.sale.AkuratecoSaleResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter].
 * @see AkuratecoSaleResult
 * @see AkuratecoSaleResponse
 */
class AkuratecoSaleDeserializer : AkuratecoBaseDeserializer<AkuratecoSaleResult, AkuratecoSaleResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): AkuratecoResponse.Result<AkuratecoSaleResult> {
        val status = jsonObject["status"].asString
        val result = jsonObject["result"].asString

        val saleResult = if (status.equals(AkuratecoStatus.SECURE_3D.status, ignoreCase = true)) {
            AkuratecoSaleResult.Secure3d(context.parse(jsonObject))
        } else {
            if (result.equals(AkuratecoResult.DECLINED.result, ignoreCase = true)) {
                AkuratecoSaleResult.Decline(context.parse(jsonObject))
            } else {
                if (jsonObject.has("recurring_token")) {
                    AkuratecoSaleResult.Recurring(context.parse(jsonObject))
                } else {
                    AkuratecoSaleResult.Success(context.parse(jsonObject))
                }
            }
        }

        return AkuratecoResponse.Result(saleResult)
    }
}
