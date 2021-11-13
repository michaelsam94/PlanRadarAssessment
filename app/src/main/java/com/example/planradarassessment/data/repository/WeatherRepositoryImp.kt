package com.example.planradarassessment.data.repository

import com.example.planradarassessment.common.Constant
import com.example.planradarassessment.data.local.AppDatabase
import com.example.planradarassessment.data.local.entities.CityEntity
import com.example.planradarassessment.data.local.entities.HistoryEntity
import com.example.planradarassessment.data.remote.WeatherApi
import com.example.planradarassessment.data.remote.dto.CityDto
import com.example.planradarassessment.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(val api: WeatherApi, val database: AppDatabase) :
    WeatherRepository {


    override fun searchCity(city: String): Single<CityDto> {
        return api.getCity(city, Constant.API_KEY)
    }

    override fun addCityHistory(historyEntity: HistoryEntity) {
        database.historyDao.insert(historyEntity)
    }

    override fun saveCity(cityEntity: CityEntity) {
        database.cityDao.insert(cityEntity)
    }

    override fun getAllSavedCities(): Single<List<CityEntity>> {
        return database.cityDao.loadAll()
    }

    override fun getCityHistory(city: String): Single<List<HistoryEntity>> {
        return database.historyDao.loadAll(city)
    }

}