package com.example.planradarassessment.presentaion.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planradarassessment.common.Resource
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.domain.usecase.GetCityUseCase
import com.example.planradarassessment.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val useCase: GetCityUseCase) : ViewModel() {

    private val _state = MutableLiveData<Resource<City>>()
    val state = _state

    fun getCity(city: String) {
        _state.value = Resource.Loading()
        useCase.setCityName(city)
        useCase.execute({
            _state.value = Resource.Success(it)
        }, {
            _state.value =
                Resource.Error(message = it.localizedMessage ?: "uncaught exception", data = null)
        })
    }
}