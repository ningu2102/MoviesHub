package com.nrk.movieshub.data.api

import com.nrk.movieshub.data.model.GenresResponse
import com.nrk.movieshub.data.model.NowPlayingMoviesResponse
import retrofit2.Response
import javax.inject.Inject

class MovieHelperImpl @Inject constructor(private val apiService: MovieService) : MovieHelper {
    override suspend fun getAllGenres(api_key: String): Response<GenresResponse> = apiService.getAllGenres(api_key)
    override suspend fun getNowPlayingMovies(api_key: String, page: Int): Response<NowPlayingMoviesResponse> = apiService.getNowPlaying(api_key, page)


}