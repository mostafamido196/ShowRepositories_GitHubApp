package com.example.odcgithubrepoapp.data.mapper.to_room

import Item
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.samy.domain.model.GithubReposDomainModel

fun GithubReposDomainModel.toReposListEntity(): ReposListEntity {
    return ReposListEntity(
        id = this.id,
        name = this.name,
        avatar = this.avatar,
        ownerName = this.ownerName,
        description = this.description,
        starsCount = this.stars
    )

}

fun Item.toReposListEntity(): ReposListEntity {
    return ReposListEntity(
        id = this.id,
        name = this.name,
        ownerName = this.owner.login,
        avatar = this.owner.avatarUrl,
        starsCount = this.stargazersCount,
        description = this.description?:"No description available"
    )
}

