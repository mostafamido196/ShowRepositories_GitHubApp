package com.example.odcgithubrepoapp.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toCustomRemoteExceptionDomainModel
import com.example.odcgithubrepoapp.data.mapper.to_domain.toGithubReposDomainModel
import com.samy.domain.model.GithubReposDomainModel

class SearchRepoListPagingSourceWithApi(
    private val apiService: GithubRemoteDataSource,
    private val searchKey: String
) : PagingSource<Int, GithubReposDomainModel>() {

    init {
        Log.d("mos samy", "PagingSource: Initialized with key: $searchKey")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubReposDomainModel> {
        val currentPage = params.key ?: 1
        Log.d("mos samy", "PagingSource: load() called for page: $currentPage")
        Log.d("mos samy", "PagingSource: loadSize: ${params.loadSize}")

        return try {
            val response = apiService.searchRepositoriesWithPaging(
                searchKey = searchKey,
                page = currentPage,
                pageSize = params.loadSize
            )

            Log.d("mos samy", "PagingSource: API response received")
            Log.d("mos samy", "PagingSource: Items count: ${response.items.size}")

            LoadResult.Page(
                data = response.items.map {
                    Log.d("mos samy", "PagingSource: Mapping item: ${it.name}")
                    it.toGithubReposDomainModel()
                },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.items.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.e("mos samy", "PagingSource: Error loading data", e)
            LoadResult.Error(e.toCustomRemoteExceptionDomainModel())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubReposDomainModel>): Int? {
        Log.d("mos samy", "PagingSource: getRefreshKey called")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}