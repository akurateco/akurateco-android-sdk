/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.core

import android.content.Context
import android.content.pm.PackageManager
import com.akurateco.sdk.feature.adapter.AkuratecoCaptureAdapter
import com.akurateco.sdk.feature.adapter.AkuratecoCreditvoidAdapter
import com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionDetailsAdapter
import com.akurateco.sdk.feature.adapter.AkuratecoGetTransactionStatusAdapter
import com.akurateco.sdk.feature.adapter.AkuratecoRecurringSaleAdapter
import com.akurateco.sdk.feature.adapter.AkuratecoSaleAdapter

/**
 * The initial point of the [AkuratecoSdk].
 *
 * Before you get an account to access Payment Platform, you must provide the following data to the Payment Platform
 * administrator:
 * IP list - List of your IP addresses, from which requests to Payment Platform will be sent.
 * Notification URL - URL which will be receiving the notifications of the processing results of your request to
 * Payment Platform.
 * Contact email -  Email address of Responsible Person who will monitor transactions, conduct refunds, etc.
 *
 * Note: Notification URL is mandatory if your account supports 3D-Secure. The length of Notification URL should not be
 * more than 255 symbols.
 * With all Payment Platform POST requests at Notification URL the Merchant must return the string "OK" if he
 * successfully received data or return "ERROR".
 *
 * You should get the following information from administrator to begin working with the Payment Platform:
 * [AkuratecoCredential.CLIENT_KEY] - Unique key to identify the account in Payment Platform
 * (used as request parameter).
 * [AkuratecoCredential.CLIENT_PASS] - Password for Client authentication in Payment Platform
 * (used for calculating hash parameter).
 * [AkuratecoCredential.PAYMENT_URL] - URL to request the Payment Platform.
 * @see com.akurateco.sdk.feature.adapter.AkuratecoBaseAdapter
 * @see com.akurateco.sdk.toolbox.AkuratecoHashUtil.md5
 *
 * For the transaction, you must send the server to server HTTPS POST request with fields listed below to Payment
 * Platform URL ([AkuratecoCredential.PAYMENT_URL]). In response Payment Platform will return the JSON  encoded string.
 * If your account supports 3D-Secure and credit card supports 3D-Secure, then Payment Platform will return the link to
 * the 3D-Secure Access Control Server to perform 3D-Secure verification. In this case, you need to redirect the
 * cardholder at this link. If there are also some parameters except the link in the result, you will need to redirect
 * the cardholder at this link together with the parameters using the method of data transmitting indicated in the same
 * result. In the case of 3D-Secure after verification on the side of the 3D-Secure server, the owner of a credit card
 * will come back to your site using the link you specify in the sale request, and Payment Platform will return the
 * result of transaction processing to the Notification URL action.
 *
 * To initialize the [AkuratecoSdk] call one of the following methods: [init].
 * The initialization can be done programmatically or through the Application AndroidManifest.xml.
 * [AkuratecoSdk] requires the Internet permission: <uses-permission android:name="android.permission.INTERNET" />
 * In case the [AkuratecoSdk] is not initialized the [AkuratecoSdkIsNotInitializedException] will be thrown.
 *
 * To test/simulate the Platon Payment System use [com.akurateco.sdk.model.request.card.AkuratecoTestCard].
 */
object AkuratecoSdk {

    /**
     * Initialize the [AkuratecoSdk] implicitly using the meta-data of the AndroidManifest.xml
     *
     * @param context the app context.
     * @throws AkuratecoSdkIsNotInitializedException
     */
    fun init(context: Context) {
        val metaData = context
            .packageManager
            .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            .metaData

        AkuratecoCredential.init(context, metaData)
        AkuratecoCredential.requireInit()
    }

    /**
     * Initialize the [AkuratecoSdk] explicitly using the [AkuratecoCredential] values.
     *
     * @param context the app context.
     * @param clientKey the [AkuratecoCredential.CLIENT_KEY] value.
     * @param clientPass the [AkuratecoCredential.CLIENT_PASS] value.
     * @param paymentUrl the [AkuratecoCredential.PAYMENT_URL] value.
     * @throws AkuratecoSdkIsNotInitializedException
     */
    fun init(
        context: Context,
        clientKey: String,
        clientPass: String,
        paymentUrl: String,
    ) {
        AkuratecoCredential.init(context, clientKey, clientPass, paymentUrl)
        AkuratecoCredential.requireInit()
    }

    /**
     * Indicating of the [AkuratecoSdk] initialize status.
     */
    fun isInitialized(): Boolean = AkuratecoCredential.isInitialized()

    /**
     * The [com.akurateco.sdk.feature.adapter]s holder to check the [AkuratecoSdk] initialization status before use.
     * @throws AkuratecoSdkIsNotInitializedException
     */
    object Adapter {
        /**
         * Adapter for the [com.akurateco.sdk.model.api.AkuratecoAction.RECURRING_SALE] request.
         * @throws AkuratecoSdkIsNotInitializedException
         */
        val RECURRING_SALE: AkuratecoRecurringSaleAdapter = AkuratecoRecurringSaleAdapter
            get() {
                AkuratecoCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.akurateco.sdk.model.api.AkuratecoAction.SALE] request.
         * @throws AkuratecoSdkIsNotInitializedException
         */
        val SALE: AkuratecoSaleAdapter = AkuratecoSaleAdapter
            get() {
                AkuratecoCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.akurateco.sdk.model.api.AkuratecoAction.CAPTURE] request.
         * @throws AkuratecoSdkIsNotInitializedException
         */
        val CAPTURE: AkuratecoCaptureAdapter = AkuratecoCaptureAdapter
            get() {
                AkuratecoCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.akurateco.sdk.model.api.AkuratecoAction.CREDITVOID] request.
         * @throws AkuratecoSdkIsNotInitializedException
         */
        val CREDITVOID: AkuratecoCreditvoidAdapter = AkuratecoCreditvoidAdapter
            get() {
                AkuratecoCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.akurateco.sdk.model.api.AkuratecoAction.GET_TRANS_STATUS] request.
         * @throws AkuratecoSdkIsNotInitializedException
         */
        val GET_TRANSACTION_STATUS: AkuratecoGetTransactionStatusAdapter = AkuratecoGetTransactionStatusAdapter
            get() {
                AkuratecoCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.akurateco.sdk.model.api.AkuratecoAction.GET_TRANS_DETAILS] request.
         * @throws AkuratecoSdkIsNotInitializedException
         */
        val GET_TRANSACTION_DETAILS: AkuratecoGetTransactionDetailsAdapter = AkuratecoGetTransactionDetailsAdapter
            get() {
                AkuratecoCredential.requireInit()
                return field
            }
    }
}
