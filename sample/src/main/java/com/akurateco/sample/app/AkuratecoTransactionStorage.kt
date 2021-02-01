/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sample.app

import android.content.Context
import androidx.core.content.edit
import com.akurateco.sdk.model.api.AkuratecoAction
import com.akurateco.sdk.model.api.AkuratecoResult
import com.akurateco.sdk.model.api.AkuratecoStatus
import com.akurateco.sdk.model.response.base.result.IAkuratecoResult
import com.google.gson.Gson
import java.util.*

internal class AkuratecoTransactionStorage(context: Context) {

    companion object {
        private const val AKURATECO_TRANSACTION_STORAGE = "AKURATECO_TRANSACTION_STORAGE"
    }

    private val storage = context.getSharedPreferences(AKURATECO_TRANSACTION_STORAGE, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun addTransaction(transaction: Transaction) {
        storage.edit {
            putString(
                UUID.randomUUID().toString(),
                gson.toJson(transaction)
            )
        }
    }

    fun getAllTransactions() = storage.all.map {
        gson.fromJson(it.value as String, Transaction::class.java)
    }

    fun getRecurringSaleTransactions() = getAllTransactions().filter {
        it.action == AkuratecoAction.SALE && it.recurringToken.isNotEmpty()
    }

    fun getCaptureTransactions() = getAllTransactions().filter {
        it.action == AkuratecoAction.SALE && it.isAuth
    }

    fun getCreditvoidTransactions() = getAllTransactions().filter {
        it.action == AkuratecoAction.SALE || it.action == AkuratecoAction.CAPTURE || it.isAuth
    }

    fun clearTransactions() = storage.edit {
        clear()
    }

    data class Transaction(
        val payerEmail: String,
        val cardNumber: String,
    ) {
        var id: String = ""
        var action: AkuratecoAction? = null
        var result: AkuratecoResult? = null
        var status: AkuratecoStatus? = null

        var recurringToken: String = ""
        var isAuth: Boolean = false

        fun fill(result: IAkuratecoResult) {
            id = result.transactionId
            action = result.action
            this.result = result.result
            status = result.status
        }
    }
}
