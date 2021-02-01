/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.toolbox

import java.io.File

/**
 * The general [com.akurateco.sdk.core.AkuratecoSdk] utils.
 */
internal object AkuratecoUtil {

    /**
     * Validate and return the base url (Payment URL).
     * @see com.akurateco.sdk.core.AkuratecoCredential.PAYMENT_URL
     *
     * @param baseUrl the base url.
     * @return the validated base url String.
     */
    fun validateBaseUrl(baseUrl: String): String {
        return if (baseUrl.endsWith(File.separatorChar)) {
            baseUrl
        } else {
            baseUrl.plus(File.separatorChar)
        }
    }
}
