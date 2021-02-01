/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.core

import android.content.Context
import android.os.Bundle
import com.akurateco.sdk.core.AkuratecoCredential.init

/**
 * The [AkuratecoSdk] credentials holder.
 * It stores and retrieve it automatically after the first successful [init].
 * [AkuratecoCredential] stores values in the [AkuratecoStorage].
 * @throws AkuratecoSdkIsNotInitializedException if [AkuratecoSdk] is not initialized.
 */
internal object AkuratecoCredential {

    /**
     * Unique key to identify the account in Payment Platform (used as request parameter).
     */
    private const val CLIENT_KEY = "com.akurateco.sdk.CLIENT_KEY"

    /**
     * Password for Client authentication in Payment Platform (used for calculating hash parameter).
     */
    private const val CLIENT_PASS = "com.akurateco.sdk.CLIENT_PASS"

    /**
     * URL to request the Payment Platform.
     */
    private const val PAYMENT_URL = "com.akurateco.sdk.PAYMENT_URL"

    private var _clientKey: String? = null
    private var _clientPass: String? = null
    private var _paymentUrl: String? = null

    private var _isInitialized: Boolean? = null
    private var akuratecoStorage: AkuratecoStorage? = null

    /**
     * Initialize the [AkuratecoCredential] values.
     *
     * @param context the app context.
     * @param metadata the meta-data of the Application AndroidManifest.xml.
     */
    fun init(
        context: Context,
        metadata: Bundle
    ) {
        init(
            context,
            metadata.getString(CLIENT_KEY) ?: return,
            metadata.getString(CLIENT_PASS) ?: return,
            metadata.getString(PAYMENT_URL) ?: return
        )
    }

    /**
     * Initialize the [AkuratecoCredential] values.
     *
     * @param context the app context.
     * @param clientKey the [AkuratecoCredential.CLIENT_KEY] value.
     * @param clientPass the [AkuratecoCredential.CLIENT_PASS] value.
     * @param paymentUrl the [AkuratecoCredential.PAYMENT_URL] value.
     */
    fun init(
        context: Context,
        clientKey: String,
        clientPass: String,
        paymentUrl: String,
    ) {
        akuratecoStorage = AkuratecoStorage(context)

        setClientKey(clientKey)
        setClientPass(clientPass)
        setPaymentUrl(paymentUrl)
    }

    private fun setClientKey(key: String) {
        _clientKey = key
        akuratecoStorage?.setCredential(CLIENT_KEY, key)
    }

    /**
     * Get the [CLIENT_KEY] value.
     * @return [_clientKey].
     * @throws AkuratecoSdkIsNotInitializedException
     */
    fun clientKey(): String {
        requireInit()

        if (_clientKey == null) {
            _clientKey = akuratecoStorage?.getCredentials(CLIENT_KEY)
        }

        return _clientKey ?: throw AkuratecoSdkIsNotInitializedException()
    }

    private fun setClientPass(password: String) {
        _clientPass = password
        akuratecoStorage?.setCredential(CLIENT_PASS, password)
    }

    /**
     * Get the [CLIENT_PASS] value.
     * @return [_clientPass].
     * @throws AkuratecoSdkIsNotInitializedException
     */
    fun clientPass(): String {
        requireInit()

        if (_clientPass == null) {
            _clientPass = akuratecoStorage?.getCredentials(CLIENT_PASS)
        }

        return _clientPass ?: throw AkuratecoSdkIsNotInitializedException()
    }

    private fun setPaymentUrl(paymentUrl: String) {
        _paymentUrl = paymentUrl
        akuratecoStorage?.setCredential(PAYMENT_URL, paymentUrl)
    }

    /**
     * Get the [PAYMENT_URL] value.
     * @return [_paymentUrl].
     * @throws AkuratecoSdkIsNotInitializedException
     */
    fun paymentUrl(): String {
        requireInit()

        if (_paymentUrl == null) {
            _paymentUrl = akuratecoStorage?.getCredentials(PAYMENT_URL)
        }

        return _paymentUrl ?: throw AkuratecoSdkIsNotInitializedException()
    }

    /**
     * Soft check of the [AkuratecoCredential] initialization status.
     * @return the true if all [AkuratecoCredential] values are provided.
     * @throws AkuratecoSdkIsNotInitializedException
     */
    fun isInitialized(): Boolean {
        if (_isInitialized == null) {
            _isInitialized =
                !_clientKey.isNullOrEmpty() && !_clientPass.isNullOrEmpty() && !_paymentUrl.isNullOrEmpty()
        }

        return _isInitialized ?: throw AkuratecoSdkIsNotInitializedException()
    }

    /**
     * Hard check of the [AkuratecoCredential] initialization status.
     * @throws AkuratecoSdkIsNotInitializedException
     */
    fun requireInit() {
        if (!isInitialized()) {
            throw AkuratecoSdkIsNotInitializedException()
        }
    }
}
