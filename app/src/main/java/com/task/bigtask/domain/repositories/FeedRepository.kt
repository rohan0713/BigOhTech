package com.task.bigtask.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.task.bigtask.data.models.ContentApi
import com.task.bigtask.data.models.ContentApiItem
import com.task.bigtask.data.paging.FeedPagingSource
import com.task.bigtask.data.remote.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class FeedRepository @Inject constructor (
    private val api : ApiInterface
) : ApiInterface{

    fun getPhotos() = Pager(
        config = PagingConfig(30, 10),
        pagingSourceFactory = { FeedPagingSource(api) }
    ).liveData

    override suspend fun getContent(page: Int): Response<ContentApi> {
        return api.getContent(page)
    }

    override suspend fun getDetails(id: String): Response<ContentApiItem> {
        return api.getDetails(id)
    }
}