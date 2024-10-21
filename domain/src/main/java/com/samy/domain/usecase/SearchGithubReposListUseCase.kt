package com.samy.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.samy.domain.model.GithubReposDomainModel
import com.samy.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchGithubReposListUseCase  @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(searchKey:String): Flow<PagingData<GithubReposDomainModel>> {
        Log.d("mos samy", "uc:search invoke: key $searchKey ")
        val r = githubReposRepository.searchRepoList(searchKey)
        Log.d("mos samy", "invoke: ${r.toList()}")
        return r
    }
}