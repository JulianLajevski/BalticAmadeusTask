package baltic.amadeus.task.data.remote

import javax.inject.Inject

class ApiRemoteDataSource @Inject constructor(
    private val api: Api
) : BaseDataSource() {
    suspend fun getPosts() = getResult { api.getPosts() }
    suspend fun getPostDetail(id: Int) = getResult { api.getPostDetail(id) }
}