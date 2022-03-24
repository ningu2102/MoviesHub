package com.nrk.movieshub.data.repository

import com.nrk.movieshub.data.api.MovieHelper
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieHelper: MovieHelper) {
    suspend fun getAllGenres(apikey: String) =  movieHelper.getAllGenres(apikey)

    suspend fun getNowPlayingMovies(apikey: String, page: Int) = movieHelper.getNowPlayingMovies(apikey, page)
}