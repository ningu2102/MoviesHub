package com.nrk.movieshub.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nrk.movieshub.BuildConfig
import com.nrk.movieshub.data.model.GenresResponse
import com.nrk.movieshub.data.model.NowPlayingMoviesResponse
import com.nrk.movieshub.data.repository.MovieRepository
import com.nrk.movieshub.utils.NetworkHelper
import com.nrk.movieshub.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    init {
        //checkInternetConnection()
    }

    fun checkInternetConnection() = networkHelper.isNetworkConnected()

    private val _allGenres = MutableLiveData<Resource<GenresResponse>>()
    val allGenres: LiveData<Resource<GenresResponse>>
        get() = _allGenres

    private val _nowPlayingMovies = MutableLiveData<Resource<NowPlayingMoviesResponse>>()
    val nowPlayingMovies: LiveData<Resource<NowPlayingMoviesResponse>>
        get() = _nowPlayingMovies

    fun getAllGenres() {
        viewModelScope.launch {
            _allGenres.value = Resource.loading(null)
            if (networkHelper.isNetworkConnected()) {
                movieRepository.getAllGenres(BuildConfig.TBDB_API_KEY).let {
                    if (it.isSuccessful) {
                        val allGenres = Resource.success(it.body())
                        allGenres.data!!.genres.forEach {
                            Log.d("MainViewModel", it.name)
                        }
                        _allGenres.value = Resource.success(it.body())

                    } else {
                        _allGenres.value = Resource.error(it.errorBody().toString(), null)
                        Log.d("MainViewModel", it.errorBody().toString())

                    }
                }
            } else {
                _allGenres.value = Resource.error("No internet connection", null)
                Log.d("MainViewModel", "No internet connection")
            }
        }
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            _nowPlayingMovies.value = Resource.loading(null)
            if (networkHelper.isNetworkConnected()) {
                movieRepository.getNowPlayingMovies(BuildConfig.TBDB_API_KEY).let {
                    if (it.isSuccessful) {
                        val allGenres = Resource.success(it.body())
                        allGenres.data!!.nowPlayingMovies.forEach {
                            Log.d("MainViewModelNowPlaying", it.originalTitle)
                        }
                        _nowPlayingMovies.value = Resource.success(it.body())

                    } else {
                        _nowPlayingMovies.value =
                            Resource.error(it.errorBody().toString(), null)
                        Log.d("MainViewModel", it.errorBody().toString())

                    }
                }
            } else {
                _allGenres.value = Resource.error("No internet connection", null)
                Log.d("MainViewModel", "No internet connection")
            }
        }
    }
}
