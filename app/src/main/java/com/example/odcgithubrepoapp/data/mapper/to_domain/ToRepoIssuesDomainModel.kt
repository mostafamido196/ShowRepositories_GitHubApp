package com.example.odcgithubrepoapp.data.mapper.to_domain

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issue_list.RepoIssuesResponse
import com.samy.domain.model.RepoIssueItemDomainModel
import com.samy.domain.model.RepoIssuesDomainModel


fun RepoIssuesResponse.toRepoIssuesDomainModel(): RepoIssuesDomainModel {
    return RepoIssuesDomainModel(
        this.map {
            RepoIssueItemDomainModel(
                id = it.id,
                authorAssociation = it.authorAssociation,
                htmlUrl = it.htmlUrl,
                createdAt = it.createdAt,
                title = it.title,
                state = it.state
            )
        },
    )
}