package com.nrk.movieshub.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nrk.movieshub.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_screen)

        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}