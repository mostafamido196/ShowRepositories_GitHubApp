package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model

import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel

sealed class RepoIssuesUiState {
    data object InitialState: RepoIssuesUiState()
    data class RepoIssuesUiModelData(val repositoryIssues: RepoIssuesUiModel) : RepoIssuesUiState()
    data object Loading : RepoIssuesUiState()
    data class Error(val customErrorExceptionUiModel: CustomRemoteExceptionUiModel) : RepoIssuesUiState()
}

//sealed class DataState {
//    object Idle : DataState()
//    object Loading : DataState()
//    data class Result<T>(val data: T) : DataState()
//    data class Error(val msg: String) : DataState()
//}