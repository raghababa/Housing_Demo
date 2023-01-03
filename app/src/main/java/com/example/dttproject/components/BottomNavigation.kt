package com.example.dttproject.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dttproject.navigation.BottomNavigationItem
import com.example.dttproject.navigation.MainActions

@Composable
fun BottomNavigation(
    navController: NavController,
    actions: MainActions
) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Information

    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.onSurface,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(

                icon = { Icon(
                    painterResource(id = item.icon),
                    contentDescription = "",

                )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                selected = currentRoute == item.screen_route,
                onClick = {
                    actions.gotoOtherCompose.invoke(item.screen_route)

                }
            )
        }
    }
}
