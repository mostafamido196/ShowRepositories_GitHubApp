import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toCustomRemoteExceptionDomainModel
import com.example.odcgithubrepoapp.data.mapper.to_room.toReposListEntity

@OptIn(ExperimentalPagingApi::class)
class RepoPagingSourceWithApiAndRoom(
    private val localDataSource: GithubLocalDataSource,
    private val remoteDataSource: GithubRemoteDataSource,
) : RemoteMediator<Int, ReposListEntity>() {

    private var nextPageKey: Int = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ReposListEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    Log.d("mos samy", "Load type: REFRESH")
                    1
                }
                LoadType.PREPEND -> {
                    Log.d("mos samy", "Load type: PREPEND, returning Success(endOfPaginationReached = true)")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    Log.d("mos samy", "Load type: APPEND, nextPageKey: $nextPageKey")
                    nextPageKey
                }
            }


            Log.d("mos samy", "Fetching repositories for page: $loadKey, size: ${state.config.pageSize}")
            val apiResponse = remoteDataSource.fetchRepositoriesWithPaging(page = loadKey, pageSize = state.config.pageSize)
            Log.d("mos samy", "API response received, items count: ${apiResponse.items.size}")


            val isEndOfList = apiResponse.items.isEmpty()
            Log.d("mos samy", "Is end of list: $isEndOfList")

            try {
                Log.d("mos samy", "Inserting repos into local database")
                localDataSource.insertRepos(apiResponse.items.map { it.toReposListEntity() })
                Log.d("mos samy", "Repos inserted successfully")
            } catch (e: Exception) {
                Log.e("mos samy", "Error inserting repos into database: ${e.message}", e)
                throw e.toCustomRemoteExceptionDomainModel()
            }

            nextPageKey = loadKey + 1
            Log.d("mos samy", "Next page key set to: $nextPageKey")

            MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e: Exception) {
            Log.e("mos samy", "Error in RemoteMediator: ${e.message}", e)
            MediatorResult.Error(e.toCustomRemoteExceptionDomainModel())
        }
    }
}
