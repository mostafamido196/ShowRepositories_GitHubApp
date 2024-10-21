package com.samy.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.samy.domain.model.GithubReposDomainModel
import com.samy.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FetchGithubReposListUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(): Flow<PagingData<GithubReposDomainModel>> {
        Log.d("mos samy", "uc:invoke:  ")
        return githubReposRepository.fetchRepoList()
    }
}

// val repositoryListApi: RepositoriesListApi = Retrofit
// val githubRemoteDataSource =  GithubRemoteDataSource(repositoryListApi)
// val githubReposRepository: GithubReposRepository = GithubReposRepositoryImpl(githubRemoteDataSource)
// val fetchGithubReposListUseCase = FetchGithubReposListUseCase(githubReposRepository)
// RepoListViewModel( fetchGithubReposListUseCase)