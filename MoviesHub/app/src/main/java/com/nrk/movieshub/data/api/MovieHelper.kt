package com.nrk.movieshub.data.api

import com.nrk.movieshub.data.model.GenresResponse
import dagger.Provides
import retrofit2.Response

interface MovieHelper {

    suspend fun getAllGenres(api_key: String) : Response<GenresResponse>
}