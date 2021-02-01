/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.gettransactionstatus

import com.akurateco.sdk.feature.adapter.AkuratecoCallback
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.base.result.IAkuratecoResult
import com.akurateco.sdk.model.response.base.result.IDetailsAkuratecoResult
import com.akurateco.sdk.model.response.base.result.IOrderAkuratecoResult
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureSuccess
import com.akurateco.sdk.model.response.gettransactiondetails.AkuratecoGetTransactionDetailsSuccess

/**
 * The response of the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionStatusAdapter].
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 */
typealias AkuratecoGetTransactionStatusResponse =
        AkuratecoResponse<AkuratecoGetTransactionStatusResult>

/**
 * The callback of the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionStatusAdapter].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoCallback
 */
typealias AkuratecoGetTransactionStatusCallback =
        AkuratecoCallback<AkuratecoGetTransactionStatusResult, AkuratecoGetTransactionStatusResponse>

/**
 * The result of the [com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionStatusAdapter].
 *
 * @param result the [IAkuratecoResult].
 */
sealed class AkuratecoGetTransactionStatusResult(open val result: IAkuratecoResult) {

    /**
     * Success result.
     *
     * @property result the [AkuratecoGetTransactionStatusSuccess].
     */
    data class Success(override val result: AkuratecoGetTransactionStatusSuccess) :
        AkuratecoGetTransactionStatusResult(result)
}
