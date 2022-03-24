package com.nrk.movieshub.view.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nrk.movieshub.R
import com.nrk.movieshub.viewmodel.MainViewModel
import com.nrk.movieshub.databinding.ActivityMainBinding
import com.nrk.movieshub.view.home.fragments.HomeFragment
import com.nrk.movieshub.view.home.fragments.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

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

        showFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> showFragment(homeFragment)
                R.id.profile -> showFragment(profileFragment)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(binding.flFragment.id, fragment)
            commit()
        }
}