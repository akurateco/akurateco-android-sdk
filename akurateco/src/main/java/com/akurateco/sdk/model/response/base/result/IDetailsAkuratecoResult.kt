/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.base.result

import java.util.*

/**
 * The base response details result data holder description.
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 * @see IOrderAkuratecoResult
 */
interface IDetailsAkuratecoResult : IOrderAkuratecoResult {
    /**
     * Transaction date in the Payment Platform.
     */
    val transactionDate: Date

    /**
     * Descriptor from the bank, the same as cardholder will see in the bank statement. Optional.
     */
    val descriptor: String?
}
