package com.example.planradarassessment.domain.model

import java.io.Serializable

data class City(
    val name: String = "",
    val icon: String = "",
    val description: String = "",
    val temp: Double = 0.0,
    val humidity: Int = 0,
    val windSpeed: Double = 0.0
    ) : Serializable