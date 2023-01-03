package com.example.dttproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.dttproject.ui.theme.strong


@Composable
//home Screen card components
fun OverViewCard(
    image: String,
    price: String,
    zip: String,
    city: String,
    bedrooms: Int,
    bathrooms: Int,
    layers: Int,
    distance: String,
    onItemClick: () -> Unit
) {
    val domain = "https://intern.development.d-tt.dev/"
    Card(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.background)
            .clip(RoundedCornerShape(30.dp))
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {

        // Row - Image + Content
        Row(
            modifier = Modifier
                .height(100.dp)
                .background(MaterialTheme.colors.onSurface),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
            ) {
                Image(
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = domain.plus(image))
                            .allowHardware(false)
                            .build(),

                        ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )

            }


            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "$".plus(price),
                    style = typography.subtitle1,
                    color = strong
                )
                Text(
                    text = zip.plus(" ").plus(city),
                    style = typography.overline,
                    color = MaterialTheme.colors.error
                )
                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(12.dp))
                OverViewCardItems(
                    bedrooms, bathrooms, layers, distance.plus("km")
                )

            }
        }

    }

}

