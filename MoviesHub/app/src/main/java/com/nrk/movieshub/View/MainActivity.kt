package com.nrk.movieshub.View

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nrk.movieshub.utils.Status
import com.nrk.movieshub.ViewModel.MainViewModel
import com.nrk.movieshub.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(MainViewModel::class.java)
        viewModel.getAllGenres()
        setObservers()
    }

    private fun setObservers() {
        viewModel.allGenres.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val allGenres = it.data
                    allGenres!!.genres.forEach {
                        Log.d("MainActivity", it.name)
                    }
                }
                Status.LOADING -> {
                    Log.d("MainActivity", "Loading")

                }
                Status.ERROR -> {
                    Log.d("MainActivity", it.message.toString())

                }
            }
        })
    }
}