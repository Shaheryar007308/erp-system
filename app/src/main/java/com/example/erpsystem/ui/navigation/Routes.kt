package com.example.erpsystem.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable data object Splash : Screen
    @Serializable data object Welcome : Screen

    // Pass the target role parameter into the login route cleanly
    @Serializable data class Login(val adminType: String) : Screen // "ERP_ADMIN" or "DEPT_ADMIN"

    @Serializable data object ErpDashboard : Screen
    @Serializable data object DeptDashboard : Screen
}