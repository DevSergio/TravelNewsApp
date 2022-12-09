package com.travel.news.app.presentation.tourist_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.travel.news.app.R
import com.travel.news.app.presentation.components.ProfileCard
import com.travel.news.app.presentation.news_feed.NewsListEvent

@Composable
fun TouristListScreen(viewModel: TouristsListViewModel) {

    val loading = viewModel.loading.value

    val tourists = viewModel.tourists.collectAsState().value

    val page = viewModel.page.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_purple))
            .wrapContentSize(Alignment.Center)
    ) {
        tourists?.let {
            ProfileCard(
                loading = loading,
                tourists = it,
                onChangeScrollPosition = viewModel::onChangeTouristScrollPosition,
                page = page,
                onTriggerNextPage = { viewModel.onTriggerEvent(NewsListEvent.NextPageEvent) }
            )
        }
    }
}

