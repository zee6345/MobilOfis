package com.app.auth.pin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.auth.pin.navigation.successfulRegistration
import com.app.network.helper.Keys
import com.app.network.utils.Message
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.bottomSheet.FingerPrintModalBottomSheet
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.views.AutoResizedText
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay


@Composable
fun RepeatPin(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {

    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    var enteredPin by remember { mutableStateOf("") }
    val isPinMatched = remember{ mutableStateOf(false) }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color(0xFFF3F7FA))) {

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
                        .padding(start = 20.sdp, end = 20.sdp, top = 5.sdp, bottom = 10.sdp)
                        .background(color = Color(0xFF203657))
                ) {

                    Row(
                        Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {

//                        Image(
//                            painterResource(id = R.drawable.ic_back_arrow),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(width = 32.dp, height = 25.dp)
//                                .clickable { navController.popBackStack() }
//                        )

                        AutoResizedText(
                            modifier = Modifier
                                .padding(horizontal = 12.sdp),
                            text = stringResource(com.app.auth.R.string.repeat_pin),
                            style = TextStyle(color = Color.White, fontSize = 22.sp)
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

                    val firstPin = loginViewModel.session[Keys.KEY_PIN]
                    if (firstPin.equals(pin)) {

                        enteredPin = pin

                        showForgetPassBottomSheetSheet.value = !showForgetPassBottomSheetSheet.value

                    } else {

//                        loginViewModel.session.delete(Keys.KEY_PIN)

                        isPinMatched.value = true

                    }

                }
            }, {
                showForgetPassBottomSheetSheet.value = !showForgetPassBottomSheetSheet.value
            })

        }

    }

    FingerPrintModalBottomSheet(showForgetPassBottomSheetSheet, onClickThen = {
        loginViewModel.session.delete(Keys.KEY_PIN)
        loginViewModel.session.put(Keys.KEY_USER_PIN, enteredPin)

        //enable login with pin
        loginViewModel.session.put(Keys.KEY_ENABLE_PIN_LOGIN, true)


        navController.navigate(successfulRegistration){
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }

    }, onClickYes = {

        loginViewModel.session.delete(Keys.KEY_PIN)
        loginViewModel.session.put(Keys.KEY_USER_PIN, enteredPin)

        //enable login with pin
        loginViewModel.session.put(Keys.KEY_ENABLE_PIN_LOGIN, true)

        navController.navigate(successfulRegistration){
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }

    })


    //show empty field toast
    if (isPinMatched.value) {
        RoundedCornerToast("Pin not matched", Toast.LENGTH_SHORT, context)

        LaunchedEffect(Unit) {
            delay(3000)
            isPinMatched.value = false
        }

    }


}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun RepeatPinScreenPreview() {
    val navController = rememberNavController()
    RepeatPin(navController)
}