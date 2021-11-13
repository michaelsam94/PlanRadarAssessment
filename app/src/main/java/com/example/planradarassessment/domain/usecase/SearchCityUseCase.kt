package com.example.planradarassessment.domain.usecase

import com.example.planradarassessment.data.remote.dto.toCity
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.domain.repository.WeatherRepository
import com.example.planradarassessment.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val repo: WeatherRepository) : SingleUseCase<City>() {

    private var cityName: String = ""

    fun setCityName(city: String) {
        cityName = city
    }

    override fun buildSingle(): Single<City> {
        return repo.searchCity(cityName).map { it.toCity() }.doAfterSuccess {
            repo.saveCity(com.example.planradarassessment.data.local.entities.CityEntity(cityName))
        }
    }

}