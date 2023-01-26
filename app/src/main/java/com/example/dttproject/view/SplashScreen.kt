package com.example.dttproject.view


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myproject.R
import com.example.dttproject.navigation.BottomNavigationItem
import com.example.dttproject.navigation.MainActions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun SplashScreen(
    actions: MainActions
) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
//                     navigation bar
                    systemUiController.isNavigationBarVisible = false

//                     status bar
                    systemUiController.isStatusBarVisible = false

//                     system bars
        systemUiController.isSystemBarsVisible = false
    }
    LaunchedEffect(key1 = true ){
        delay(3000L)
        actions.gotoOtherCompose.invoke(BottomNavigationItem.Home.screen_route)
    }
    val view = LocalView.current
    SideEffect {
        requestFullScreen(view)
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
       Image(
           painter = painterResource(id = R.drawable.baseline_house_24),
           contentDescription = "",
           alignment = Alignment.Center
       )
    }
}

fun requestFullScreen(view: View) {
    // !! should be safe here since the view is part of an Activity
    val window = view.context.getActivity()!!.window
    WindowCompat.getInsetsController(window, view).hide(
        WindowInsetsCompat.Type.statusBars() or
                WindowInsetsCompat.Type.navigationBars()
    )
}

fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}


