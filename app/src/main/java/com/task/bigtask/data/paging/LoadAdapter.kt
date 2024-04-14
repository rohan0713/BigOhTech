package com.task.bigtask.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.bigtask.databinding.LoaderItemBinding

class LoadAdapter : LoadStateAdapter<LoadAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: LoaderItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(loadState: LoadState){
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = LoaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.Error || loadState is LoadState.NotLoading
    }
}