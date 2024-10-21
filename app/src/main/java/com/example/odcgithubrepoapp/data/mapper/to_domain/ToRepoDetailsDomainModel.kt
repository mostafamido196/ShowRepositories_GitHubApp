package com.example.odcgithubrepoapp.data.mapper.to_domain

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_details.RepoDetailsDataModel
import com.samy.domain.model.RepoDetailsDomainModel

fun RepoDetailsDataModel.toRepoDetailsDomainModel(): RepoDetailsDomainModel {
    return RepoDetailsDomainModel(
        id = this.id,
        name = this.name,
        owner = this.owner.login,
        avatar = this.owner.avatarUrl,
        stars = this.stargazersCount,
        description = this.description,
        fullName = this.fullName,
        forks = this.forks,
        url = this.url,
        createdAt = createdAt,
        language = this.language ?: ""
    )
}