package com.karangjambe.umkm.presentation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.karangjambe.umkm.presentation.navigation.UmkmNavGraph
import kotlinx.coroutines.launch

@Composable
fun UmkmApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        modifier = modifier
    ) { contentPadding ->
        UmkmNavGraph(
            navController = navController,
            showSnackBarMessage = { message ->
                scope.launch {
                    snackBarHostState.showSnackbar(message)
                }
            },
            modifier = Modifier.padding(contentPadding)
        )
    }
}