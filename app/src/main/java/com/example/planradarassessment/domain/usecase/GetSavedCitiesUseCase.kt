package com.example.planradarassessment.domain.usecase

import com.example.planradarassessment.data.local.entities.toCity
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.domain.repository.WeatherRepository
import com.example.planradarassessment.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetSavedCitiesUseCase @Inject constructor(private val repo: WeatherRepository) :
    SingleUseCase<List<City>>()  {

    override fun buildSingle(): Single<List<City>> {
        return  repo.getAllSavedCities().map { it -> it.map { it.toCity() } }
    }

}