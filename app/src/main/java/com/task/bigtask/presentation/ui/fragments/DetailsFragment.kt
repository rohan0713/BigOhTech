package com.task.bigtask.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.task.bigtask.R
import com.task.bigtask.databinding.FragmentDetailsBinding
import com.task.bigtask.domain.repositories.ContentRepository
import com.task.bigtask.presentation.ui.viewmodels.ContentViewModel
import com.task.bigtask.presentation.ui.viewmodels.ViewModelProviderFactory

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args : DetailsFragmentArgs by navArgs()
    private lateinit var viewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        val id = args.photoID
        val repository = ContentRepository()
        val viewModelProvider = ViewModelProviderFactory(repository)

        viewModel = ViewModelProvider(this, viewModelProvider)[ContentViewModel::class.java]
        viewModel.getDetails(id)

        viewModel.details.observe(viewLifecycleOwner, Observer {
            it.let {
                Glide.with(binding.root).load(it.download_url).into(binding.ivContent)
                binding.tvAuthor.text = "Author : ${it.author}"
                binding.tvLink.text = it.url
            }
        })
        return binding.root
    }
}