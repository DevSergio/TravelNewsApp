package com.travel.news.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.travel.news.app.R
import com.travel.news.app.domain.model.Post
import com.travel.news.app.utils.DateUtils.INPUT_FORMAT
import com.travel.news.app.utils.DateUtils.OUTPUT_FORMAT
import com.travel.news.app.utils.DateUtils.getDateInFormat


@Composable
fun PostCard(post: Post?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color.White)
    ) {
        HorizontalDivider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (post?.user?.profilepicture?.isNotEmpty() == true) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(post.user.profilepicture)
                        .crossfade(true)
                        .build(),
                    contentDescription = post.title,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(4.dp)
                        .clip(CircleShape)
                )
            }
            post?.user?.name?.let { Text(text = it, fontWeight = FontWeight.Bold) }
            Box(modifier = Modifier.fillMaxWidth()) {
                post?.location?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Thin,
                        color = Color.Blue,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 8.dp)
                    )
                }
            }

        }
        if (post?.multimedia?.isNotEmpty() == true) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.multimedia[0].url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                error = painterResource(R.drawable.ic_placeholder)
            )
        }
        post?.description?.let { Text(text = it, modifier = Modifier.padding(8.dp)) }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${post?.commentCount} " + stringResource(id = R.string.comments),
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                post?.createdDate?.getDateInFormat(INPUT_FORMAT, OUTPUT_FORMAT)?.let {
                    Text(
                        text = it,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
        }
    }
}


@Composable
fun HorizontalDivider() {
    Divider(
        color = Color.LightGray,
        thickness = 5.dp,
        modifier = Modifier
            .alpha(0.3f)
            .padding(top = 8.dp, bottom = 8.dp)
    )
}
