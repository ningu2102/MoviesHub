package com.nrk.movieshub.view.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nrk.movieshub.R
import com.nrk.movieshub.utils.Status
import com.nrk.movieshub.viewmodel.MainViewModel
import com.nrk.movieshub.databinding.ActivityMainBinding
import com.nrk.movieshub.view.home.fragments.HomeFragment
import com.nrk.movieshub.view.home.fragments.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import okio.blackholeSink

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragments()
    }

    private fun setupFragments() {
        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()

        setDefaultFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> setDefaultFragment(homeFragment)
                R.id.profile -> setDefaultFragment(profileFragment)
            }
            true
        }
    }

    private fun setDefaultFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flFragment.id, fragment)
            commit()
        }
}