package com.nrk.movieshub.data.model


import com.google.gson.annotations.SerializedName

data class NowPlayingMoviesResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val nowPlayingMovies: List<NowPlayingMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)