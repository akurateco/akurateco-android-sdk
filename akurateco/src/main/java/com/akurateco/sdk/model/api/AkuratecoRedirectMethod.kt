/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.api

import com.google.gson.annotations.SerializedName

/**
 * The method of transferring parameters (POST/GET).
 * @see com.akurateco.sdk.model.response.sale.AkuratecoSale3ds
 *
 * @property redirectMethod the redirect method value.
 */
enum class AkuratecoRedirectMethod(val redirectMethod: String) {
    /**
     * GET redirect method value.
     */
    @SerializedName("GET")
    GET("GET"),

    /**
     * POST redirect method value.
     */
    @SerializedName("POST")
    POST("POST"),
}
