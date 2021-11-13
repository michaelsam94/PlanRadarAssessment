package com.example.planradarassessment.domain.usecase

import com.example.planradarassessment.common.Constant
import com.example.planradarassessment.data.local.entities.HistoryEntity
import com.example.planradarassessment.data.remote.dto.toCity
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.domain.repository.WeatherRepository
import com.example.planradarassessment.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCityUseCase @Inject constructor(private val repo: WeatherRepository) :
    SingleUseCase<City>() {

    private var city: String = ""

    fun setCityName(city: String) {
        this.city = city
    }

    override fun buildSingle(): Single<City> {
        return repo.searchCity(city).map { it.toCity() }.doAfterSuccess {
            repo.addCityHistory(
                HistoryEntity(
                    icon = Constant.getIcon(it.icon),
                    timeStamp = System.currentTimeMillis(),
                    condition = it.description,
                    temp = it.temp,
                    city = city
                )
            )
        }
    }
}