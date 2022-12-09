package com.travel.news.app.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.travel.news.app.domain.model.Tourist
import com.travel.news.app.repository.TouristDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TouristDetailsViewModel @Inject constructor(
    private val touristDetailsRepository: TouristDetailsRepository) :
    ViewModel() {

    private val _touristDetails = MutableStateFlow<Tourist?>(null)
    val touristDetails = _touristDetails.asStateFlow()

    val loading = mutableStateOf(false)

    fun getTouristDetails(id : Int) {
        touristDetailsRepository.loadTouristDetails(id).onEach { dataState ->
            Timber.d(dataState.toString())
            _touristDetails.value = dataState.data
        }.launchIn(viewModelScope)
    }
}