package com.task.bigtask.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.bigtask.domain.repositories.FeedRepository

class ViewModelProviderFactory(
    private val repository: FeedRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeedViewModel(repository) as T
    }
}