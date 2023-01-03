package com.example.dttproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
// The last row of each cardView in home page(4 vectors)
fun OverViewCardItems(
    text1: Int,
    text2: Int,
    text3: Int,
    text4: String

) {
    Row(
        Modifier.background(MaterialTheme.colors.onSurface)
    ) {
        Box(
            modifier = Modifier
                .weight(.25f)
                .height(15.dp)
        ) {
            Row() {
                Image(
                    painterResource(id =  com.example.dttproject.R.drawable.ic_bed) ,
                    contentDescription = ""
                )

                Text(
                    text = text1.toString(),
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 3.dp, bottom = 0.dp,top = 3.dp)
                )
            }

        }

        Box(
            modifier = Modifier
                .weight(.25f)
                .height(15.dp)
        ) {
            Row() {
                Image(
                    painterResource(id =  com.example.dttproject.R.drawable.ic_bath) ,
                    contentDescription = ""
                )

                Text(
                    text = text2.toString(),
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 3.dp, bottom = 0.dp,top = 3.dp)
                )
            }

        }

        Box(
            modifier = Modifier
                .weight(.25f)
                .height(15.dp)
        ) {
            Row() {
                Image(
                    painterResource(id =  com.example.dttproject.R.drawable.ic_layers) ,
                    contentDescription = ""
                )

                Text(
                    text = text3.toString(),
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 3.dp, bottom = 0.dp,top = 3.dp)
                )
            }

        }

        Box(
            modifier = Modifier
                .weight(.25f)
                .height(15.dp)
        ) {
            Row() {
                Image(
                    painterResource(id =  com.example.dttproject.R.drawable.ic_location) ,
                    contentDescription = ""
                )

                Text(
                    text = text4,
                    style = MaterialTheme.typography.overline,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 3.dp, bottom = 0.dp,top = 3.dp)
                )
            }

        }
    }

}
