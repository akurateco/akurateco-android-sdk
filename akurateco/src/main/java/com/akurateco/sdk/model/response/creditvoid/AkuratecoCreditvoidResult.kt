/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.creditvoid

import com.akurateco.sdk.feature.adapter.AkuratecoCallback
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.base.result.IAkuratecoResult
import com.akurateco.sdk.model.response.base.result.IDetailsAkuratecoResult
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureSuccess

/**
 * The response of the [com.akurateco.sdk.feature.adapter.AkuratecoCreditvoidAdapter].
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 */
typealias AkuratecoCreditvoidResponse = AkuratecoResponse<AkuratecoCreditvoidResult>

/**
 * The callback of the [com.akurateco.sdk.feature.adapter.AkuratecoCreditvoidAdapter].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoCallback
 */
typealias AkuratecoCreditvoidCallback = AkuratecoCallback<AkuratecoCreditvoidResult, AkuratecoCreditvoidResponse>

/**
 * The result of the [com.akurateco.sdk.feature.adapter.AkuratecoCreditvoidAdapter].
 *
 * @param result the [IAkuratecoResult].
 */
sealed class AkuratecoCreditvoidResult(open val result: IAkuratecoResult) {

    /**
     * Success result.
     *
     * @property result the [AkuratecoCreditvoidSuccess].
     */
    data class Success(override val result: AkuratecoCreditvoidSuccess) : AkuratecoCreditvoidResult(result)
}
