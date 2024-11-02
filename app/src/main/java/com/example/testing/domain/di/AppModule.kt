package com.example.testing.domain.di

import android.app.Application
import androidx.room.Room
import com.example.testing.data.local.UsersDB
import com.example.testing.data.remote.UsersApi
import com.example.testing.domain.model.UsersRepository
import com.example.testing.domain.model.UsersRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://reqres.in/"

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideUsersApi(): UsersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(UsersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(api: UsersApi, db: UsersDB): UsersRepository {
        return UsersRepositoryImp(api, db)
    }

    @Provides
    @Singleton
    fun provideUsersDatabase(app: Application): UsersDB {
        return Room.databaseBuilder(
            app,
            UsersDB::class.java,
            "users_db"
        ).build()
    }

    @Provides
    fun provideUserDao(db: UsersDB) = db.dao
}
