package com.samy.domain.usecase

import com.samy.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SetFirstTimeUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository,
) {
    suspend operator fun invoke(
        bool: Boolean,
    ) {
        return githubReposRepository.setFirstTime(bool)
    }
}