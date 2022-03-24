package com.nrk.movieshub.view.common
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nrk.movieshub.databinding.RetryViewBinding

class LoaderStateAdapter constructor(private val retry:()->Unit): LoadStateAdapter<LoaderStateAdapter.LoaderStateViewHolder>() {

    override fun onBindViewHolder(holder: LoaderStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoaderStateViewHolder {
        return LoaderStateViewHolder(RetryViewBinding.inflate(LayoutInflater.from(parent.context),parent,false),retry)
    }

    class LoaderStateViewHolder(private val binding: RetryViewBinding,retry: () -> Unit)
        : RecyclerView.ViewHolder(binding.root){

        init {

            binding.retryBtn.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryBtn.isVisible = loadState !is LoadState.Loading
                errorTxt.isVisible = loadState !is LoadState.Loading
            }
        }
    }

}