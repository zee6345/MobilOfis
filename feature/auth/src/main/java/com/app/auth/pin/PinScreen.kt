package com.app.auth.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.pin.navigation.resetPinNavigationRoute
import com.app.auth.pin.navigation.successfulRegistration
import com.app.network.helper.Keys
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.views.AutoResizedText
import com.app.uikit.views.CustomKeyboard
import com.app.uikit.views.PinInputView
import com.app.uikit.views.PinTextField
import ir.kaaveh.sdpcompose.sdp


@Composable
fun PinScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F7FA))
    ) {

        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.2f),
            color = Color(0xFF203657),
        ) {


            Column(Modifier.fillMaxSize()) {

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(0.1f)

                ) {


                    CurvedBottomBox(
                        color = Color(0xff334b66),
                        curveHeight = 30.dp
                    )

                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(0.2f)
                        .padding(start = 20.sdp, end = 20.sdp, top = 5.sdp, bottom = 5.sdp)
                        .background(color = Color(0xFF203657))
                ) {

                    AutoResizedText(
                        modifier = Modifier
                            .align(Alignment.CenterStart),
                        text = stringResource(R.string.do_you_want_to_set_a_pin),
                        style = TextStyle(color = Color.White, fontSize = 18.sp)
                    )
                }

            }

        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            var enteredPin by remember { mutableStateOf("") }
            PinInputView(length = 5, { pin ->
                enteredPin = pin

                if (pin.length == 5) {

                    viewModel.session.put(Keys.KEY_PIN, pin)
                    navController.navigate(resetPinNavigationRoute)
                }
            }, {

            })

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        navController.navigate(successfulRegistration)
                    },
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 18.dp),
                    color = Color(0xFF203657)
                )
            }


        }

    }
}

//@Composable
//fun PinInputView(
//    length: Int,
//    onPinEntered: (String) -> Unit,
//    onKeyEntered: (String) -> Unit
//) {
//    val pinValue = remember { mutableStateOf("") }
//
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.Transparent)
//            .padding(20.dp),
//        color = Color.Transparent
//    ) {
//        PinTextField(
//            otpText = pinValue.value,
//            onOtpTextChange = { value, _ ->
//                pinValue.value = value.take(length)
//            }
//        )
//    }
//
//    CustomKeyboard { key ->
//        if (key == "del") {
//            if (pinValue.value.isNotEmpty()) {
//                pinValue.value = pinValue.value.dropLast(1)
//            }
//        } else if (key == "fngr") {
//            onKeyEntered("fngr")
//        } else {
//            if (pinValue.value.length < length) {
//                pinValue.value += key
//            }
//        }
//
//        if (pinValue.value.length == length) {
//            onPinEntered(pinValue.value)
//        }
//    }
//}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun pinScreenPreview() {
    PinScreen(rememberNavController())

}