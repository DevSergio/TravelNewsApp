package com.travel.news.app.presentation.news_feed

sealed class NewsListEvent {

    object NextPageEvent : NewsListEvent()
}