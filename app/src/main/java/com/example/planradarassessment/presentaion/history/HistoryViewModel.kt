package com.example.planradarassessment.presentaion.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planradarassessment.common.Resource
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.domain.model.History
import com.example.planradarassessment.domain.usecase.GetCityHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val useCase: GetCityHistoryUseCase) : ViewModel() {

    private val _state = MutableLiveData<Resource<List<History>>>()
    val state = _state

    fun getSavedHistory(cityName: String) {
        _state.value = Resource.Loading()
        useCase.setCityName(cityName)
        useCase.execute({
            _state.value = Resource.Success(it)
        }, {
            _state.value =
                Resource.Error(message = it.localizedMessage ?: "uncaught exception", data = null)
        })
    }
}