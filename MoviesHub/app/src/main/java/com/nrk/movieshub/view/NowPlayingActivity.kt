package com.nrk.movieshub.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nrk.movieshub.R
import com.nrk.movieshub.databinding.ActivityNowPlayingBinding
import com.nrk.movieshub.view.adapters.NowPlayingAdapter
import com.nrk.movieshub.view.common.LoaderStateAdapter
import com.nrk.movieshub.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NowPlayingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNowPlayingBinding
    val viewModel: NowPlayingViewModel by viewModels()
    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNowPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SetUi()
        GetNowPlayingMovies()
    }

    private fun GetNowPlayingMovies() {
        lifecycleScope.launchWhenCreated {
            viewModel.getAllDogs.collectLatest {response->
                binding.apply {
                    progressBar.isVisible = false
                    recyclerview.isVisible =  true
                }
                nowPlayingAdapter.submitData(response)
            }
        }
    }

    private fun SetUi() {
        SetupRecyclerView()
    }

    private fun SetupRecyclerView() {
        nowPlayingAdapter = NowPlayingAdapter()

        binding.apply {
            binding.recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@NowPlayingActivity)
                adapter = nowPlayingAdapter.withLoadStateHeaderAndFooter(
                    header = LoaderStateAdapter{nowPlayingAdapter::retry},
                    footer =  LoaderStateAdapter(nowPlayingAdapter::retry)
                )
            }
        }
    }
}