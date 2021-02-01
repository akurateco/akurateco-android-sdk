/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.feature.adapter

import com.akurateco.sdk.model.response.base.error.AkuratecoError
import com.akurateco.sdk.model.response.base.AkuratecoResponse

/**
 * The Akurateco Callback for the [AkuratecoBaseAdapter] and its extenders.
 *
 * @param Result the successful result type of the [Response].
 * @param Response the response of the request type.
 */
interface AkuratecoCallback<Result, Response : AkuratecoResponse<Result>> {

    /**
     * The custom success result callback. Required to override.
     * Called after the [onResponse] in case the response is [AkuratecoResponse.Result].
     *
     * @param result the [Result].
     */
    fun onResult(result: Result)

    /**
     * The custom error result callback. Required to override.
     * Called after the [onResponse] in case the response is [AkuratecoResponse.Error].
     *
     * @param error the [AkuratecoError].
     */
    fun onError(error: AkuratecoError)

    /**
     * The unhandled exception callback. Optional to override.
     *
     * @param throwable the [Throwable].
     */
    fun onFailure(throwable: Throwable) = Unit

    /**
     * The custom success response callback: result or error. Optional to override.
     * @see AkuratecoResponse
     *
     * @param response the [Response].
     */
    fun onResponse(response: Response) = Unit
}
