package com.example.odcgithubrepoapp.di

import com.samy.domain.repository.GithubReposRepository
import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.repository.GithubReposRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubReposRepository(
        githubRemoteDataSource: GithubRemoteDataSource,
        localDataSource: GithubLocalDataSource
    ): com.samy.domain.repository.GithubReposRepository {
        return GithubReposRepositoryImpl(githubRemoteDataSource, localDataSource)
    }
}