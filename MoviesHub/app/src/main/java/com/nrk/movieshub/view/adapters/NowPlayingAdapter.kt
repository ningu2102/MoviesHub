package com.nrk.movieshub.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nrk.movieshub.data.model.NowPlayingMovie
import com.nrk.movieshub.databinding.NowPlayingItemBinding
import javax.inject.Inject

class NowPlayingAdapter @Inject constructor(): PagingDataAdapter<NowPlayingMovie,NowPlayingAdapter.NowPlayingViewHolder>(Diff()) {


    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        val nowPlayingMovie = getItem(position)
        if(nowPlayingMovie!= null){
            holder.binds(nowPlayingMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder  =
        NowPlayingViewHolder(NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    class NowPlayingViewHolder(private val binding:NowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun binds(nowPlayingMovie:NowPlayingMovie){

            binding.apply {
                Glide.with(this@NowPlayingViewHolder.itemView)
                    .load("https://image.tmdb.org/t/p/w500/" + nowPlayingMovie.backdropPath)
                    .into(this@NowPlayingViewHolder.binding.ivPoster)

                this@NowPlayingViewHolder.binding.txtTitle.text = nowPlayingMovie.title
                this@NowPlayingViewHolder.binding.txtRating.text = nowPlayingMovie.voteAverage.toString()

            }
        }
    }

    class Diff : DiffUtil.ItemCallback<NowPlayingMovie>(){
        override fun areItemsTheSame(oldItem: NowPlayingMovie, newItem: NowPlayingMovie): Boolean  =
            oldItem.backdropPath == newItem.backdropPath

        override fun areContentsTheSame(oldItem: NowPlayingMovie, newItem: NowPlayingMovie): Boolean =
            oldItem == newItem
    }
}