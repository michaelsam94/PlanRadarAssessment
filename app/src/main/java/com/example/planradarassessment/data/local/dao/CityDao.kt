package com.example.planradarassessment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.planradarassessment.data.local.entities.CityEntity
import io.reactivex.Single

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityEntity: CityEntity): Long

    @Query("SELECT * FROM City")
    fun loadAll(): Single<List<CityEntity>>
}