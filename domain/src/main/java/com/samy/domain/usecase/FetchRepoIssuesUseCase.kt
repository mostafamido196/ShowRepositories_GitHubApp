package com.samy.domain.usecase

import com.samy.domain.model.GithubReposDomainModel
import com.samy.domain.model.RepoIssueItemDomainModel
import com.samy.domain.model.RepoIssuesDomainModel
import com.samy.domain.repository.GithubReposRepository
import javax.inject.Inject

class FetchRepoIssuesUseCase  @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(
        ownerName:String, name:String
    ): RepoIssuesDomainModel {
        return githubReposRepository.fetchRepoIssues(ownerName,name)
    }
}
