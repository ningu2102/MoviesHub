package com.nrk.movieshub.data.model


import com.google.gson.annotations.SerializedName

data class VideoListResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val videos: List<Video>
)