package com.example.odcgithubrepoapp.data.data_sources.local

import android.util.Log
import androidx.paging.PagingSource
import com.example.odcgithubrepoapp.data.data_sources.local.data_store.DataStorePreference
import com.example.odcgithubrepoapp.data.data_sources.local.room.RepoListDao
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubLocalDataSource @Inject constructor(
    private val repoListDao: RepoListDao,
    private val dataStorePreference: DataStorePreference,
) {
    fun getRepoList(): PagingSource<Int, ReposListEntity> {
        return repoListDao.getReposList()
    }

    suspend fun insertRepos(repoList: List<ReposListEntity>) {
        Log.d("mos samy", "room:insertRepos: $repoList ")
        Log.d("mos samy", "room:insertRepos:size ${repoList.size} ")
//        GlobalScope.launch(Dispatchers.IO) {
//            val result =repoListDao.getRepoListSize()
//            Log.d("mos samy", "room:getRepoListSize: $result ")
//        }
//        val isFirstTime = dataStorePreference.readIsFirstTimeEnterApp().first()
//        if (isFirstTime) {
            repoListDao.insertReposList(repoList)
//            dataStorePreference.saveIsFirstTimeEnterApp(false)
//        }
    }


    suspend fun setFirstTime(bool:Boolean){
        dataStorePreference.saveIsFirstTimeEnterApp(bool)
    }
    suspend fun isFirstTime(): Flow<Boolean> {
       return dataStorePreference.readIsFirstTimeEnterApp()
    }

}