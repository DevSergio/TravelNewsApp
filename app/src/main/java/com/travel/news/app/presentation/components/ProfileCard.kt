package com.travel.news.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.travel.news.app.domain.model.Tourist

@Composable
fun ProfileCard(
    loading: Boolean,
    tourists: List<Tourist>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerNextPage: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && tourists.isEmpty()) {
            LoadingDataShimmer(imageHeight = 250.dp)
        } else if (tourists.isEmpty()) {
            NothingHere()
        } else {
            LazyColumn() {
                itemsIndexed(
                    items = tourists
                ) { index, tourists ->
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * 10) && !loading) {
                        onTriggerNextPage()
                    }
                    TouristCard(
                        tourist = tourists
                    )
                }
            }
        }
    }
}