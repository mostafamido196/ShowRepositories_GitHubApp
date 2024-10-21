package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samy.domain.model.CustomRemoteExceptionDomainModel
import com.samy.domain.usecase.FetchRepoIssuesUseCase
import com.example.odcgithubrepoapp.presentation.mapper.toCustomExceptionRemoteUiModel
import com.example.odcgithubrepoapp.presentation.mapper.toGithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssuesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoIssuesViewModel  @Inject constructor(
    private val fetchRepositoryIssuesUseCase: com.samy.domain.usecase.FetchRepoIssuesUseCase
) : ViewModel() {
    private val _repoIssuesStateFlow =
        MutableStateFlow<RepoIssuesUiState>(
            RepoIssuesUiState.InitialState)
    val repoIssuesStateFlow: StateFlow<RepoIssuesUiState> = _repoIssuesStateFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _repoIssuesStateFlow.value = RepoIssuesUiState.Loading
        _repoIssuesStateFlow.value = RepoIssuesUiState.Error(
            (throwable as com.samy.domain.model.CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
        )
    }

    fun requestRepoIssues(
        ownerName: String, name: String
    ) {
        _repoIssuesStateFlow.value = RepoIssuesUiState.Loading
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val repoIssues = fetchRepositoryIssuesUseCase(ownerName, name)
                _repoIssuesStateFlow.value = RepoIssuesUiState.RepoIssuesUiModelData(repoIssues.toGithubReposUiModel())
            } catch (e: Exception) {
                _repoIssuesStateFlow.value = RepoIssuesUiState.Error(
                    (e as com.samy.domain.model.CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
                )

            }
        }
    }
}