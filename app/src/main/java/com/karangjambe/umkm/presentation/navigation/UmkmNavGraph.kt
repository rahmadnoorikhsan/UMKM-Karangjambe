package com.karangjambe.umkm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.karangjambe.umkm.presentation.screen.detail.detailScreenRoute
import com.karangjambe.umkm.presentation.screen.home.homeScreenRoute
import com.karangjambe.umkm.presentation.screen.splash.splashScreenRoute

@Composable
fun UmkmNavGraph(
    navController: NavHostController,
    showSnackBarMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        splashScreenRoute(navController = navController)
        homeScreenRoute(showSnackBarMessage = showSnackBarMessage, navController = navController)
        detailScreenRoute(showSnackBarMessage = showSnackBarMessage, navController = navController)
    }
}