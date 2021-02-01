/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.gettransactiondetails

import com.akurateco.sdk.feature.adapter.AkuratecoCallback
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.base.result.IDetailsAkuratecoResult
import com.akurateco.sdk.model.response.base.result.IOrderAkuratecoResult
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureSuccess

/**
 * The response of the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionDetailsAdapter].
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 */
typealias AkuratecoGetTransactionDetailsResponse =
        AkuratecoResponse<AkuratecoGetTransactionDetailsResult>

/**
 * The callback of the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionDetailsAdapter].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoCallback
 */
typealias AkuratecoGetTransactionDetailsCallback =
        AkuratecoCallback<AkuratecoGetTransactionDetailsResult, AkuratecoGetTransactionDetailsResponse>

/**
 * The result of the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionDetailsAdapter].
 *
 * @param result the [IOrderAkuratecoResult].
 */
sealed class AkuratecoGetTransactionDetailsResult(open val result: IOrderAkuratecoResult) {

    /**
     * Success result.
     *
     * @property result the [AkuratecoGetTransactionDetailsSuccess].
     */
    data class Success(override val result: AkuratecoGetTransactionDetailsSuccess) :
        AkuratecoGetTransactionDetailsResult(result)
}
