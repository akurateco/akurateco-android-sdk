/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.deserializer

import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

/**
 * The base Akurateco [JsonDeserializer] to properly manage the response: result or error.
 * The end-user response is presenter by the [AkuratecoResponse].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoBaseAdapter
 *
 * @param Result the success result type for [Response].
 * @param Response the response type.
 */
abstract class AkuratecoBaseDeserializer<Result, Response : AkuratecoResponse<Result>> : JsonDeserializer<Response> {

    companion object {
        private const val RESULT = "result"
    }

    @Suppress("UNCHECKED_CAST")
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Response? {
        if (context == null) return null
        val jsonObject = json?.asJsonObject ?: return null

        val result = jsonObject[RESULT].asString
        return if (result.equals(AkuratecoResult.ERROR.result, ignoreCase = true)) {
            parseErrorResponse(context, jsonObject) as Response
        } else {
            parseResultResponse(context, jsonObject) as Response
        }
    }

    /**
     * Parse the generic response to the [AkuratecoResponse.Error].
     * @see com.akurateco.sdk.model.response.base.error.AkuratecoError
     */
    private fun parseErrorResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ) = AkuratecoResponse.Error<Result>(
        context.parse(jsonObject)
    )

    /**
     * Parse the generic response to the [AkuratecoResponse.Result]. Required to override.
     * @see com.akurateco.sdk.model.response.base.result.IAkuratecoResult
     */
    protected abstract fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): AkuratecoResponse.Result<Result>

    /**
     * Extension to parse the [JsonObject] by [ParseClass] type.
     *
     * @param ParseClass the class type to parse.
     * @param jsonObject the actual JSON.
     */
    protected inline fun <reified ParseClass> JsonDeserializationContext.parse(jsonObject: JsonObject) =
        deserialize<ParseClass>(jsonObject, ParseClass::class.java)
}
