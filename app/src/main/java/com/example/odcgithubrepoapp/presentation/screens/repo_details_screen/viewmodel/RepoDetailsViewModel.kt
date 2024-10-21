package com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.presentation.mapper.toCustomExceptionRemoteUiModel
import com.example.odcgithubrepoapp.presentation.mapper.toRepoDetailsUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.model.RepoDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val fetchRepositoryDetailsUseCase: com.samy.domain.usecase.FetchRepositoryDetailsUseCase,
) : ViewModel() {
    private val _uiSate =
        MutableStateFlow<RepoDetailsUiState>(
            RepoDetailsUiState.InitialState
        )
    val uiState: StateFlow<RepoDetailsUiState> = _uiSate.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiSate.value = RepoDetailsUiState.Loading(isLoading = false)
        _uiSate.value = RepoDetailsUiState.Error(
            (throwable as com.samy.domain.model.CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
        )
    }

    fun requestRepoDetails(
        ownerName: String, name: String,
    ) {
        _uiSate.value = RepoDetailsUiState.Loading(isLoading = true)
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val repoDetails = fetchRepositoryDetailsUseCase(ownerName, name)
                _uiSate.value = RepoDetailsUiState.Loading(isLoading = false)
                _uiSate.value =
                    RepoDetailsUiState.RepoDetailsUiModelData(repoDetails.toRepoDetailsUiModel())
            } catch (e: Exception) {
                _uiSate.value = RepoDetailsUiState.Loading(isLoading = false)
                _uiSate.value = RepoDetailsUiState.Error(
                    (e as com.samy.domain.model.CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
                )

            }
        }
    }
}