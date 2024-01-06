package com.karangjambe.umkm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun UmkmNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
   NavHost(navController = navController, startDestination = Screen.Splash.route, modifier = modifier) {

   }
}