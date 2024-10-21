package com.example.odcgithubrepoapp.data.data_sources.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity

@Dao
interface RepoListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReposList(repoList: List<ReposListEntity>)

    @Query("SELECT * FROM ReposListEntity")
    fun getReposList():  PagingSource<Int, ReposListEntity>

    @Query("SELECT COUNT(*) FROM ReposListEntity")
    suspend fun getRepoListSize(): Int
}