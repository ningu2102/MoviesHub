package com.nrk.movieshub.data.api

import com.nrk.movieshub.data.model.GenresResponse
import retrofit2.Response
import javax.inject.Inject

class MovieHelperImpl @Inject constructor(private val apiService: MovieService) : MovieHelper {
    override suspend fun getAllGenres(api_key: String): Response<GenresResponse> = apiService.getAllGenres(api_key)


}