package com.travel.news.app.presentation.news_feed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.travel.news.app.domain.model.Post
import com.travel.news.app.repository.NewsFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val PAGE_SIZE = 10

@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val newsFeedRepository: NewsFeedRepository) :
    ViewModel() {

    private val _post = MutableStateFlow<List<Post>?>(emptyList())
    val post = _post.asStateFlow()

    private var newsListScrollPosition = 0

    val page = mutableStateOf(1)

    init {
        getNews(page.value)
    }

    val loading = mutableStateOf(false)

    private fun getNews(page: Int) {
        newsFeedRepository.loadNewsFeed(page).onEach { dataState ->
            Timber.d(dataState.toString())
            _post.value = dataState.data
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
        if ((newsListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            incrementPage()

            if (page.value > 1) {
                newsFeedRepository.loadNewsFeed(page.value).onEach { dataState ->
                    loading.value = dataState.loading

                    dataState.data?.let { list ->
                        appendNews(list)
                    }

                    dataState.error?.let { error ->
                        Timber.e("nextPage: $error")
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onChangeRecipeScrollPosition(position: Int){
        setListScrollPosition(position = position)
    }

    private fun setListScrollPosition(position: Int){
        newsListScrollPosition = position
    }

    private fun appendNews(news: List<Post>) {
        val current = this._post.value?.let { ArrayList(it) }
        current?.addAll(news)
        this._post.value = current
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    private fun setPage(page: Int) {
        this.page.value = page
    }
}