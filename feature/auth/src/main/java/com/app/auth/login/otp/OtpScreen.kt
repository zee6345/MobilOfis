package com.app.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.login.components.utils.TimerTextView
import com.app.auth.login.navigation.pinNavigationRoute
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpScreen(navController: NavController) {

//    val keyboardController = LocalSoftwareKeyboardController.current
//    val coroutineScope = rememberCoroutineScope()
//
//    coroutineScope.launch {
//        keyboardController?.show() // Open the soft input keyboard
//    }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.2f),
            color = Color(0xFF203657),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        text = "Enter SMS code", style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(vertical = 10.sdp)
                    )
                    Text(
                        text = "Please enter the SMS code sent to the number ***68-06.",
                        style = TextStyle(
                            color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                        )
                    )
                }

            }
        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            OtpView()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 17.dp)
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(15.dp),
                        )
                        .background(
                            color = Color(0xFFE7F0F9),
                        )
                ) {

                   TimerTextView()

                }

                ClickableText(modifier = Modifier.padding(5.dp),
                    text = AnnotatedString(text = "Re-send SMS code"),
//                    color = Color(0xFF203657),
                    onClick = { })
            }


//            CustomKeyboardOtp()

            ScrollableButtons(navController = navController)

        }


    }


}

@Composable
private fun ScrollableButtons(navController: NavController) {
    var offset by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                orientation = Orientation.Vertical,
                // Scrollable state: describes how to consume
                // scrolling delta and update offset
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            ),
//        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 17.dp)
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFE7EEFC), // Change the background color here
                    contentColor = Color(0xFF203657) // Change the text color here if needed
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)

            ) {
                Text(
                    "Close",
                    modifier = Modifier.padding(vertical = 6.dp),
                    style = TextStyle(
                        fontSize = 17.sp, shadow = null
                    )
                )
            }

            Button(
                onClick = { navController.navigate(pinNavigationRoute) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF203657), contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    "Next", modifier = Modifier.padding(vertical = 6.dp), style = TextStyle(
                        color = Color.White, fontSize = 17.sp, shadow = null
                    )
                )
            }
        }
    }
}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun OtpScreenPreview() {
    val navController = rememberNavController()
    OtpScreen(navController)
}
