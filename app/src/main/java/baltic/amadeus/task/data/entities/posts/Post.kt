package baltic.amadeus.task.data.entities.posts

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import baltic.amadeus.task.utils.Constants.Companion.POSTS_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = POSTS_TABLE)
@Parcelize
data class Post(
        @SerializedName("body")
        val body: String,
        @PrimaryKey
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
) : Parcelable