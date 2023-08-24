package com.app.adjustment.changepin.confirmpin

import android.content.Context
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.app.adjustment.changepin.currentpin.PinInputView
import com.app.uikit.bottomSheet.PinChangedBottomSheet
import com.app.network.helper.Keys
import com.app.network.utils.Message
import com.app.network.viewmodel.LoginViewModel


@Composable
fun RepeatPin(navController: NavController, viewModel:LoginViewModel = hiltViewModel()) {

    var enteredPin by remember { mutableStateOf("") }
    val context: Context = LocalContext.current
    val pinChanged = rememberSaveable { mutableStateOf(false) }

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
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)

            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    text = stringResource(R.string.repeat_the_pin),
                    style = TextStyle(color = Color.White, fontSize = 22.sp)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))


            PinInputView(navController, length = 5) { pin ->
                enteredPin = pin

                if (pin.isNotEmpty() && pin.length == 5) {
                    val firstPin = viewModel.session[Keys.KEY_PIN]

                    if (pin == firstPin) {

                        //store new pin
                        viewModel.session.put(Keys.KEY_USER_PIN, pin)

                        pinChanged.value = true



                    } else {
                        Message.showMessage(context, "Pin not matched")
                    }

                } else {
                    Message.showMessage(context, "Pin must be 5 digit!")
                }


            }

        }

    }

    PinChangedBottomSheet(pinChanged, navController)

}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun RepeatPinScreenPreview() {
    val navController = rememberNavController()
    RepeatPin(navController)
}