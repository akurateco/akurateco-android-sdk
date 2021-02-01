/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.order

/**
 * The base order data holder description.
 * @see com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter
 */
interface IAkuratecoOrder {

    /**
     * Transaction ID in the Merchants system. String up to 255 characters.
     */
    val id: String

    /**
     * The amount of the transaction. Numbers in the form XXXX.XX (without leading zeros).
     */
    val amount: Double

    /**
     * Description of the transaction (product name). String up to 1024 characters.
     */
    val description: String
}
