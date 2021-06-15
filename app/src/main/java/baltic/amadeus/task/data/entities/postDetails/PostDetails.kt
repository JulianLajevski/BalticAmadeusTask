package baltic.amadeus.task.data.entities.postDetails


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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