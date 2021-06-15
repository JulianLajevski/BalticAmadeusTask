package baltic.amadeus.task.data.remote

import baltic.amadeus.task.data.entities.postDetails.PostDetails
import baltic.amadeus.task.data.entities.posts.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("users/{user_id}")
    suspend fun getPostDetail(@Path("user_id") userId: Int): Response<PostDetails>
}