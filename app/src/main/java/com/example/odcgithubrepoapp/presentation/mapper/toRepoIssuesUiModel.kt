package com.example.odcgithubrepoapp.presentation.mapper

import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssueItemUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssuesUiModel


fun com.samy.domain.model.RepoIssuesDomainModel.toGithubReposUiModel(): RepoIssuesUiModel {
    return RepoIssuesUiModel(
        this.repoIssues.map {
            RepoIssueItemUiModel(
                id = it.id,
                authorAssociation = it.authorAssociation,
                htmlUrl = it.htmlUrl,
                createdAt = it.createdAt,
                title = it.title,
                state = it.state
            )
        }
    )
}