/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.sale

import com.akurateco.sdk.feature.adapter.AkuratecoCallback
import com.akurateco.sdk.model.response.base.AkuratecoResponse
import com.akurateco.sdk.model.response.base.result.IDetailsAkuratecoResult
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureSuccess

/**
 * The response of the [com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter].
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 */
typealias AkuratecoSaleResponse = AkuratecoResponse<AkuratecoSaleResult>

/**
 * The callback of the [com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter].
 * @see com.akurateco.sdk.feature.adapter.AkuratecoCallback
 */
typealias AkuratecoSaleCallback = AkuratecoCallback<AkuratecoSaleResult, AkuratecoSaleResponse>

/**
 * The result of the [com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter].
 *
 * @param result the [IDetailsAkuratecoResult].
 */
sealed class AkuratecoSaleResult(open val result: IDetailsAkuratecoResult) {

    /**
     * Success result.
     *
     * @property result the [AkuratecoSaleSuccess].
     */
    data class Success(override val result: AkuratecoSaleSuccess) : AkuratecoSaleResult(result)

    /**
     * Decline result.
     *
     * @property result the [AkuratecoSaleDecline].
     */
    data class Decline(override val result: AkuratecoSaleDecline) : AkuratecoSaleResult(result)

    /**
     * Recurring Init result.
     *
     * @property result the [AkuratecoSaleRecurring].
     */
    data class Recurring(override val result: AkuratecoSaleRecurring) : AkuratecoSaleResult(result)

    /**
     * 3DS result.
     *
     * @property result the [AkuratecoSale3ds].
     */
    data class Secure3d(override val result: AkuratecoSale3ds) : AkuratecoSaleResult(result)
}
