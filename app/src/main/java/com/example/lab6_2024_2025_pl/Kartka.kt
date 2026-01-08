package com.example.lab6_2024_2025_pl

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class Kartka : AppCompatActivity() {

    private var prezentyString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pobieramy dane przekazane z poprzedniej aktywności [cite: 4877-4881]
        prezentyString = intent.getStringExtra("LISTA_ZYCZEN") ?: "Brak prezentów"

        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true

        // Dodajemy interfejs JS
        webView.addJavascriptInterface(this, "Interfejs")

        webView.loadUrl("file:///android_asset/Kartka.html")
        setContentView(webView)
    }

    // Metoda wywoływana z JS przy załadowaniu strony
    @JavascriptInterface
    fun pobierzPrezenty(): String {
        return prezentyString
    }
}