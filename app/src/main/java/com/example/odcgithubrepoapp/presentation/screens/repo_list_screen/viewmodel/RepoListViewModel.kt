package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.example.odcgithubrepoapp.presentation.mapper.toGithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val githubReposListUseCase: com.samy.domain.usecase.FetchGithubReposListUseCase,
    private val searchGithubReposListUseCase: com.samy.domain.usecase.SearchGithubReposListUseCase,
) : ViewModel() {
    private val _reposStateFlow =
        MutableStateFlow<RepoListUiState>(RepoListUiState.Idle)
    val reposStateFlow: StateFlow<RepoListUiState> = _reposStateFlow

    var searchKey = MutableStateFlow("")
    private var currentSearchJob: Job? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("mos samy", "VM:coroutineExceptionHandler: $throwable ")
    }

    fun requestGithubRepoList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _reposStateFlow.value = RepoListUiState.Loading
            try {
                val repoListFlow =
                    if (searchKey.value.isEmpty()) {
                        githubReposListUseCase()
                    } else {
                        searchGithubReposListUseCase(searchKey.value)
                    }

                _reposStateFlow.value = RepoListUiState.Success(repoListFlow.map {
                    it.map { it.toGithubReposUiModel() }
                })
                Log.d("mos samy", "Fetched data: ${repoListFlow.firstOrNull()}")
            } catch (e: Exception) {
                _reposStateFlow.value = RepoListUiState.Error()
            }
        }
    }

    fun search(key: String) {
        searchKey.value = key

        // Cancel any previous search job
        currentSearchJob?.cancel()

        currentSearchJob = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            delay(1000L) // Delay to debounce user input
            requestGithubRepoList()
        }
    }
}
