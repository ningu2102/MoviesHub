package com.nrk.movieshub.data.api.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nrk.movieshub.BuildConfig
import com.nrk.movieshub.data.api.MovieService
import com.nrk.movieshub.data.model.Movie
import com.nrk.movieshub.data.model.NowPlayingMovie
import retrofit2.HttpException
import java.io.IOException

class NowPlayingMoviePageSource constructor(
    private val movieService: MovieService
) : PagingSource<Int, NowPlayingMovie>(){
    private val DEFAULT_PAGE_INDEX= 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NowPlayingMovie> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = movieService.getNowPlaying(BuildConfig.TBDB_API_KEY,page)
            val nowPlayingMovies = response.body()!!.nowPlayingMovies
            LoadResult.Page(
                nowPlayingMovies,
                prevKey = if(page == DEFAULT_PAGE_INDEX) null else page-1,
                nextKey = if(nowPlayingMovies.isEmpty()) null else page+1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NowPlayingMovie>): Int? {
        return null
    }
}