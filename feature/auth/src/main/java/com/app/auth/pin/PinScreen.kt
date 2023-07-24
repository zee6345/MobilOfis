package com.app.auth.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.pin.components.CustomKeyboard
import com.app.auth.pin.components.PinTextField
import com.app.auth.pin.navigation.resetPinNavigationRoute


@Composable
fun PinScreen(navController: NavController) {

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
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    text = "Do you want to set a PIN?",
                    style = TextStyle(color = Color.White, fontSize = 22.sp)
                )
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
            PinInputView(navController, length = 5) { pin ->
                enteredPin = pin

                if (pin.length == 5) {
                    navController.navigate(resetPinNavigationRoute)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(modifier = Modifier.wrapContentSize(), shape = RoundedCornerShape(14.dp)) {
                Text(
                    text = "Skip",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 18.dp),
                    color = Color(0xFF203657)
                )
            }


        }

    }
}

@Composable
fun PinInputView(
    navController: NavController,
    length: Int,
    onPinEntered: (String) -> Unit
) {
    val pinValue = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(20.dp),
        color = Color.Transparent
    ) {
        PinTextField(
            otpText = pinValue.value,
            onOtpTextChange = { value, _ ->
                pinValue.value = value.take(length)
            }
        )
    }

    CustomKeyboard(navController, "PinScreen") { key ->
        if (key == "del") {
            if (pinValue.value.isNotEmpty()) {
                pinValue.value = pinValue.value.dropLast(1)
            }
        } else {
            if (pinValue.value.length < length) {
                pinValue.value += key
            }
        }

        if (pinValue.value.length == length) {
            onPinEntered(pinValue.value)
        }
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun pinScreenPreview() {
    val navController = rememberNavController()
    PinScreen(navController)

}