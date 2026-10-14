package com.example.erpsystem.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.erpsystem.data.remote.ApiClient
import com.example.erpsystem.data.remote.ApiService
import com.example.erpsystem.data.repository.AuthRepository
import com.example.erpsystem.ui.screens.LoginScreen
import com.example.erpsystem.ui.screens.SplashScreen
import com.example.erpsystem.ui.screens.WelcomeScreen
import com.example.erpsystem.viewmodel.LoginViewModel

@Composable
fun NavGraph() {

    val navController= rememberNavController()

    val apiService = ApiClient.retrofitInstance.create(ApiService::class.java)
    val authRepository = AuthRepository(apiService)

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

            val loginViewModel : LoginViewModel = viewModel(
                factory = object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return LoginViewModel(authRepository) as T
                    }
                }
            )

            LoginScreen(
                adminType = adminType,
                viewModel = loginViewModel,
                onNavigateBack = { navController.popBackStack() },
                onLoginSuccess = {role->
                    if (role=="ERP_ADMIN"){
                        navController.navigate(Screen.ErpDashboard) {
                            popUpTo(Screen.Welcome) { inclusive = true }
                        }
                    }else {
                        navController.navigate(Screen.DeptDashboard) {
                            popUpTo(Screen.Welcome) { inclusive = true }
                        } }
                }
            )

        }

//        composable<Screen.ErpDashboard> {  }
        composable<Screen.ErpDashboard> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Master ERP Admin Dashboard")
            }
        }
        composable<Screen.DeptDashboard> {  }

    }

}