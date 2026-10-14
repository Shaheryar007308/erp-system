package com.example.erpsystem.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.erpsystem.ui.screens.SplashScreen
import com.example.erpsystem.ui.screens.WelcomeScreen

@Composable
fun NavGraph() {

    val navController= rememberNavController()

    NavHost(navController = navController , startDestination = Screen.Splash) {

        composable<Screen.Splash> {
            SplashScreen(
                onNavigateToWelcome = {
                    navController.navigate(Screen.Welcome){
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.Welcome> {
            WelcomeScreen(
                onRoleSelected = {
                    role-> navController.navigate(Screen.Login(role))
                }
            )
        }

        composable<Screen.Login>{ backStageEntry->
            val routeArgs= backStageEntry.toRoute<Screen.Login>()
            val adminType=routeArgs.adminType
        }

        composable<Screen.ErpDashboard> {  }
        composable<Screen.DeptDashboard> {  }

    }

}