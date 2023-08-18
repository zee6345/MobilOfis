package com.app.adjustment.changepin.currentpin

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
//import com.app.uikit.CustomKeyboard
//import com.app.uikit.PinTextField
import com.app.adjustment.changepin.newpin.navigation.adjustmentToNewPin
import com.app.network.helper.Keys
import com.app.network.utils.Message
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.views.CustomKeyboard
import com.app.uikit.views.PinTextField


@Composable
fun CurrentPin(navController: NavController, viewModel: LoginViewModel= hiltViewModel()) {

    val context: Context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(R.color.border_light_grey))
    ) {
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
                Row(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ){

                    Image(
                        painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 32.dp, height = 25.dp)
                            .clickable { navController.popBackStack() }
                    )

                    Text(
                        text = stringResource(R.string.enter_your_current_pin),
                        style = TextStyle(color = Color.White, fontSize = 22.sp),
                        modifier = Modifier.padding(12.dp)
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
            PinInputView(navController, length = 5) { pin ->
                enteredPin = pin

                val oldPin = viewModel.session[Keys.KEY_USER_PIN]

                if (pin.isNotEmpty() && pin.length == 5) {
                    if (pin == oldPin){

                        navController.navigate(adjustmentToNewPin)

                    } else {
                        Message.showMessage(context, "Invalid Pin!")
                    }

                } else {
                    Message.showMessage(context, "Pin must be 5 digit!")
                }
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

    CustomKeyboard { key ->
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
    CurrentPin(navController)

}