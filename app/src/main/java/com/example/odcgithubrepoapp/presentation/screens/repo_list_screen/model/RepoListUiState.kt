package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model

import androidx.paging.PagingData
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import kotlinx.coroutines.flow.Flow


sealed class RepoListUiState() {
    data object Idle : RepoListUiState()
    data object Loading : RepoListUiState()
    data class Success(val repoList: Flow<PagingData<GithubReposUiModel>>) : RepoListUiState()

    data class Error(val customRemoteExceptionUiModel: CustomRemoteExceptionUiModel = CustomRemoteExceptionUiModel.Unknown) :
        RepoListUiState()

}
/*
 data class Error(val customRemoteExceptionUiModel: CustomRemoteExceptionUiModel = CustomRemoteExceptionUiModel.Unknown) :
        RepoListUiState()

 */