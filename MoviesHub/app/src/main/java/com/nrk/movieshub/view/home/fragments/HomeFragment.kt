package com.nrk.movieshub.view.home.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.nrk.movieshub.data.model.NowPlayingMovie
import com.nrk.movieshub.databinding.FragmentHomeBinding
import com.nrk.movieshub.utils.Status
import com.nrk.movieshub.view.MainActivity
import com.nrk.movieshub.view.NowPlayingActivity
import com.nrk.movieshub.view.home.adapters.NowPlayingAdapter
import com.nrk.movieshub.view.utils.CarouselLayoutManager
import com.nrk.movieshub.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private lateinit var snapHelper: SnapHelper

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
        SetUi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (viewModel.checkInternetConnection()) {
            viewModel.getNowPlayingMovies()
            setObservers()
        }
    }

    private fun SetUi() {
        SetClicks()
        SetupRecyclerViews()
    }

    private fun SetClicks(){
        binding.txtSeeAllNowPlaying.setOnClickListener({
            startActivity(Intent(activity, NowPlayingActivity::class.java))
        })
    }

    private fun SetupRecyclerViews() {
        SetupNowPlayingRecyclerView()
    }

    private fun SetupNowPlayingRecyclerView() {
        layoutManager = CarouselLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNowPlaying.layoutManager = layoutManager

        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvNowPlaying)

        nowPlayingAdapter = NowPlayingAdapter()
        binding.rvNowPlaying.adapter = nowPlayingAdapter
    }

    private fun setObservers() {
        setAllGenreObserver()
        setNowPlayingObserver()
    }

    private fun setNowPlayingObserver() {
        viewModel.nowPlayingMovies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    var nowPlayingMovies = it.data!!.nowPlayingMovies
                    nowPlayingAdapter.setValue(data = nowPlayingMovies as ArrayList<NowPlayingMovie>)
                }
            }
        })
    }

    private fun setAllGenreObserver() {
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