package com.karangjambe.umkm.presentation.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.karangjambe.umkm.presentation.navigation.Screen

fun NavGraphBuilder.homeScreenRoute(navController: NavController) {
    composable(Screen.Home.route) {
        HomeScreen()
    }
}