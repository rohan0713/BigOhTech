package com.task.bigtask.data.remote

import com.task.bigtask.data.models.ContentApi
import com.task.bigtask.data.models.ContentApiItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("v2/list")
    suspend fun getContent() : Response<ContentApi>

    @GET("id/{id}/info")
    suspend fun getDetails(@Path("id") id : String) : Response<ContentApiItem>
}