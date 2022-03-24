package com.nrk.movieshub.data.api

import com.nrk.movieshub.data.model.GenresResponse
import com.nrk.movieshub.data.model.NowPlayingMoviesResponse
import dagger.Provides
import retrofit2.Response

interface MovieHelper {

    suspend fun getAllGenres(api_key: String) : Response<GenresResponse>

    suspend fun getNowPlayingMovies(api_key: String) : Response<NowPlayingMoviesResponse>
}