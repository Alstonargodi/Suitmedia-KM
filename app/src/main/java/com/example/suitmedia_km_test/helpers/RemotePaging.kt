package com.example.suitmedia_km_test.helpers

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.suitmedia_km_test.data.remote.Data
import com.example.suitmedia_km_test.data.remote.service.ApiService

class RemotePaging(private val apiService: ApiService): PagingSource<Int,Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?:
            anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val position = params.key ?: initialIndex
            val responseData = apiService.getUser(position,params.loadSize)

            Log.d("paging","$position ${params.loadSize}")
            LoadResult.Page(
                data = responseData.data,
                prevKey = if (position == initialIndex) null else position - 1,
                nextKey = if (responseData.data.isNullOrEmpty()) null else position + 1
            )
        }catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

    private companion object{
        const val initialIndex = 0
    }
}