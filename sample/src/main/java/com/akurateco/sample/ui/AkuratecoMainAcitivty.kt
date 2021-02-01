/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.akurateco.sample.R
import com.akurateco.sample.databinding.ActivityMainBinding

class AkuratecoMainAcitivty : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureView()
    }

    private fun configureView() {
        binding.btnSale.setOnClickListener {
            startActivity(Intent(this, AkuratecoSaleActivity::class.java))
        }
        binding.btnRecurringSale.setOnClickListener {
            startActivity(Intent(this, AkuratecoRecurringSaleActivity::class.java))
        }
        binding.btnCapture.setOnClickListener {
            startActivity(Intent(this, AkuratecoCaptureActivity::class.java))
        }
        binding.btnCreditVoid.setOnClickListener {
            startActivity(Intent(this, AkuratecoCreditvoidActivity::class.java))
        }
        binding.btnGetTransStatus.setOnClickListener {
            startActivity(Intent(this, AkuratecoGetTransStatusActivity::class.java))
        }
        binding.btnGetTransDetails.setOnClickListener {
            startActivity(Intent(this, AkuratecoGetTransDetailsActivity::class.java))
        }
    }
}
