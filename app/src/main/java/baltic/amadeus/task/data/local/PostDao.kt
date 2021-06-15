package baltic.amadeus.task.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import baltic.amadeus.task.data.entities.postDetails.PostDetails
import baltic.amadeus.task.data.entities.posts.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<Post>)

    @Query("SELECT * FROM POSTS_TABLE ORDER BY id ASC")
    fun readAllData(): LiveData<List<Post>>
}