package com.app.auth.pin

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
import androidx.compose.material.Divider
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
import androidx.compose.ui.graphics.ColorFilter
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
import com.app.auth.R
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.home.navigation.homeScreenRoute
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.utils.Message
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.bottomSheet.FingerPrintModalBottomSheet
import ir.kaaveh.sdpcompose.sdp

private const val TAG = "WelcomePinScreen"

@Composable
fun WelcomePinScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    var enteredPin by remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)
    username.value = userDetails.userName


    Column(modifier = Modifier.fillMaxSize()) {

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

                    Text(
                        modifier = Modifier.align(Alignment.BottomStart),
                        text = stringResource(id = R.string.text_welcome) + ",\n${username.value}",
                        style = TextStyle(color = Color.White, fontSize = 22.sp)
                    )
                }

            }

        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))


                PinInputView(length = 5, { pin ->
                    enteredPin = pin

                    if (pin.isNotEmpty() && pin.length == 5) {
                        val finalPin = viewModel.session[Keys.KEY_USER_PIN]
                        if (finalPin.equals(pin)) {
                            navController.navigate(homeScreenRoute)
                        } else {
                            Message.showMessage(context, "Wrong pin entered!")
                        }
                    }
                }, {
                    showForgetPassBottomSheetSheet.value = !showForgetPassBottomSheetSheet.value
                })

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Divider(
                    color = Color(0xFFD1D4D9),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp) // Replace with your desired modifier
                )
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 17.dp)
                        .clickable {

                            navController.navigate(loginNavigationRoute) {
                                popUpTo(loginNavigationRoute) { inclusive = true }
                            }

                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.exit_icon),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color(com.app.home.R.color.background_card_blue)),
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 12.dp),
                    )
                    Text(
                        text = stringResource(R.string.enter_in_a_different_way),
                        style = TextStyle(color = Color(0xFF667080), fontSize = 14.sp),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 12.dp, horizontal = 6.dp)
                    )
                }


            }


        }

    }

    FingerPrintModalBottomSheet(showForgetPassBottomSheetSheet, onClickThen = {
        navController.navigate(homeScreenRoute)
    }, onClickYes = {
        navController.navigate(homeScreenRoute)
    })
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun welcomepinScreenPreview() {
    val navController = rememberNavController()
    WelcomePinScreen(navController)
}