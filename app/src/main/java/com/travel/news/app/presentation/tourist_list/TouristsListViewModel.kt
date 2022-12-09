package com.travel.news.app.presentation.tourist_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.travel.news.app.domain.model.Tourist
import com.travel.news.app.presentation.news_feed.NewsListEvent
import com.travel.news.app.presentation.news_feed.PAGE_SIZE
import com.travel.news.app.repository.TouristListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TouristsListViewModel @Inject constructor(private val touristListRepository: TouristListRepository) :
    ViewModel() {

    private val _tourists = MutableStateFlow<List<Tourist>?>(emptyList())
    val tourists = _tourists.asStateFlow()

    private var touristListScrollPosition = 0

    val page = mutableStateOf(1)

    init {
        getTourists(page.value)
    }

    val loading = mutableStateOf(false)

    private fun getTourists(page: Int) {
        touristListRepository.loadTourists(page).onEach { dataState ->
            Timber.d(dataState.toString())
            _tourists.value = dataState.data
        }.launchIn(viewModelScope)
    }

    fun onTriggerEvent(event: NewsListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is NewsListEvent.NextPageEvent -> {
                        nextPage()
                    }
                }
            } catch (e: Exception) {
                Timber.d("launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Timber.d("launchJob: finally called.")
            }
        }
    }

    private fun nextPage() {
        if ((touristListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            incrementPage()

            if (page.value > 1) {
                touristListRepository.loadTourists(page.value).onEach { dataState ->
                    loading.value = dataState.loading

                    dataState.data?.let { list ->
                        appendTourists(list)
                    }

                    dataState.error?.let { error ->
                        Timber.e("nextPage: $error")
                        //  dialogQueue.appendErrorMessage("An Error Occurred", error)
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun appendTourists(news: List<Tourist>) {
        val current = this._tourists.value?.let { ArrayList(it) }
        current?.addAll(news)
        this._tourists.value = current
    }

    fun onChangeTouristScrollPosition(position: Int){
        setListScrollPosition(position = position)
    }

    private fun setListScrollPosition(position: Int){
        touristListScrollPosition = position
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    private fun setPage(page: Int) {
        this.page.value = page
    }
}