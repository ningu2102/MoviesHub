package com.nrk.movieshub.view.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nrk.movieshub.data.model.NowPlayingMovie
import com.nrk.movieshub.databinding.HomeNowPlayingItemBinding
import com.nrk.movieshub.view.home.fragments.HomeFragment_GeneratedInjector

class NowPlayingAdapter() : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>(){

    private var nowPlayingMovies = ArrayList<NowPlayingMovie>()

    fun setValue(data: ArrayList<NowPlayingMovie>){
        nowPlayingMovies = data;
        notifyDataSetChanged()
    }
    class NowPlayingViewHolder(val binding: HomeNowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        return NowPlayingViewHolder(HomeNowPlayingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        var movie = nowPlayingMovies[position]
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.backdropPath)
            .into(holder.binding.ivPoster)

        holder.binding.txtMovieName.text = movie.originalTitle
        holder.binding.txtRating.text = movie.voteAverage.toString()
    }

    override fun getItemCount(): Int {
        return nowPlayingMovies.size
    }
}