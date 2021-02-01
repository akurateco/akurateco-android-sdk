/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.base.result

/**
 * The base response order result data holder description.
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 * @see IAkuratecoResult
 */
interface IOrderAkuratecoResult : IAkuratecoResult {
    /**
     * Amount of capture.
     */
    val orderAmount: Double

    /**
     * Currency.
     */
    val orderCurrency: String
}
