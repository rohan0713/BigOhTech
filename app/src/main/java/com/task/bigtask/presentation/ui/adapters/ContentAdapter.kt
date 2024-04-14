package com.task.bigtask.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.bigtask.R
import com.task.bigtask.data.models.ContentApi
import com.task.bigtask.data.models.ContentApiItem
import com.task.bigtask.databinding.PhotoItemBinding
import com.task.bigtask.presentation.ui.fragments.ImagesFragmentDirections

class ContentAdapter : PagingDataAdapter<ContentApiItem, ContentAdapter.ViewHolder>(COMPARATOR) {

    inner class ViewHolder(private val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContentApiItem) {

            binding.tvAuthor.text = item.author
            item.download_url.let {
                Glide.with(binding.root).load(item.download_url).placeholder(R.drawable.ic_loading).into(binding.ivContent)
                Glide.with(binding.root).load(item.download_url).placeholder(R.drawable.ic_loading).into(binding.ivProfile)
            }

            itemView.setOnClickListener {
                val action = ImagesFragmentDirections.actionImagesFragmentToDetailsFragment(item.id)
                binding.root.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ContentApiItem>(){
            override fun areItemsTheSame(oldItem: ContentApiItem, newItem: ContentApiItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ContentApiItem, newItem: ContentApiItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}