/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.capture

import com.akurateco.sdk.feature.adapter.AkuratecoCallback
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.base.result.IDetailsAkuratecoResult
import com.akurateco.sdk.model.response.sale.AkuratecoSaleDecline

/**
 * The response of the [com.akurateco.sdk.feature.adapter.AkuratecoCaptureAdapter].
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 */
typealias AkuratecoCaptureResponse = AkuratecoResponse<AkuratecoCaptureResult>

/**
 * The callback of the [com.akurateco.sdk.feature.adapter.AkuratecoCaptureAdapter].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoCallback
 */
typealias AkuratecoCaptureCallback = AkuratecoCallback<AkuratecoCaptureResult, AkuratecoCaptureResponse>

/**
 * The result of the [com.akurateco.sdk.feature.adapter.AkuratecoCaptureAdapter].
 *
 * @param result the [IDetailsAkuratecoResult].
 */
sealed class AkuratecoCaptureResult(open val result: IDetailsAkuratecoResult) {

    /**
     * Success result.
     *
     * @property result the [AkuratecoCaptureSuccess].
     */
    data class Success(override val result: AkuratecoCaptureSuccess) : AkuratecoCaptureResult(result)

    /**
     * Decline result.
     *
     * @property result the [AkuratecoSaleDecline].
     */
    data class Decline(override val result: AkuratecoSaleDecline) : AkuratecoCaptureResult(result)
}
