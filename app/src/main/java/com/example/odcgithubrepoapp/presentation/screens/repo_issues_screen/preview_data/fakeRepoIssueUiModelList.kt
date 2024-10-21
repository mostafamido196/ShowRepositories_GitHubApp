package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.preview_data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssueItemUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssuesUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssuesUiState

val fakeRepoIssueUiModelList =
    RepoIssuesUiModel(
        listOf(
            RepoIssueItemUiModel(
                124,
                "2024-10-8, 8:00 Am",
                "Bump Bump Payarrow from 7 Payer Bump Payarrow from 7 Payer",
                "",
                "Open",
                "None"
            ),
            RepoIssueItemUiModel(
                125,
                "2024-10-8, 8:00 Am",
                "Bump Bump Payarrow from 7 Payer Bump Payarrow from 7 Payer",
                "",
                "Open",
                "None"
            ),
            RepoIssueItemUiModel(
                126,
                "2024-10-8, 8:00 Am",
                "Bump Bump Payarrow from 7 Payer Bump Payarrow from 7 Payer",
                "",
                "Open",
                "None"
            ),
            RepoIssueItemUiModel(
                127,
                "2024-10-8, 8:00 Am",
                "Bump Bump Payarrow from 7 Payer Bump Payarrow from 7 Payer",
                "",
                "Open",
                "None"
            ),
        )
    )

// preview multi state on same time
class IssueUiStatePreviewParameterProvider : PreviewParameterProvider<RepoIssuesUiState> {
    override val values = sequenceOf(
        RepoIssuesUiState.Loading,
        RepoIssuesUiState.RepoIssuesUiModelData(
            fakeRepoIssueUiModelList
        ),
        RepoIssuesUiState.Error(
            CustomRemoteExceptionUiModel.Unknown)
    )
}


// preview single state
val fakeRepoIssuesInitialUiState = RepoIssuesUiState.InitialState
val fakeRepoIssuesLoadingUiState = RepoIssuesUiState.Loading
val fakeRepoIssuesErrorUiState = RepoIssuesUiState.Error(CustomRemoteExceptionUiModel.Unknown)
val fakeRepoIssuesResultUiState = RepoIssuesUiState.RepoIssuesUiModelData(fakeRepoIssueUiModelList
)
