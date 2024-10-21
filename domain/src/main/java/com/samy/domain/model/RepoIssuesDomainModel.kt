package com.samy.domain.model

data class RepoIssuesDomainModel (
    val repoIssues:List<RepoIssueItemDomainModel>
)
data class RepoIssueItemDomainModel(
    val id:Long,
    val createdAt: String,
    val title: String,
    val htmlUrl: String,
    val state: String,
    val authorAssociation: String,
)