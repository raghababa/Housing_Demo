package com.example.dttproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dttproject.view.HouseDetailScreen
import com.example.dttproject.view.InformationScreen
import com.example.dttproject.view.OverViewScreen
import com.example.dttproject.view.SplashScreen
import com.example.dttproject.viewmodel.MainViewModel

object EndPoints {
    const val ID = "id"
}
@ExperimentalComposeUiApi
@Composable
// to know the state of the application and back correctly and navigate
fun DTTNavGraph() {
    val viewModel = MainViewModel()
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController, startDestination = MyScreens.Splash.name) {
        // Splash screen
        composable(MyScreens.Splash.name) {
            viewModel.getAllHouseList()
            SplashScreen(actions )

        }
        //overviewScreen
        composable(BottomNavigationItem.Home.screen_route){
            viewModel.sortAllHouses()
            OverViewScreen(viewModel ,actions)
        }
        // HouseDetailScreen
        composable(
            "${MyScreens.HouseDescription.name}/{id}/{distance}",
            arguments = listOf(navArgument(EndPoints.ID) { type = NavType.StringType }

            )
        ) {
            val isIdNo = it.arguments?.getString(EndPoints.ID)
                ?: throw IllegalStateException("'House Id No' shouldn't be null")
            viewModel.getHouseById(isIdNo = isIdNo)

            val distance = it.arguments?.getString("distance")
            if (distance != null) {
                HouseDetailScreen( actions,distance, viewModel)
            }
        }
        //Information screen
        composable(BottomNavigationItem.Information.screen_route){
            InformationScreen(actions)
        }

    }
}
//navigation functions
class MainActions(navController: NavHostController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoHouseDetails: (isIdNo:String, distance:String) -> Unit = { isIdNo, distance ->
        navController.navigate("${MyScreens.HouseDescription.name}/$isIdNo/$distance")
    }

    val gotoOtherCompose: (String) -> Unit = {route ->
        navController.navigate(route)
    }

}