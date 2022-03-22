package com.nrk.movieshub.data.model


import com.google.gson.annotations.SerializedName

data class KeywordListResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("keywords")
    val keywords: List<Keyword>
)