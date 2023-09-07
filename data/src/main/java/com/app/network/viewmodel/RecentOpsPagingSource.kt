package com.app.network.viewmodel

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.responseModels.GetRecentOpsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.repository.HomeRepository


//class RecentOpsPagingSource(private val session: Session, private val repository: HomeRepository) :
//    PagingSource<Int, GetRecentOpsItem>() {
//
//    private val str = session[Keys.KEY_USER_DETAILS]
//    private val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)
//
//    override fun getRefreshKey(state: PagingState<Int, GetRecentOpsItem>): Int? {
//        return state.anchorPosition?.let { position ->
//            val page = state.closestPageToPosition(position)
//            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetRecentOpsItem> {
//        return try {
//
//            val page = params.key ?: 1
//            val response = repository.getRecentOps(session[Keys.KEY_TOKEN]!!, userDetails.customerNo, page)
//            LoadResult.Page(
//                data = response.body()!!,
//                prevKey = null,
//                nextKey = if (response.body()!!.isNotEmpty()) page + 1 else null
//            )
//
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}

