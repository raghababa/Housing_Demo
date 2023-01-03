package com.example.dttproject.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dttproject.R
import com.example.dttproject.ui.theme.strong

// House detail
@SuppressLint("SuspiciousIndentation")
@Composable
fun HouseDetailItem(
    price: String,
    bedrooms: Int,
    bathrooms: Int,
    size: Int,
    distance: String,
    description: String
) {

//components of houseDetailScreen
    Column(
        modifier = Modifier.background(MaterialTheme.colors.onSurface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 24.dp)
        ) {
            //price
            Text(
                text = "$".plus(
                    price

                ),
                style = MaterialTheme.typography.subtitle1,
                color = strong,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
            //vector assets
            OverViewCardItems(
                bedrooms, bathrooms, size,
                distance.plus("km")
            )

        }
        //description title
        Text(
            text = stringResource(id = R.string.description),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = 16.dp, top = 34.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        //description
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colors.primaryVariant.copy(0.7F),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        //location title
        Text(
            stringResource(id = R.string.location),
            style = MaterialTheme.typography.subtitle1,
            color = strong,
            modifier = Modifier
                .padding(start = 16.dp))

        Spacer(modifier = Modifier.height(8.dp))

    }
}




