package com.example.lab6_2024_2025_pl

import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class Prezenty : AppCompatActivity() {

    // Lista prezentów (zgodnie z instrukcją ArrayList)
    private val listaPrezentow = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Konfiguracja WebView
        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true

        // Dodajemy interfejs JS pod nazwą "Interfejs"
        webView.addJavascriptInterface(this, "Interfejs")

        // Ładujemy plik z assets
        webView.loadUrl("file:///android_asset/Prezenty.html")

        setContentView(webView)
    }

    // Metoda wywoływana z JS: Interfejs.dodajDoListy("Rower")
    @JavascriptInterface
    fun dodajDoListy(prezent: String) {
        listaPrezentow.add(prezent)
    }

    // Metoda wywoływana z JS: Interfejs.idzDoKartki()
    @JavascriptInterface
    fun idzDoKartki() {
        val intent = Intent(this, Kartka::class.java)

        // Zamieniamy listę na jednego długiego Stringa HTML [cite: 4915]
        val prezentyHtml = listaPrezentow.joinToString(separator = "<br>")
        intent.putExtra("LISTA_ZYCZEN", prezentyHtml)

        startActivity(intent)
    }
}