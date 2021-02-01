/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.model.request.card

/**
 * The test environment cards.
 * @see AkuratecoCard
 */
object AkuratecoTestCard {
    private const val TEST_CARD_NUMBER = "4111111111111111"
    private const val TEST_CARD_EXPIRE_YEAR = 2025
    private const val TEST_CARD_CVV = "411"

    /**
     * This card number and card expiration date must be used for testing successful sale.
     *
     * Response on successful SALE request: "action": "SALE", "result": "SUCCESS", "status": "SETTLED".
     * Response on successful AUTH request: "action": "SALE", "result": "SUCCESS", "status": "PENDING".
     */
    val SALE_SUCCESS = AkuratecoCard(TEST_CARD_NUMBER, 1, TEST_CARD_EXPIRE_YEAR, TEST_CARD_CVV)

    /**
     * This card number and card expiration date must be used for testing unsuccessful sale.
     *
     * Response on unsuccessful SALE request: "action": "SALE", "result": "DECLINED", "status": "DECLINED".
     * Response on unsuccessful AUTH request: "action": "SALE", "result": "DECLINED", "status": "DECLINED".
     */
    val SALE_FAILURE = AkuratecoCard(TEST_CARD_NUMBER, 2, TEST_CARD_EXPIRE_YEAR, TEST_CARD_CVV)

    /**
     * This card number and card expiration date must be used for testing unsuccessful CAPTURE after successful AUTH.
     *
     * Response on successful AUTH request: "action": "SALE", "result": "SUCCESS", "status": "PENDING".
     * Response on unsuccessful CAPTURE request: "action": "CAPTURE", "result": "DECLINED", "status": "PENDING".
     */
    val CAPTURE_FAILURE = AkuratecoCard(TEST_CARD_NUMBER, 3, TEST_CARD_EXPIRE_YEAR, TEST_CARD_CVV)

    /**
     * This card number and card expiration date must be used for testing  successful sale after 3DS verification.
     *
     * Response on VERIFY request: "action": "SALE", "result": "REDIRECT", "status": "3DS".
     * After return from ACS: "action": "SALE", "result": "SUCCESS", "status": "SETTLED".
     */
    val SECURE_3D_SUCCESS = AkuratecoCard(TEST_CARD_NUMBER, 5, TEST_CARD_EXPIRE_YEAR, TEST_CARD_CVV)

    /**
     * This card number and card expiration date must be used for testing unsuccessful sale after 3DS verification.
     *
     * Response on VERIFY request: "action": "SALE", "result": "REDIRECT", "status": "3DS".
     * After return from ACS: "action": "SALE", "result": "DECLINED", "status": "DECLINED".
     */
    val SECURE_3D_FAILURE = AkuratecoCard(TEST_CARD_NUMBER, 6, TEST_CARD_EXPIRE_YEAR, TEST_CARD_CVV)
}
