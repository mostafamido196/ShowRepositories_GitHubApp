package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.common_component.shimmer.repo_list.AnimateShimmerRepoList
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.components.IssueItem
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssuesUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssuesUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.preview_data.IssueUiStatePreviewParameterProvider
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.viewmodel.RepoIssuesViewModel


@Composable
fun RepoIssuesScreen(
    owner: String,
    name: String,
    onShowIssueItemClicked: (String) -> Unit,
    onClickBack: () -> Unit,
) {
    val repoIssuesViewModel: RepoIssuesViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        repoIssuesViewModel.requestRepoIssues(ownerName = owner, name = name)
    }
    val repoDetailsUiState by repoIssuesViewModel.repoIssuesStateFlow.collectAsStateWithLifecycle()

    CompositionLocalProvider(LocalOnShowIssueItemClicked provides onShowIssueItemClicked) {
        RepoIssuesContent(
            repoDetailsUiState = repoDetailsUiState,
            onClickBack = onClickBack,
            onRefreshButtonClicked = {
                repoIssuesViewModel.requestRepoIssues(ownerName = owner, name = name)
            }
        )    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoIssuesContent(
    modifier: Modifier = Modifier,
    repoDetailsUiState: RepoIssuesUiState,
    onClickBack: () -> Unit,
    onRefreshButtonClicked: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            AppBar(
                title = R.string.issues_app_bar_title,
                showBackButton = true,
                onBackButtonClicked = onClickBack
            )
        }
    ) { innerPadding ->
        when (repoDetailsUiState) {
            is RepoIssuesUiState.InitialState -> {}

            is RepoIssuesUiState.Loading -> {
                AnimateShimmerRepoList(
                    innerPadding = innerPadding
                )
            }

            is RepoIssuesUiState.RepoIssuesUiModelData -> {
                IssueList(
                    innerPadding = innerPadding,
                    repoDetailsUiModel = repoDetailsUiState.repositoryIssues,
                )
            }

            is RepoIssuesUiState.Error -> {
                ErrorSection(
                    innerPadding = innerPadding,
                    customErrorExceptionUiModel = repoDetailsUiState.customErrorExceptionUiModel,
                    onRefreshButtonClicked = onRefreshButtonClicked
                )
            }
        }

    }
}

@Composable
fun IssueList(
    innerPadding: PaddingValues,
    repoDetailsUiModel: RepoIssuesUiModel,
) {
    LazyColumn(
        Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        items(repoDetailsUiModel.repoIssues) { issueItem ->
            IssueItem(issueItem, LocalOnShowIssueItemClicked.current)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewMultiStateScreen(
    @PreviewParameter(com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.preview_data.IssueUiStatePreviewParameterProvider::class) uiState: RepoIssuesUiState
) {
    RepoIssuesContent(
        repoDetailsUiState = uiState,
        onClickBack = {},
        onRefreshButtonClicked = {}
    )
}

//@Preview
//@Composable
//private fun PreviewSingleStateScreen() {
//    ODCGithubRepoAppTheme {
//        RepoIssuesContent(
//            repoDetailsUiState = fakeRepoIssuesResultUiState,
//            onShowIssueItemClicked = {_->},
//            onClickBack = {},
//            onRefreshButtonClicked = {}
//        )
//    }
//}
