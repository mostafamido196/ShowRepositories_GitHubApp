package com.samy.domain.usecase

import com.samy.domain.model.RepoDetailsDomainModel
import com.samy.domain.repository.GithubReposRepository
import javax.inject.Inject

class FetchRepositoryDetailsUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(
        ownerName:String, name:String
    ): RepoDetailsDomainModel {
        return githubReposRepository.fetchRepoDetails(ownerName, name)
    }
}
