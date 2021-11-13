package com.example.planradarassessment.domain.model

data class History(
    val icon: String,
    val timeStamp: Long,
    val condition: String,
    val temp: Double
)