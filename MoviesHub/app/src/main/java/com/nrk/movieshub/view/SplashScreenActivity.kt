package com.nrk.movieshub.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_screen)

        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}