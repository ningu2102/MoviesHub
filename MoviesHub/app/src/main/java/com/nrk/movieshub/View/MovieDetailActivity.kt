package com.nrk.movieshub.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nrk.movieshub.R
import com.nrk.movieshub.databinding.ActivityMainBinding

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}