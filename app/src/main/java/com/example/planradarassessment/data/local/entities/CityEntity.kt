package com.example.planradarassessment.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.planradarassessment.domain.model.City

@Entity(tableName = "City")
data class CityEntity(@PrimaryKey val name: String)

fun CityEntity.toCity() : City = City(name = name)