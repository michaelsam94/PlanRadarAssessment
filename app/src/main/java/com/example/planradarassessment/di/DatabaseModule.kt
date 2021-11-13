package com.example.planradarassessment.di

import android.app.Application
import androidx.room.Room
import com.example.planradarassessment.data.local.AppDatabase
import com.example.planradarassessment.data.local.dao.CityDao
import com.example.planradarassessment.data.local.dao.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()
    }

    @Provides
    internal fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao {
        return appDatabase.historyDao
    }

    @Provides
    internal fun provideCityDao(appDatabase: AppDatabase): CityDao {
        return appDatabase.cityDao
    }
}