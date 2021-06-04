package com.example.TakeControl.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.TakeControl.R

class SplashActivity : AppCompatActivity() {
    // Timer da splash screen
    private val timerSplash = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            Runnable
            /* * Exibindo splash com um timer. */
            { // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                val intent = Intent( this, LoginActivity::class.java)
                startActivity(intent)

                // Fecha esta activity
                finish()
            }, timerSplash.toLong()
        )
    }
}