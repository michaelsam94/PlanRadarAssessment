package com.example.planradarassessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planradarassessment.data.local.dao.CityDao
import com.example.planradarassessment.data.local.dao.HistoryDao
import com.example.planradarassessment.data.local.entities.CityEntity
import com.example.planradarassessment.data.local.entities.HistoryEntity

@Database(entities = [CityEntity::class,HistoryEntity::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract val historyDao: HistoryDao
    abstract val cityDao: CityDao

    companion object {
        const val DB_NAME = "weather.db"
    }
}