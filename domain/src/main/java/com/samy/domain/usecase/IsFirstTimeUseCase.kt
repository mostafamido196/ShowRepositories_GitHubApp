package com.samy.domain.usecase

import com.samy.domain.model.RepoDetailsDomainModel
import com.samy.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFirstTimeUseCase  @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(
    ): Flow<Boolean> {
        return githubReposRepository.isFirstTime()
    }
}
