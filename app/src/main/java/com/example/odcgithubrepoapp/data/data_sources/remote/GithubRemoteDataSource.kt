package com.example.odcgithubrepoapp.data.data_sources.remote

import android.util.Log
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepoDetailsApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepoIssuesApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api.RepositoriesListApi
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issue_list.RepoIssuesResponse
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_details.RepoDetailsDataModel
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
import com.example.odcgithubrepoapp.data.mapper.toCustomRemoteExceptionDomainModel
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val repositoryListApi: RepositoriesListApi,
    private val repositoryDetailsApi: RepoDetailsApi,
    private val repositoryIssueApi: RepoIssuesApi,
) {


    suspend fun searchRepositoriesWithPaging(searchKey:String,page: Int,pageSize:Int): GithubReposDataModel {
        try {
            val result =repositoryListApi.fetchRepositoriesWithPaging(language = searchKey, page = page, perPage = pageSize)
                .body() as GithubReposDataModel
            Log.d("mos samy", "api:searchRepositoriesWithPaging:page $page ")
            Log.d("mos samy", "api:searchRepositoriesWithPaging:size ${result.items.size} ")
            return result
        } catch (e: Exception) {
            Log.e("mos samy", "api:searchRepositoriesWithPaging:e ${e.message} ")
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun searchRepositories(searchKey:String): GithubReposDataModel {
        try {
            val result =repositoryListApi.fetchRepositories(language = searchKey)
                .body() as GithubReposDataModel
            Log.d("mos samy", "api:searchRepositories:result ${result} ")
            return result
        } catch (e: Exception) {
            Log.e("mos samy", "api:searchRepositories:e ${e.message} ")
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun fetchRepositoriesWithPaging(page: Int,pageSize:Int): GithubReposDataModel {
        try {
            val result =repositoryListApi.fetchRepositoriesWithPaging(page = page, perPage = pageSize)
                .body() as GithubReposDataModel
            Log.d("mos samy", "api:fetchRepositoriesWithPaging:page $page ")
            Log.d("mos samy", "api:fetchRepositoriesWithPaging:size ${result.items.size} ")
            return result
        } catch (e: Exception) {
            Log.e("mos samy", "api:fetchRepositoriesWithPaging:e ${e.message} ")
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDataModel {
        try {
            return repositoryDetailsApi.fetchRepoDetails(ownerName, name)
                .body() as RepoDetailsDataModel
        } catch (e: Exception) {
            // Convert and rethrow the exception as a custom remote exception
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }

    suspend fun fetchRepoIssues(ownerName: String, name: String): RepoIssuesResponse {
        try {
            return repositoryIssueApi.fetchRepoIssues(ownerName, name).body() as RepoIssuesResponse
        } catch (e: Exception) {
            // Convert and rethrow the exception as a custom remote exception
            throw e.toCustomRemoteExceptionDomainModel()
        }
    }
}