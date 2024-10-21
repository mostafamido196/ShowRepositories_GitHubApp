package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.common_component.shimmer.repo_list.AnimateShimmerRepoList
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component.CustomSearchBar
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component.RepoItem
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.GithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoListUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.RepoListUiStatePreviewParameterProvider
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.viewmodel.RepoListViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count

@Composable
fun RepoListScreen(
    onRepoItemClicked: (String, String) -> Unit,
) {
    val repoListViewModel: RepoListViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        repoListViewModel.requestGithubRepoList()
    }
    val repoListUiState by repoListViewModel.reposStateFlow.collectAsStateWithLifecycle()


    SearchAndRepoListContent(
        repoListUiState = repoListUiState,
        onRefreshButtonClicked = {
            repoListViewModel.requestGithubRepoList()
        },
        onRepoItemClicked = onRepoItemClicked,
        onSearch = { key -> repoListViewModel.search(key) }
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndRepoListContent(
    modifier: Modifier = Modifier,
    repoListUiState: RepoListUiState,
    onRefreshButtonClicked: () -> Unit,
    onRepoItemClicked: (String, String) -> Unit,
    onSearch: (String) -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(
                title = R.string.app_name,
                showBackButton = false,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Log.d("mos samy", "--------------------------------------------")
        Log.d("mos samy", "RepoListContent: repoListUiState:$repoListUiState")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            if (repoListUiState is RepoListUiState.Loading || repoListUiState is RepoListUiState.Success) {
                CustomSearchBar(
                    modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 8.dp),
                    onSearch = onSearch,
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { newQuery -> searchQuery = newQuery },
                )
            }

            when (repoListUiState) {
                is RepoListUiState.Idle -> {
                    // Do nothing or show initial screen message
                }
                is RepoListUiState.Loading -> {
                    Log.d("mos samy", "SearchAndRepoListContent: loading")
                    AnimateShimmerRepoList(
                        innerPadding = PaddingValues(
                            top = 8.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                    )
                }
                is RepoListUiState.Error -> {
                    ErrorSection(
                        innerPadding = PaddingValues(),
                        customErrorExceptionUiModel = repoListUiState.customRemoteExceptionUiModel,
                        onRefreshButtonClicked = onRefreshButtonClicked
                    )
                }
                is RepoListUiState.Success -> {
                    RepoListSuccess(
                        repoList = repoListUiState.repoList,
                        onRepoItemClicked = onRepoItemClicked
                    )
                }
            }
        }
    }
}

@Composable
fun RepoListSuccess(
    repoList: Flow<PagingData<GithubReposUiModel>>,
    onRepoItemClicked: (String, String) -> Unit
) {
    val repoItems = repoList.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(repoItems.itemCount) { index ->
            val repo = repoItems[index]
            if (repo != null) {
                RepoItem(
                    githubRepoUiModel = repo,
                    onRepoItemClicked = onRepoItemClicked
                )
            }
        }

        when (repoItems.loadState.append) {
            is LoadState.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
            is LoadState.Error -> {
                item {
                    Text(
                        text = "Error loading more data. Try again.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { repoItems.retry() }
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            is LoadState.NotLoading -> {
                // You can add some logging here if needed
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRepoListScreen(
    @PreviewParameter(RepoListUiStatePreviewParameterProvider::class) uiState: RepoListUiState,
) {
    ODCGithubRepoAppTheme {
//        SearchAndRepoListContent(
//            repoListUiState = uiState,
//            onRefreshButtonClicked = {},
//            onRepoItemClicked = { _, _ -> },
//            onSearch = { _ -> }
//        )
    }
}