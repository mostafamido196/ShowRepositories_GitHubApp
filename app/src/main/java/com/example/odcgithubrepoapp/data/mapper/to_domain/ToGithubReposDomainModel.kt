package com.example.odcgithubrepoapp.data.mapper.to_domain

import Item
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.samy.domain.model.GithubReposDomainModel

fun Item.toGithubReposDomainModel(): GithubReposDomainModel {
    return GithubReposDomainModel(
        id = this.id,
        name = this.name,
        ownerName = this.owner.login,
        avatar = this.owner.avatarUrl,
        stars = this.stargazersCount,
        description = this.description ?: "No description available"
    )
}

fun ReposListEntity.toGithubReposDomainModel(): GithubReposDomainModel {
    return GithubReposDomainModel(
        id = this.id,
        name = this.name,
        ownerName = this.ownerName,
        avatar = this.avatar,
        stars = this.starsCount,
        description = this.description
    )
}