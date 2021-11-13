package com.example.planradarassessment.domain.repository

import com.example.planradarassessment.data.local.entities.CityEntity
import com.example.planradarassessment.data.local.entities.HistoryEntity
import com.example.planradarassessment.data.remote.dto.CityDto
import io.reactivex.Single

interface WeatherRepository {

    fun searchCity(city: String): Single<CityDto>
    fun addCityHistory(historyEntity: HistoryEntity)
    fun saveCity(cityEntity: CityEntity)
    fun getAllSavedCities(): Single<List<CityEntity>>
    fun getCityHistory(city: String): Single<List<HistoryEntity>>
}