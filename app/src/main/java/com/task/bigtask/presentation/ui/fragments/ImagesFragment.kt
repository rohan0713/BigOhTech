package com.task.bigtask.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.bigtask.data.paging.LoadAdapter
import com.task.bigtask.databinding.FragmentImagesBinding
import com.task.bigtask.domain.repositories.FeedRepository
import com.task.bigtask.presentation.ui.adapters.ContentAdapter
import com.task.bigtask.presentation.ui.viewmodels.ContentViewModel
import com.task.bigtask.presentation.ui.viewmodels.FeedViewModel
import com.task.bigtask.presentation.ui.viewmodels.ViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding

    private lateinit var viewModel: FeedViewModel
    private lateinit var adapter: ContentAdapter

    @Inject
    lateinit var repository: FeedRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        binding.rvImages.layoutManager = LinearLayoutManager(binding.root.context)

        val viewModelProvider = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProvider)[FeedViewModel::class.java]
        adapter = ContentAdapter()
        binding.rvImages.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadAdapter(),
            footer = LoadAdapter()
        )

        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
        return binding.root
    }
}