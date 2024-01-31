package com.karangjambe.umkm.presentation.screen.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.karangjambe.umkm.presentation.navigation.Screen

fun NavGraphBuilder.detailScreenRoute(navController: NavController) {
    composable(
        route = Screen.Detail.route,
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        ),
    ) {
        val id = it.arguments?.getInt("id", 0)

        DetailScreen(navController = navController, id = id ?: 0)
    }
}