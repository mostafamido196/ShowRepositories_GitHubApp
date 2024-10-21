package com.samy.domain.repository

import androidx.paging.PagingData
import com.samy.domain.model.GithubReposDomainModel
import com.samy.domain.model.RepoDetailsDomainModel
import com.samy.domain.model.RepoIssuesDomainModel
import kotlinx.coroutines.flow.Flow

interface GithubReposRepository {
    suspend fun fetchRepoList(): Flow<PagingData<GithubReposDomainModel>>
    suspend fun searchRepoList(searchKey: String): Flow<PagingData<GithubReposDomainModel>>
    suspend fun isFirstTime():  Flow<Boolean>
    suspend fun setFirstTime(bool: Boolean)
    suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel
    suspend fun fetchRepoIssues(ownerName: String, name: String): RepoIssuesDomainModel
}