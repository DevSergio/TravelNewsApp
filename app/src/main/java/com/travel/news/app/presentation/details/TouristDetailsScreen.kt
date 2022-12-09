package com.travel.news.app.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.travel.news.app.R
import com.travel.news.app.utils.DateUtils
import com.travel.news.app.utils.DateUtils.getDateInFormat

@Composable
fun TouristDetailsScreen(viewModel: TouristDetailsViewModel) {

    val touristDetails = viewModel.touristDetails.collectAsState().value

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White,
            elevation = 4.dp
        )
        {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .size(154.dp)
                        .padding(5.dp),
                    shape = CircleShape,
                    border = BorderStroke(0.5.dp, Color.LightGray),
                    elevation = 4.dp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_placeholder),
                        contentDescription = "profile image",
                        modifier = Modifier.size(135.dp),
                        contentScale = ContentScale.Crop
                    )

                }
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    touristDetails?.name?.let {
                        Text(
                            color = Color.Blue,
                            fontSize = 24.sp,
                            style = MaterialTheme.typography.h4,
                            text = it
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.location) + " " + touristDetails?.location,
                        modifier = Modifier.padding(3.dp)
                    )

                    touristDetails?.email?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(3.dp),
                            style = MaterialTheme.typography.subtitle1
                        )
                    }

                    touristDetails?.dateCreated?.getDateInFormat(
                        DateUtils.INPUT_FORMAT,
                        DateUtils.OUTPUT_FORMAT
                    )?.let {
                        Text(
                            text = it,
                            color = Color.DarkGray,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}