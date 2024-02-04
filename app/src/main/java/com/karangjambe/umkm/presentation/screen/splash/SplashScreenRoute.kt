package com.karangjambe.umkm.presentation.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.karangjambe.umkm.presentation.navigation.Screen

fun NavGraphBuilder.splashScreenRoute(navController: NavController) {
    composable(
        route = Screen.Splash.route,
    ) {
        SplashScreen(navController = navController)
    }
}