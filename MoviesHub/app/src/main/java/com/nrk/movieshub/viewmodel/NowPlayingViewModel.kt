package com.nrk.movieshub.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nrk.movieshub.data.api.MovieService
import com.nrk.movieshub.data.api.pagesource.NowPlayingMoviePageSource
import com.nrk.movieshub.data.model.NowPlayingMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NowPlayingViewModel @ViewModelInject constructor(private val movieService: MovieService) : ViewModel()
{
    val getAllDogs: Flow<PagingData<NowPlayingMovie>> = Pager(config = PagingConfig(20,enablePlaceholders = false)){
        NowPlayingMoviePageSource(movieService)
    }.flow.cachedIn(viewModelScope)
}