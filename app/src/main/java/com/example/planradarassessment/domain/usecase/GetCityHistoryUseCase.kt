package com.example.planradarassessment.domain.usecase

import com.example.planradarassessment.data.local.entities.toHistory
import com.example.planradarassessment.domain.model.History
import com.example.planradarassessment.domain.repository.WeatherRepository
import com.example.planradarassessment.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCityHistoryUseCase @Inject constructor(private val repo: WeatherRepository) :
    SingleUseCase<List<History>>()  {

    private var city: String = ""

    fun setCityName(city: String) {
        this.city = city
    }

    override fun buildSingle(): Single<List<History>> {
        return repo.getCityHistory(city).map { it -> it.map { it.toHistory() } }
    }
}