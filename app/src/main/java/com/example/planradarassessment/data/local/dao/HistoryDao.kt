package com.example.planradarassessment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.planradarassessment.data.local.entities.CityEntity
import com.example.planradarassessment.data.local.entities.HistoryEntity
import io.reactivex.Single

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(historyEntity: HistoryEntity): Long

    @Query("SELECT * FROM History where city = :city")
    fun loadAll(city: String): Single<List<HistoryEntity>>

}