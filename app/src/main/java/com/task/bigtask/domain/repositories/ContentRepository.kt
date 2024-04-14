package com.task.bigtask.domain.repositories

import com.task.bigtask.data.models.ContentApi
import com.task.bigtask.data.models.ContentApiItem
import com.task.bigtask.data.remote.ApiInterface
import com.task.bigtask.data.remote.RetrofitClient
import retrofit2.Response

class ContentRepository : ApiInterface {

    override suspend fun getContent(): Response<ContentApi> {
        return RetrofitClient.api.getContent()
    }

    override suspend fun getDetails(id: String): Response<ContentApiItem> {
        return RetrofitClient.api.getDetails(id)
    }

}