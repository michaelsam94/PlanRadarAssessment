package com.example.planradarassessment.presentaion.cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planradarassessment.common.Resource
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.domain.usecase.GetSavedCitiesUseCase
import com.example.planradarassessment.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val savedCitiesUseCase: GetSavedCitiesUseCase
) : ViewModel() {

    private val _searchCityState = MutableLiveData<Resource<City>>()
    val searchCityState = _searchCityState

    private val _savedCitiesState = MutableLiveData<Resource<List<City>>>()
    val saveCitiesState = _savedCitiesState

    init {
        getSavedCities()
    }

    private fun getSavedCities() {
        _savedCitiesState.value = Resource.Loading()
        savedCitiesUseCase.execute({
            _savedCitiesState.value = Resource.Success(it)
        }, {
            _searchCityState.value =
                Resource.Error(message = it.localizedMessage ?: "uncaught exception", data = null)
        })
    }

    fun addCity(city: String) {
        _searchCityState.value = Resource.Loading()
        searchCityUseCase.setCityName(city)
        searchCityUseCase.execute({
            _searchCityState.value = Resource.Success(it)
        }, {
            _searchCityState.value =
                Resource.Error(message = it.localizedMessage ?: "uncaught exception", data = null)
        })
    }

}