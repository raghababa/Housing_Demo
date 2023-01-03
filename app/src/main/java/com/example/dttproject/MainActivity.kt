package com.example.dttproject

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.dttproject.navigation.DTTNavGraph
import com.example.dttproject.ui.theme.DTTProjectTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY



@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.

        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        setContent {
            DTTProjectTheme {
                val systemUiController = rememberSystemUiController()
                window?.statusBarColor = Color.Red.toArgb()
                SideEffect {
                     systemUiController.isSystemBarsVisible = false
                }


                Surface() {

                    getMyCurrentLocation()
                    DTTNavGraph()
                }
            }
        }

    }


    private fun getMyCurrentLocation() {
        if (checkPermission()) {

            if (isMyLocationEnabled()) {
                //getting latitude and longitude
                fusedLocationProviderClient.getCurrentLocation(
                    PRIORITY_HIGH_ACCURACY, null
                )
                    .addOnSuccessListener { location ->
                        val latitude = location.latitude
                        val longitude = location.longitude
                        if (!location.equals(null)) {
                            val sharedPreferences =
                                this.getPreferences(Context.MODE_PRIVATE)
                                    ?: return@addOnSuccessListener
                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            editor.putString("lat_key", latitude.toString())
                            editor.putString("long_key", longitude.toString())
                            editor.apply()
                        }
                    }

            } else {
                //open settings here
                Toast.makeText(this, "Please turn on your location", Toast.LENGTH_SHORT).show()
                val sharedPreferences =
                    this.getPreferences(Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("lat_key", "")
                editor.putString("long_key", "")
            }

        } else {
            //permission request here
            requestPermission()
        }
    }

    private fun isMyLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION
            ),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

    private fun checkPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this, ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            return true
        }
        return false
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getMyCurrentLocation()
            }
        }
    }
}




