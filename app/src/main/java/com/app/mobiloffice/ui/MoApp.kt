package com.app.mobiloffice.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.app.mobiloffice.navigation.MoNavHost


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoApp(
    appState: MoState = rememberMoState(),
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(modifier = Modifier) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MoNavHost(appState = appState)
        }
    }


}