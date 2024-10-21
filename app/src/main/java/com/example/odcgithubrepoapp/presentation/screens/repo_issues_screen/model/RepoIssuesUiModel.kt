package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model

data class RepoIssuesUiModel (
    val repoIssues:List<RepoIssueItemUiModel>
)
data class RepoIssueItemUiModel(
    val id:Long,
    val createdAt: String,
    val title: String,
    val htmlUrl: String,
    val state: String,
    val authorAssociation: String,
)