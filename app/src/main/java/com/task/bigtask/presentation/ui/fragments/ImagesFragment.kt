package com.task.bigtask.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.bigtask.databinding.FragmentImagesBinding
import com.task.bigtask.domain.repositories.ContentRepository
import com.task.bigtask.presentation.ui.adapters.ContentAdapter
import com.task.bigtask.presentation.ui.viewmodels.ContentViewModel
import com.task.bigtask.presentation.ui.viewmodels.ViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private lateinit var viewModel: ContentViewModel

    @Inject
    lateinit var repository: ContentRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        binding.rvImages.layoutManager = LinearLayoutManager(binding.root.context)

        val viewModelProvider = ViewModelProviderFactory(repository)

        viewModel = ViewModelProvider(this, viewModelProvider)[ContentViewModel::class.java]

        viewModel.photos.observe(viewLifecycleOwner, Observer {
            binding.rvImages.adapter = ContentAdapter(it)
        })

        return binding.root
    }
}