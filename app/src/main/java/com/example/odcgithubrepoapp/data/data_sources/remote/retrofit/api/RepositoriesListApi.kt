package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api

import com.example.odcgithubrepoapp.data.Constants.Companion.GITHUB_REPOS_ENDPOINT
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesListApi {
    @GET(GITHUB_REPOS_ENDPOINT)
    suspend fun fetchRepositoriesList(): Response<GithubReposDataModel>


    @GET("search/repositories")
    suspend fun fetchRepositoriesWithPaging(
        @Query("q") language: String = "language",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
    ): Response<GithubReposDataModel>

    @GET("search/repositories")
    suspend fun fetchRepositories(
        @Query("q") language: String = "language",
    ): Response<GithubReposDataModel>
//    https://api.github.com/search/repositories?q=language&page=1&per_page=20
//    start for page 1 because page 0 = page 1
//    max result is 1000 repo -> max page is 15 page
}