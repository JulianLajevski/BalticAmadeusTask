package baltic.amadeus.task.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import baltic.amadeus.task.data.entities.posts.Post
import baltic.amadeus.task.utils.Constants.Companion.DATABASE_NAME

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
        abstract fun postDao(): PostDao

        companion object{
            @Volatile private var instance: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

            private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
}