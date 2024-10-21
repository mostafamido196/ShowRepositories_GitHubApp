package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.GithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoListUiState
import kotlinx.coroutines.flow.flowOf

val fakeRepoUiModelList = listOf(
    GithubReposUiModel(
        id = 1,
        name = "KotlinCoroutines",
        avatarUrl = "https://avatars.githubusercontent.com/u/1485041?v=4",
        description = "Multiplatform coroutines for Kotlin",
        starsCount = "10000",
        ownerName = "JetBrains"
    ),
    GithubReposUiModel(
        id = 2,
        name = "Compose",
        avatarUrl = "https://avatars.githubusercontent.com/u/281053?v=4",
        description = "Declarative UI toolkit for Android",
        starsCount = "8000",
        ownerName = "JetBrains"
    ),
    GithubReposUiModel(
        id = 3,
        name = "Retrofit",
        avatarUrl = "https://avatars.githubusercontent.com/u/1485041?v=4",
        description = "Type-safe HTTP client for Android and Java",
        starsCount = "6000",
        ownerName = "Square"
    ),
    GithubReposUiModel(
        id = 4,
        name = "OkHttp",
        avatarUrl = "https://avatars.githubusercontent.com/u/1485041?v=4",
        description = "Efficient HTTP client for Android and Java",
        starsCount = "5000",
        ownerName = "Square"
    ),
    GithubReposUiModel(
        id = 5,
        name = "Room",
        avatarUrl = "https://avatars.githubusercontent.com/u/1485041?v=4",
        description = "SQLite database access object",
        starsCount = "4000",
        ownerName = "Google"
    )
)


// preview single state
 val fakeRepoListUiState = RepoListUiState.Success(flowOf(PagingData.from(fakeRepoUiModelList)) )
val fakeRepoListLoadingUiState = RepoListUiState.Loading
val fakeRepoListIdleUiState = RepoListUiState.Idle
val fakeRepoListErrorUiState = RepoListUiState.Error(
    customRemoteExceptionUiModel = CustomRemoteExceptionUiModel.NoInternetConnection
)

// preview multi state on same time
class RepoListUiStatePreviewParameterProvider : PreviewParameterProvider<RepoListUiState> {
    override val values = sequenceOf(
        fakeRepoListUiState,
        fakeRepoListIdleUiState,
        fakeRepoListLoadingUiState,
        fakeRepoListErrorUiState
    )
}
