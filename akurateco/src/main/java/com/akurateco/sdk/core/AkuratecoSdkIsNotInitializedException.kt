/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.core

/**
 * The [AkuratecoSdk] not initialized exception. Thrown when [AkuratecoCredential.requireInit] is not satisfied with
 * the [AkuratecoCredential.isInitialized] soft check.
 */
class AkuratecoSdkIsNotInitializedException : IllegalAccessError(MESSAGE) {

    companion object {
        private const val MESSAGE = """
           Akurateco SDK is not initialized.
           Please, provide your Payment Platform credentials in AndroidManifest.xml,
           and call AkuratecoSdk.init(yourAppContext) in YourApplication.class.
           You can also initialize the Akurateco SDK programmatically.
        """
    }
}
