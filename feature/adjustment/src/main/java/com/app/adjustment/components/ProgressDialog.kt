package com.app.adjustment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ShowProgressDialog(isLoading: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = { isLoading.value = false },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment= Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                ){

                CircularProgressIndicator()

                Text("Please wait...",
                    Modifier.padding(start = 20.sdp))
            }

        }
    }
}