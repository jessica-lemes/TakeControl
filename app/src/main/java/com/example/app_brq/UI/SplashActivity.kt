package com.example.app_brq.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.app_brq.R

class SplashActivity : AppCompatActivity() {
    lateinit var clickSplash: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        carregarElemento()
        carregarEvento()
    }

    private fun carregarElemento() {
        clickSplash = findViewById(R.id.TelaCheia)
    }

    private fun carregarEvento() {
        clickSplash.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}