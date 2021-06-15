package baltic.amadeus.task.data.entities.postDetails


import androidx.room.Entity
import androidx.room.PrimaryKey
import baltic.amadeus.task.utils.Constants.Companion.POST_DETAIL_TABLE
import com.google.gson.annotations.SerializedName

@Entity(tableName = POST_DETAIL_TABLE)
data class PostDetails(
        @SerializedName("email")
        val email: String,
        @PrimaryKey
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("username")
        val username: String
)