package com.xdust.auryxbrowser

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var addressBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        addressBar = findViewById(R.id.addressBar)

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://www.google.com")

        addressBar.setOnEditorActionListener { _, _, _ ->
            var url = addressBar.text.toString()
            if (!url.startsWith("http")) {
                url = "https://$url"
            }
            webView.loadUrl(url)
            true
        }

        findViewById<android.view.View>(R.id.btnBack).setOnClickListener {
            if (webView.canGoBack()) webView.goBack()
        }

        findViewById<android.view.View>(R.id.btnForward).setOnClickListener {
            if (webView.canGoForward()) webView.goForward()
        }

        findViewById<android.view.View>(R.id.btnRefresh).setOnClickListener {
            webView.reload()
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
