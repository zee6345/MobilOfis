package com.app.uikit.views

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun BackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val dispatcher = context as? OnBackPressedDispatcherOwner
    val callback = rememberUpdatedState(onBack)

    DisposableEffect(Unit) {
        val backCallback = object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    callback.value()
                }
            }
        }

        dispatcher?.onBackPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}
