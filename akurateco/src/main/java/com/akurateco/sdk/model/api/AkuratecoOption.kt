/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.api

import com.google.gson.annotations.SerializedName

/**
 * "Y" or "N" value holder.
 *
 * @property option the option value.
 */
enum class AkuratecoOption(val option: String) {
    /**
     * "Y" value.
     */
    @SerializedName("Y")
    YES("Y"),

    /**
     * "N" value.
     */
    @SerializedName("N")
    NO("N");

    companion object {
        /**
         * Maps the [boolean] to the [AkuratecoOption].
         *
         * @param boolean value to map.
         */
        fun map(boolean: Boolean): AkuratecoOption = if (boolean) YES else NO
    }
}
