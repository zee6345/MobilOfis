package com.app.adjustment.changepin

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R

import com.app.uikit.bottomSheet.PinChangedBottomSheet
import com.app.network.helper.Keys
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.views.AutoResizedText
import com.app.uikit.views.PinInputView
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay


const val adjustmentToConfirmPin = "adjustmentToConfirmPin"

@Composable
fun RepeatPin(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    var enteredPin by remember { mutableStateOf("") }
    val context: Context = LocalContext.current
    val pinChanged = rememberSaveable { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorsMessage by remember { mutableStateOf("") }

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
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(20.dp)

                ) {

                    Row(
                        Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Image(
                            painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 32.dp, height = 25.dp)
                                .clickable { navController.popBackStack() }
                        )

                        Spacer(modifier = Modifier.padding(horizontal = 10.sdp))

                        AutoResizedText(
                            modifier = Modifier
                                .padding(12.dp),
                            text = stringResource(R.string.repeat_the_pin),
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 30.1.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFFFFFFF),
                            )
                        )

                    }
                }
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


            PinInputView(length = 5, { pin ->
                enteredPin = pin

                if (pin.isNotEmpty() && pin.length == 5) {
                    val firstPin = viewModel.session[Keys.KEY_PIN]

                    if (pin == firstPin) {

                        //store new pin
                        viewModel.session.put(Keys.KEY_USER_PIN, pin)

                        pinChanged.value = true


                    } else {
                        errorsMessage = "Pin not matched!"
                        showError = true
//                        Message.showMessage(context, "Pin not matched")
                    }

                } else {
                    errorsMessage = "Pin must be 5 digit!"
                    showError = true
//                    Message.showMessage(context, "Pin must be 5 digit!")
                }

            }, {

            })

        }

    }

    PinChangedBottomSheet(pinChanged){
        (context as ComponentActivity).finish()
    }


    if (showError) {
        RoundedCornerToast(errorsMessage, Toast.LENGTH_SHORT, context)

        LaunchedEffect(Unit) {
            delay(3000)
            showError = false
        }
    }

}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun RepeatPinScreenPreview() {
    val navController = rememberNavController()
    RepeatPin(navController)
}