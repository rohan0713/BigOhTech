package com.task.bigtask.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.bigtask.domain.repositories.ContentRepository

class ViewModelProviderFactory(
    private val repository: ContentRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContentViewModel(repository) as T
    }
}