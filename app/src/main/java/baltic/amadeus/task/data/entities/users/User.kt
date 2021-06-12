package baltic.amadeus.taskapp.data.network.models.users


import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("username")
        val username: String
)