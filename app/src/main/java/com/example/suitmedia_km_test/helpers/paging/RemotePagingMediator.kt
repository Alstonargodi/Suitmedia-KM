package com.example.suitmedia_km_test.helpers.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.suitmedia_km_test.data.local.database.LocalDatabase
import com.example.suitmedia_km_test.data.local.entity.mediator.RemoteKeys
import com.example.suitmedia_km_test.data.remote.Data
import com.example.suitmedia_km_test.data.remote.service.ApiService

@OptIn(ExperimentalPagingApi::class)
class RemotePagingMediator(
    private val database: LocalDatabase,
    private val apiService: ApiService
): RemoteMediator<Int,Data>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Data>): MediatorResult {
       val page = when(loadType){
           LoadType.REFRESH->{
               val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
               remoteKeys?.nextKey?.minus(1) ?: initialPage
           }

           LoadType.PREPEND -> {
               val remoteKeys = getRemoteKeyFirstItem(state)
               val prevKey = remoteKeys?.prevKey
                   ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
               prevKey
           }

           LoadType.APPEND -> {
               val remoteKeys = getRemoteKeyLastItem(state)
               val nextKey = remoteKeys?.nextKey
                   ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
               nextKey
           }
       }
        try {
            val data = apiService.getUser(
                page = page,
                state.config.pageSize
            ).data
            val endPage = data.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteDao().deleteRemoteKeys()
                    database.remoteDao().deleteUsersList()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endPage) null else page + 1
                val keys = data.map {
                    RemoteKeys(
                        id = it.id.toString(),
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                database.remoteDao().insertRemoteKeys(keys)
                database.remoteDao().insertUsersList(data)
            }
            return MediatorResult.Success(endOfPaginationReached = endPage)
        }catch (e : Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyLastItem(state: PagingState<Int, Data>): RemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.remoteDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyFirstItem(state: PagingState<Int, Data>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.remoteDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int,  Data>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteDao().getRemoteKeysId(id)
            }
        }
    }

    companion object{
        const val initialPage = 1
    }

}