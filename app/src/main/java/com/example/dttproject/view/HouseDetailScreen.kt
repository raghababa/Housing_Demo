package com.example.dttproject.view

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.myproject.R
import com.example.dttproject.components.HouseDetailItem
import com.example.dttproject.model.DetailViewState
import com.example.dttproject.navigation.MainActions
import com.example.dttproject.viewmodel.MainViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.text.NumberFormat

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HouseDetailScreen(
    actions: MainActions,
    distance: String,
    viewModel: MainViewModel
) {
    val domain = "https://intern.development.d-tt.dev/"

    when (val result = viewModel.houseDetails.value) {
        DetailViewState.Loading -> Text(text = "Loading")
        is DetailViewState.Error -> Text(text = "Error found: ${result.exception}")
        is DetailViewState.Success -> {
            val house = result.data

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .height(260.dp)
                            .background(MaterialTheme.colors.primary)
                    ) {

                        Image(
                            rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = domain.plus(house.image))
                                    .allowHardware(false)
                                    .build()
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        )

                        TopAppBar(
                            backgroundColor = Color.Transparent.copy(alpha = 0f),
                        ) {
                            Image(
                                painterResource(id = R.drawable.ic_back),
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable { actions.upPress.invoke() }
                                    .size(55.dp)
                                    .padding(top = 28.dp, start = 24.dp)

                            )

                        }

                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(MaterialTheme.colors.onSurface)
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))

                    ) {
                        Column {
                            HouseDetailItem(
                                NumberFormat.getInstance().format(
                                    house.price
                                ),
                                house.bedrooms,
                                house.bathrooms,
                                house.size,
                                distance,
                                house.description
                            )

                            val singapore = LatLng(house.latitude, house.longitude)
                            val cameraPositionState = rememberCameraPositionState {
                                position = CameraPosition.fromLatLngZoom(singapore, 15f)
                            }
                            GoogleMap(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colors.onSurface)
                                    .height(300.dp)
                                    .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 16.dp),
                                cameraPositionState = cameraPositionState
                            ) {
                                Marker(
                                    state = MarkerState(position = singapore),
                                    title = "",
                                    snippet = ""
                                )
                            }
                        }
                    }
                }
            }

        }
    }
//}



