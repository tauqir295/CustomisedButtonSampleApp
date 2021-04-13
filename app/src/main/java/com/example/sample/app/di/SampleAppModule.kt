package com.example.sample.app.di

import android.content.Context
import androidx.room.Room
import com.example.sample.app.database.AppDatabase
import com.example.sample.app.database.UserDao
import com.example.sample.app.login.signup.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SampleAppModule {

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideMainRepo(userDao: UserDao): SignUpRepository = SignUpRepository(userDao)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "customised-button-sample-app-db"
        ).build()
    }
}