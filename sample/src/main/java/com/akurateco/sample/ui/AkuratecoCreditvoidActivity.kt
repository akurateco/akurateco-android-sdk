/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sample.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.akurateco.sample.R
import com.akurateco.sample.app.AkuratecoTransactionStorage
import com.akurateco.sample.app.preattyPrint
import com.akurateco.sample.databinding.ActivityCaptureBinding
import com.akurateco.sample.databinding.ActivityCreditvoidBinding
import com.akurateco.sdk.core.AkuratecoSdk
import com.akurateco.sdk.model.response.base.error.AkuratecoError
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureCallback
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResponse
import com.akurateco.sdk.model.response.capture.AkuratecoCaptureResult
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidCallback
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidResponse
import com.akurateco.sdk.model.response.creditvoid.AkuratecoCreditvoidResult
import java.util.*

class AkuratecoCreditvoidActivity : AppCompatActivity(R.layout.activity_creditvoid) {

    private lateinit var binding: ActivityCreditvoidBinding
    private lateinit var akuratecoTransactionStorage: AkuratecoTransactionStorage

    private var selectedTransaction: AkuratecoTransactionStorage.Transaction? = null
    private var transactions: List<AkuratecoTransactionStorage.Transaction>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        akuratecoTransactionStorage = AkuratecoTransactionStorage(this)
        binding = ActivityCreditvoidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureView()
    }

    private fun configureView() {
        binding.btnLoadCreditvoid.setOnClickListener {
            transactions = akuratecoTransactionStorage.getCreditvoidTransactions()
            invalidateSpinner()
        }
        binding.btnLoadAll.setOnClickListener {
            transactions = akuratecoTransactionStorage.getAllTransactions()
            invalidateSpinner()
        }
        binding.btnCreditvoid.setOnClickListener {
            executeRequest()
        }

        invalidateSpinner()
    }

    private fun invalidateSpinner() {
        binding.spinnerTransactions.apply {
            val prettyTransactions = transactions
                .orEmpty()
                .map { it.toString() }
                .toMutableList()
                .apply {
                    add(0, "Select Transaction")
                }

            adapter = object : ArrayAdapter<String>(
                this@AkuratecoCreditvoidActivity,
                android.R.layout.simple_spinner_dropdown_item,
                prettyTransactions
            ) {
                override fun isEnabled(position: Int): Boolean {
                    return position != 0
                }

                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    return super.getDropDownView(position, convertView, parent).apply {
                        alpha = if (position == 0) 0.5F else 1.0F
                    }
                }
            }

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    parent?.get(0)?.alpha = if (position == 0) 0.5F else 1.0F

                    if (transactions.isNullOrEmpty()) {
                        invalidateSelectedTransaction()
                        return
                    }

                    selectedTransaction = if (position == 0) {
                        null
                    } else {
                        transactions?.get((position - 1).coerceAtLeast(0))
                    }

                    invalidateSelectedTransaction()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedTransaction = null
                    invalidateSelectedTransaction()
                }
            }

            invalidateSelectedTransaction()
        }

    }

    private fun invalidateSelectedTransaction() {
        binding.txtSelectedTransaction.text = selectedTransaction?.preattyPrint()
        binding.btnCreditvoid.isEnabled = selectedTransaction != null
    }

    private fun onRequestStart() {
        binding.progressBar.show()
        binding.txtResponse.text = ""
    }

    private fun onRequestFinish() {
        binding.progressBar.hide()
    }

    private fun executeRequest() {
        selectedTransaction?.let { selectedTransaction ->
            val amount = try {
                binding.etxtAmount.text.toString().toDouble()
            } catch (e: Exception) {
                0.00
            }

            val transaction = AkuratecoTransactionStorage.Transaction(
                payerEmail = selectedTransaction.payerEmail,
                cardNumber = selectedTransaction.cardNumber
            )

            onRequestStart()
            AkuratecoSdk.Adapter.CREDITVOID.execute(
                transactionId = selectedTransaction.id,
                payerEmail = selectedTransaction.payerEmail,
                cardNumber = selectedTransaction.cardNumber,
                amount = amount,
                callback = object : AkuratecoCreditvoidCallback {
                    override fun onResponse(response: AkuratecoCreditvoidResponse) {
                        super.onResponse(response)
                        onRequestFinish()
                        binding.txtResponse.text = response.preattyPrint()
                    }

                    override fun onResult(result: AkuratecoCreditvoidResult) {
                        transaction.fill(result.result)

                        akuratecoTransactionStorage.addTransaction(transaction)
                    }

                    override fun onError(error: AkuratecoError) = Unit

                    override fun onFailure(throwable: Throwable) {
                        super.onFailure(throwable)
                        onRequestFinish()
                        binding.txtResponse.text = throwable.preattyPrint()
                    }
                }
            )
        } ?: invalidateSelectedTransaction()
    }
}
