package com.example.planradarassessment.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.planradarassessment.domain.model.History

@Entity(
    tableName = "History"
)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val city: String,
    val icon: String,
    val timeStamp: Long,
    val condition: String,
    val temp: Double
)

fun HistoryEntity.toHistory(): History = History(
    icon = icon,
    timeStamp = timeStamp,
    condition = condition,
    temp = temp
)
