package com.example.odcgithubrepoapp.data.repository

import RepoPagingSourceWithApiAndRoom
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.to_domain.toGithubReposDomainModel
import com.example.odcgithubrepoapp.data.mapper.to_domain.toRepoDetailsDomainModel
import com.example.odcgithubrepoapp.data.mapper.to_domain.toRepoIssuesDomainModel
import com.samy.domain.model.GithubReposDomainModel
import com.samy.domain.model.RepoDetailsDomainModel
import com.samy.domain.model.RepoIssuesDomainModel
import com.samy.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubReposRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource,
) : GithubReposRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun fetchRepoList(): Flow<PagingData<GithubReposDomainModel>> {
        Log.d("mos samy", "repo:fetchReposList:  ")
        return Pager(
            config = PagingConfig(
                pageSize = 6,  // The number of items loaded at once
                enablePlaceholders = false,  // Disable placeholders
                initialLoadSize = 6,  // The size of the first load
                prefetchDistance = 5  // Load more when the user scrolls within 5 items of the end
            ),
            remoteMediator = RepoPagingSourceWithApiAndRoom( // Use the RemoteMediator here
                localDataSource = githubLocalDataSource,
                remoteDataSource = githubRemoteDataSource
            ),
            pagingSourceFactory = {
                // Provide a PagingSource, not a Flow
                githubLocalDataSource.getRepoList() // This should return a PagingSource
            }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toGithubReposDomainModel() }
            }
    }


    override suspend fun searchRepoList(searchKey: String): Flow<PagingData<GithubReposDomainModel>> {
        val key = if (searchKey == "") "language" else searchKey
        Log.d("mos samy", "Repository: Searching with key: $key")
        return flowOf(PagingData.from(githubRemoteDataSource.searchRepositories(searchKey = searchKey).items.map { it.toGithubReposDomainModel() }))
    }
    /*return Pager(
        config = PagingConfig(
            pageSize = 30,  // Increased to GitHub's default
            enablePlaceholders = false,
            initialLoadSize = 30,
            prefetchDistance = 10
        ),
        pagingSourceFactory = {
            Log.d("mos samy", "Repository: Creating new PagingSource")
            SearchRepoListPagingSourceWithApi(
                searchKey = key,
                apiService = githubRemoteDataSource
            )
        }
    ).flow*/


    override suspend fun isFirstTime(): Flow<Boolean> {
        return githubLocalDataSource.isFirstTime()
    }

    override suspend fun setFirstTime(bool: Boolean) {
        return githubLocalDataSource.setFirstTime(bool)
    }

    override suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel {
        return githubRemoteDataSource.fetchRepoDetails(ownerName, name).toRepoDetailsDomainModel()
    }

    override suspend fun fetchRepoIssues(ownerName: String, name: String): RepoIssuesDomainModel {
        return githubRemoteDataSource.fetchRepoIssues(ownerName, name).toRepoIssuesDomainModel()
    }
}