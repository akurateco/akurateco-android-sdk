/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.response.base.result

import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus

/**
 * The base response result data holder description.
 * @see com.akurateco.sdk.model.response.base.AkuratecoResponse
 */
interface IAkuratecoResult {
    /**
     * The action of the transaction.
     */
    val action: AkuratecoAction

    /**
     * The result of the transaction.
     */
    val result: AkuratecoResult

    /**
     * The status of the transaction.
     */
    val status: AkuratecoStatus

    /**
     * Transaction ID in the Merchant’s system.
     */
    val orderId: String

    /**
     * Transaction ID in the Payment Platform.
     */
    val transactionId: String
}
