package com.nrk.movieshub.data.api
import com.nrk.movieshub.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/{movie_id}/keywords")
    suspend fun fetchKeywords(@Path("movie_id") id: Int): Response<KeywordListResponse>

    @GET("/3/movie/{movie_id}/videos")
    suspend fun fetchVideos(@Path("movie_id") id: Int): Response<VideoListResponse>

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun fetchReviews(@Path("movie_id") id: Int): Response<ReviewListResponse>

    @GET("3/movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlaying() : Response<NowPlayingMoviesResponse>

    @GET("/3/genre/movie/list?")
    suspend fun getAllGenres(@Query("api_key") api_key: String) : Response<GenresResponse>

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun getDiscoverMovie(@Query("page") page: Int,
                                 @Query("with_genres")genreId: Int)
            : Response<DiscoverMovieResponse>
}