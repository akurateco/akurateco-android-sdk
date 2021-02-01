/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sample.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.akurateco.sample.R
import com.akurateco.sample.databinding.ActivityRedirect3dsBinding

class AkuratecoRedirect3dsActivity : AppCompatActivity(R.layout.activity_redirect_3ds) {

    private lateinit var binding: ActivityRedirect3dsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRedirect3dsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val termUrl3ds = intent.getStringExtra(TERM_URL_3DS_KEY)

        binding.webView.apply {
            settings.defaultTextEncodingName = "utf-8"

            webViewClient = object : WebViewClient() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    if (handleTermUrl3ds(request?.url?.path.orEmpty())) {
                        return false
                    }

                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (handleTermUrl3ds(url.orEmpty())) {
                        return false
                    }

                    return super.shouldOverrideUrlLoading(view, url)
                }

                private fun handleTermUrl3ds(url: String): Boolean {
                    if (url == termUrl3ds) {
                        setResult(Activity.RESULT_OK)
                        finish()

                        return true
                    }

                    return false
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    binding.progressBar.show()
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressBar.hide()
                    super.onPageFinished(view, url)
                }
            }

            binding.progressBar.show()
            loadUrl(intent.getStringExtra(TERM_URL_KEY).orEmpty())
        }
    }

    companion object {

        private const val TERM_URL_KEY = "TERM_URL_KEY"
        private const val TERM_URL_3DS_KEY = "TERM_URL_3DS_KEY"

        private const val REQUEST_CODE = 100

        fun open(context: Activity, termUrl: String, termUrl3ds: String) {
            context.startActivityForResult(
                Intent(context, AkuratecoRedirect3dsActivity::class.java).apply {
                    putExtra(TERM_URL_KEY, termUrl)
                    putExtra(TERM_URL_3DS_KEY, termUrl3ds)
                },
                REQUEST_CODE
            )
        }

        fun isOk(requestCode: Int, resultCode: Int) = requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK
    }
}
