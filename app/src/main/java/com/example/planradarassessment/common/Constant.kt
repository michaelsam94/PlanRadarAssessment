package com.example.planradarassessment.common

object Constant {

    const val BASE_URL = "https://api.openweathermap.org"
    const val API_KEY = "f5cb0b965ea1564c50c6f1b74534d823"

    fun getIcon(iconId: String) = "https://openweathermap.org/img/w/$iconId.png"
}