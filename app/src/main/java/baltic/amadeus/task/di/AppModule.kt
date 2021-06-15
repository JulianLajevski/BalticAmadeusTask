package baltic.amadeus.task.di

import android.content.Context
import baltic.amadeus.task.data.local.AppDatabase
import baltic.amadeus.task.data.local.PostDao
import baltic.amadeus.task.data.remote.Api
import baltic.amadeus.task.data.remote.ApiRemoteDataSource
import baltic.amadeus.task.data.repository.PostRepository
import baltic.amadeus.task.utils.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(api: Api) = ApiRemoteDataSource(api)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.postDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ApiRemoteDataSource,
                          localDataSource: PostDao) =
        PostRepository(remoteDataSource, localDataSource)
}