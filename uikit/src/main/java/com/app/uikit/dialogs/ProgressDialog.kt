package com.app.uikit.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ehsanmsz.mszprogressindicator.progressindicator.BallSpinFadeLoaderProgressIndicator

@Composable
fun ShowProgressDialog(isLoading: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = { isLoading.value = false },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
//                .height(70.dp)
                .background(Color.Transparent,
//                    shape = RoundedCornerShape(8.dp)
                )
//                .padding(horizontal = 30.dp, vertical = 10.dp)
        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//
//                ){
//
//                CircularProgressIndicator()
//
//                Text("Please wait...", Modifier.padding(start = 20.sdp))
//            }


            BallSpinFadeLoaderProgressIndicator(
                modifier = Modifier,
                color = Color.White,
                animationDuration = 800,
                diameter = 40.dp,
                isClockwise = true,

                )

        }
    }
}