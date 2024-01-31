package com.karangjambe.umkm.presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.karangjambe.umkm.presentation.navigation.UmkmNavGraph

@Composable
fun UmkmApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars,
        modifier = modifier
    ) { contentPadding ->
        UmkmNavGraph(navController = navController, modifier = Modifier.padding(contentPadding))
    }
}