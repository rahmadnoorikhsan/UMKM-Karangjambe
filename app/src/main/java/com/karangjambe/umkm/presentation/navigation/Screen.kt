package com.karangjambe.umkm.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding: Screen("onBoarding")
    object Home : Screen("home")
}