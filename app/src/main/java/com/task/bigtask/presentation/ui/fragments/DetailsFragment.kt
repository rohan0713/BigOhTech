package com.task.bigtask.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.task.bigtask.databinding.FragmentDetailsBinding
import com.task.bigtask.domain.repositories.FeedRepository
import com.task.bigtask.presentation.ui.viewmodels.FeedViewModel
import com.task.bigtask.presentation.ui.viewmodels.ViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args : DetailsFragmentArgs by navArgs()
    private lateinit var viewModel: FeedViewModel

    @Inject
    lateinit var repository : FeedRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        val id = args.photoID
        binding.progressBar.animate()

        val viewModelProvider = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProvider)[FeedViewModel::class.java]
        viewModel.getDetails(id)

        viewModel.details.observe(viewLifecycleOwner, Observer {
            it.let {
                binding.progressBar.visibility = View.GONE
                binding.constraintLayout.visibility = View.VISIBLE
                Glide.with(binding.root).load(it.download_url).into(binding.ivContent)
                binding.tvAuthor.text = "Author : ${it.author}"
                binding.tvLink.text = it.url
            }
        })
        return binding.root
    }
}