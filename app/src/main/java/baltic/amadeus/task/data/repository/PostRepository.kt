package baltic.amadeus.task.data.repository

import baltic.amadeus.task.data.local.PostDao
import baltic.amadeus.task.data.remote.ApiRemoteDataSource
import baltic.amadeus.task.utils.performGetOperation
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiRemoteDataSource: ApiRemoteDataSource,
    private val postDao: PostDao
){
    fun getPosts() = performGetOperation(
        databaseQuery = {postDao.readAllData()},
        networkCall = {apiRemoteDataSource.getPosts()},
        saveCallResult = {postDao.insertData(it)}
    )

    fun getPostDetails(id: Int) = performGetOperation(
            databaseQuery = {postDao.getPostDetail(id)},
            networkCall = {apiRemoteDataSource.getPostDetail(id)},
            saveCallResult = {postDao.insertPostDetail(it)}
    )

}