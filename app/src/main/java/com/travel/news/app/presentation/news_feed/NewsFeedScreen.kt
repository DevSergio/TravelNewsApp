package com.travel.news.app.presentation.news_feed

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
import com.travel.news.app.presentation.components.NewsCard

@Composable
fun NewsFeedScreen(viewModel: NewsFeedViewModel) {

    val loading = viewModel.loading.value

    val posts = viewModel.post.collectAsState().value

    val page = viewModel.page.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_purple))
            .wrapContentSize(Alignment.Center)

    ) {
        posts?.let {
            NewsCard(
                loading = loading,
                news = it,
                onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                page = page,
                onTriggerNextPage = { viewModel.onTriggerEvent(NewsListEvent.NextPageEvent) },
            )
        }
    }
}
