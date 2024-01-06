package com.karangjambe.umkm.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.karangjambe.umkm.presentation.navigation.UmkmNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UmkmApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(modifier = modifier) { contentPadding ->
        UmkmNavGraph(navController = navController, modifier = Modifier.padding(contentPadding))
    }
}