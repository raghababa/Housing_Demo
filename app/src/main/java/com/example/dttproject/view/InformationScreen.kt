package com.example.dttproject.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myproject.R
import com.example.dttproject.navigation.MainActions
import com.example.dttproject.ui.theme.strong

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InformationScreen(actions: MainActions) {
    val dttWebsite = "https://www.d-tt.nl"
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(dttWebsite)) }

    val context = LocalContext.current

    Scaffold(

        bottomBar = {
            com.example.dttproject.components.BottomNavigation(
                navController = NavController(context),
                actions
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)

        ) {
            //title
            Text(
                stringResource(id = R.string.about),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(start = 20.dp, top = 34.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            // about description
            Text(
                text = stringResource(id = R.string.about_information),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colors.primaryVariant.copy(0.7F),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            //design and development
            Text(
                stringResource(id = R.string.design_and_development),
                style = MaterialTheme.typography.h5, color = strong,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
            Row(modifier = Modifier.padding(start = 20.dp)) {
                //dtt banner
                Image(painterResource(id = R.drawable.baseline_house_24), contentDescription = "")
                // dtt link

                Column(modifier = Modifier.padding(start = 24.dp, bottom = 250.dp)) {
                    Text(
                        stringResource(id = R.string.by_me),
                        color = Color.Black
                    )
                    TextButton(
                        onClick = {
                            context.startActivity(intent)

                        }, colors = ButtonDefaults.textButtonColors(

                        )
                    ) {
                        Text(
                            stringResource(id = R.string.link),
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                }
            }
        }

    }


}
