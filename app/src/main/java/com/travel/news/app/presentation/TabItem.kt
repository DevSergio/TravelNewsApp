package com.travel.news.app.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.travel.news.app.R
import com.travel.news.app.presentation.news_feed.NewsFeedScreen
import com.travel.news.app.presentation.tourist_list.TouristListScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    object NewsFeed : TabItem(R.drawable.ic_news_feed, "News", { NewsFeedScreen(hiltViewModel()) })
    object TouristList :
        TabItem(R.drawable.ic_tourist_list, "Tourists", { TouristListScreen(hiltViewModel()) })
}