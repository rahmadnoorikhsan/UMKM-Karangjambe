package com.karangjambe.umkm.presentation.screen.detail

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
        enterTransition = {
            scaleIn(
                animationSpec = tween(220, delayMillis = 90),
                initialScale = 1.1f
            ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
        },
        exitTransition = {
            scaleOut(
                animationSpec = tween(
                    durationMillis = 220,
                    delayMillis = 90
                ), targetScale = 0.9f
            ) + fadeOut(tween(delayMillis = 90))
        },
        popEnterTransition = {
            scaleIn(
                animationSpec = tween(220, delayMillis = 90),
                initialScale = 0.9f
            ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
        },
        popExitTransition = {
            scaleOut(
                animationSpec = tween(
                    durationMillis = 220,
                    delayMillis = 90
                ), targetScale = 1.1f
            ) + fadeOut(tween(delayMillis = 90))
        }
    ) {
        val id = it.arguments?.getInt("id", 0)

        DetailScreen(navController = navController, id = id ?: 0)
    }
}