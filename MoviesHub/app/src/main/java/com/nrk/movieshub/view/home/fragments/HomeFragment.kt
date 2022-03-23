package com.nrk.movieshub.view.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nrk.movieshub.R
import com.nrk.movieshub.databinding.FragmentHomeBinding
import com.nrk.movieshub.utils.Status
import com.nrk.movieshub.view.home.MainActivity
import com.nrk.movieshub.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAllGenres()
        setObservers()
    }

    private fun setObservers() {
        viewModel.allGenres.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val allGenres = it.data
                    allGenres!!.genres.forEach {
                        Log.d("HomeFragment", it.name)
                    }
                }
                Status.LOADING -> {
                    Log.d("HomeFragment", "Loading")

                }
                Status.ERROR -> {
                    Log.d("HomeFragment", it.message.toString())

                }
            }
        })
    }
}