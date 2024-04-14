package com.task.bigtask.domain.repositories

import com.task.bigtask.data.models.ContentApi
import com.task.bigtask.data.models.ContentApiItem
import com.task.bigtask.data.remote.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val api : ApiInterface
) : ApiInterface {

    override suspend fun getContent(): Response<ContentApi> {
        return api.getContent()
    }

    override suspend fun getDetails(id: String): Response<ContentApiItem> {
        return api.getDetails(id)
    }

}