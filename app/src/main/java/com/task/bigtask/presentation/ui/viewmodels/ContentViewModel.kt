package com.task.bigtask.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.bigtask.data.models.ContentApi
import com.task.bigtask.data.models.ContentApiItem
import com.task.bigtask.domain.repositories.ContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentViewModel(
    private val repository: ContentRepository
) : ViewModel() {

    private val _photos : MutableLiveData<ContentApi> = MutableLiveData()
    val photos : LiveData<ContentApi> = _photos

    private val _details : MutableLiveData<ContentApiItem> = MutableLiveData()
    val details : LiveData<ContentApiItem> = _details

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getContent()
            if(response.isSuccessful){
                response.body().let {
                    _photos.postValue(it)
                }
            }
        }
    }

    fun getDetails(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getDetails(id)
            if(response.isSuccessful){
                response.body().let {
                    _details.postValue(it)
                }
            }
        }
    }
}