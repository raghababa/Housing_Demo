package com.example.dttproject.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dttproject.R
import com.example.dttproject.components.*
import com.example.dttproject.model.HouseItem
import com.example.dttproject.model.ViewState
import com.example.dttproject.navigation.MainActions
import com.example.dttproject.viewmodel.MainViewModel
import java.text.NumberFormat
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.roundToLong
import kotlin.math.sin


@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalComposeUiApi
@Composable
fun OverViewScreen(
    viewModel: MainViewModel,
    actions: MainActions
) {
// defining states
    when (val result = viewModel.houses.value) {
        ViewState.Loading -> Text(text = "Loading")
        is ViewState.Error -> Text(text = "Error found: ${result.exception}")
        is ViewState.Success -> {
            HouseList(result.data, actions)
        }

        ViewState.Empty -> Text(text = "No results found!")
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@ExperimentalComposeUiApi
@Composable
fun HouseList(houseList: List<HouseItem>, actions: MainActions) {

    val context = LocalContext.current
    val search = remember {
        mutableStateOf("")
    }
    val listState = rememberLazyListState()
    val activity = (context as Activity)
    val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE) ?: return

    // get the lat and long
    val mLatitude = sharedPreferences.getString("lat_key", "")
    Context.MODE_PRIVATE

    val mLongitude = sharedPreferences.getString("long_key", "")
    Context.MODE_PRIVATE

    // calculating the distance
    fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    fun distanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): String {

        val theta = lon1 - lon2
        var dist = sin(deg2rad(lat1)) * sin(deg2rad(lat2)) +
                cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(theta))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        dist *= 1.609344
        return dist.roundToLong().toString()

    }

// to exit from the app
    BackHandler {
        activity.finish()
    }
// the view of the screen
    Scaffold(
        topBar = {
            Text(
                stringResource(id = R.string.title),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = 2,
                modifier = Modifier.padding(start = 20.dp, top = 34.dp)
            )
        },
        bottomBar = {
            BottomNavigation(navController = NavController(context), actions)
        }
    ) {
        Column() {

            //search
            Spacer(modifier = Modifier.height(16.dp))
            SearchView(search)

            LazyColumn(
                state = listState, contentPadding = PaddingValues(top = 0.dp, bottom = 50.dp),
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {

//         All houses list view
                if (search.value == "") {
                    items(
                        houseList
                    ) { house ->
                        var distance = "-"
                        if ((mLatitude != "") && (mLongitude != "")) {
                            distance = distanceInKm(
                                mLatitude!!.toDouble(),
                                mLongitude!!.toDouble(),
                                house.latitude,
                                house.longitude
                            )
                        }

                        OverViewCard(
                            house.image,
                            NumberFormat.getInstance().format(
                                house.price
                            ),
                            house.zip,
                            house.city,
                            house.bedrooms,
                            house.bathrooms,
                            house.size,
                            distance
                        ) {
                            actions.gotoHouseDetails.invoke((house.id).toString(), distance)
                        }
                    }

                } else {

                    val searchedList = houseList.filter {
                        it.zip.contains(
                            search.value,
                            ignoreCase = true
                        )
                    }

                    //empty state
                    if (searchedList.isEmpty()) {
                        item {

                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colors.background),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                Image(
                                    painterResource(id = R.drawable.search_state_empty),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(300.dp)
                                        .padding(top = 150.dp, bottom = 10.dp)

                                    )
                                //Empty result
                                Text(
                                    stringResource(id = R.string.empty_result),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 24.dp),
                                    color = MaterialTheme.colors.surface,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    stringResource(id = R.string.another_search),
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colors.surface,
                                    textAlign = TextAlign.Center
                                )

                            }

                        }


                    } else {
                        //filtered houses
                        items(
                            houseList.filter {
                                it.zip.contains(
                                    search.value,
                                    ignoreCase = true
                                )
                            }
                        ) { house ->
                            var distance = "-"
                            if ((mLatitude != "") && (mLongitude != "")) {
                                distance = distanceInKm(
                                    mLatitude!!.toDouble(),
                                    mLongitude!!.toDouble(),
                                    house.latitude,
                                    house.longitude
                                )
                            }

                            OverViewCard(
                                house.image,
                                house.price.toString(),
                                house.zip,
                                house.city,
                                house.bedrooms,
                                house.bathrooms,
                                house.size,
                                distance
                            ) {
                                actions.gotoHouseDetails.invoke((house.id).toString(), distance)
                            }
                        }
                    }
                }
            }
        }
    }
}












