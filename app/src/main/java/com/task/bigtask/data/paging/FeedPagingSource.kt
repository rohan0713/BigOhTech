package com.task.bigtask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.bigtask.data.models.ContentApi
import com.task.bigtask.data.models.ContentApiItem
import com.task.bigtask.data.remote.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedPagingSource @Inject constructor(
    private val api : ApiInterface
) : PagingSource<Int, ContentApiItem>() {

    override fun getRefreshKey(state: PagingState<Int, ContentApiItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContentApiItem> {
        return try {
            val position = params.key ?: 1
            val response = withContext(Dispatchers.IO) {
                api.getContent(position)
            }
            if(response.isSuccessful){
                LoadResult.Page(
                    data = response.body() ?: emptyList(),
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = position + 1
                )
            }else{
                LoadResult.Error(Exception("API call failed"))
            }
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}