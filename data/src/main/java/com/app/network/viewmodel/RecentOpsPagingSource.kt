package com.app.network.viewmodel

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.responseModels.GetRecentOpsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.repository.HomeRepository
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class RecentOpsPagingSource(private val session: Session, private val repository: HomeRepository) :
    PagingSource<Int, GetRecentOpsItem>() {

    private val str = session[Keys.KEY_USER_DETAILS]
    private val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    override fun getRefreshKey(state: PagingState<Int, GetRecentOpsItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetRecentOpsItem> =
        try {
            val page = params.key ?: 0
            val size = params.loadSize
            val from = page * size

            val data = repository.getRecentOps(session[Keys.KEY_TOKEN]!!, userDetails.customerNo, page)

            if (params.placeholdersEnabled) {

                val data = data.body() ?: emptyList()

                val itemsAfter = data.size - from + data.size
                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1,
                    itemsAfter = maxOf(0, minOf(itemsAfter, size)),
                    itemsBefore = from
                )
            } else {
                LoadResult.Page(
                    data = data.body()!!,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (data.body()!!.isEmpty()) null else page + 1
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }



//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetRecentOpsItem> =
//        try {
//            val page = params.key ?: 0
//            val size = params.loadSize
//            val from = page * size
//
//            val dataResponse = repository.getRecentOps(session[Keys.KEY_TOKEN]!!, userDetails.customerNo, page)
//
//            if (dataResponse.isSuccessful) {
////            if (params.placeholdersEnabled) {
//
//                Log.e("mTAG", "paging loaded")
//
//                val data = dataResponse.body() ?: emptyList()
//
//                val itemsAfter = data.size - from
//                val nextPage = if (itemsAfter > 0) page + 1 else null
//
//                LoadResult.Page(
//                    data = data,
//                    prevKey = if (page == 0) null else page - 1,
//                    nextKey = nextPage,
//                    itemsAfter = if (itemsAfter > size) size else itemsAfter,
//                    itemsBefore = from
//                )
//            } else {
//
//                Log.e("mTAG", "paging not loaded")
//                LoadResult.Error(Exception("API call failed"))
//            }
//        } catch (e: Exception) {
//            Log.e("mTAG", "paging error")
//            LoadResult.Error(e)
//        }

}

