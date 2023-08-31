package com.app.auth.login.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.login.navigation.loginNavigationRoute
import com.app.auth.login.otp.otpScreen.userName
import com.app.auth.pin.navigation.pinNavigationRoute
import com.app.auth.pin.navigation.welcomePinScreen
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorResponse
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.utils.Message
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.SharedModel
import com.app.uikit.views.OtpView
import com.app.uikit.views.TimerTextsView
import com.app.uikit.views.TimerTextsView20S
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


object otpScreen {
    var userName: String? = null
}

@Composable
fun OtpScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val otpValue = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val isTimerStarted = remember { mutableStateOf(false) }
    val otpData by viewModel.otp.collectAsState()
    var offset by remember { mutableStateOf(0f) }
    val otpCount = remember { mutableStateOf(6) }
    val coroutine = rememberCoroutineScope()

    val loginType = SharedModel.init().loginType.value

    //set initial value for OTP view
    when (loginType) {
        0 -> otpCount.value = 5
        1 -> otpCount.value = 6
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F7FA))
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
                        .padding(start = 20.sdp, end = 20.sdp, top = 5.sdp, bottom = 10.sdp)
                        .background(color = Color(0xFF203657))
                ) {

                    Column(
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        if (loginType == 1) {
                            Text(
                                text = "Enter the Google code",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(bottom = 5.sdp)
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.enter_otp_code),
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(bottom = 5.sdp)
                            )
                            Text(
                                text = when (loginType) {
                                    0 -> stringResource(R.string.text_send_otp)
                                    1 -> stringResource(R.string.text_auth_otp)
                                    else -> ""
                                },
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }

            }

        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween
        ) {


            //google auth otp
            Column {
                OtpView(otpCount.value) {
                    otpValue.value = it
                }

                if (loginType == 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 17.dp)
                            .padding(horizontal = 22.dp),
                        contentAlignment = Alignment.Center
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

                            TimerTextsView()

                        }
                    }
                } else {


                    //normal otp

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

//                            CountdownTimerSeconds(otpValue.value)
                            TimerTextsView20S(onTimerStart = {
                                isTimerStarted.value = true
                            }, onTimerStop = {
                                isTimerStarted.value = false
                            })

                        }

                        Text(
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    if (!isTimerStarted.value) {
                                        coroutine.launch {
                                            Message.showMessage(context, "OTP send again!")
                                        }
                                    }
                                },
                            text = stringResource(R.string.re_send_sms_code),
                            style = TextStyle(
                                color = if (!isTimerStarted.value) Color(0xFF203657) else Color(
                                    0xFF859DB5
                                )
                            )
                        )
                    }
                }
            }



            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                        onClick = {
                            navController.navigate(loginNavigationRoute) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(R.color.border_grey), // Change the background color here
                            contentColor = Color(0xFF203657) // Change the text color here if needed
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)

                    ) {
                        Text(
                            stringResource(R.string.close),
                            modifier = Modifier.padding(vertical = 6.dp),
                            style = TextStyle(
                                fontSize = 17.sp, shadow = null
                            )
                        )
                    }

                    Button(
                        onClick = {
                            if (otpValue.value.isNotEmpty()) {
                                if (otpValue.value.length == otpCount.value) {


                                    viewModel.loginAuthVerification(
                                        LoginVerificationRequest(
                                            userName = userName!!,
                                            verfication = otpValue.value.toInt(),
                                            channel = "INT",
                                        )
                                    )


                                } else {
                                    Message.showMessage(context, "OTP must be 6 digit..")
                                }

                            } else {
                                Message.showMessage(context, "Please add your OTP..")
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF203657), contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            stringResource(R.string.next),
                            modifier = Modifier.padding(vertical = 6.dp),
                            style = TextStyle(
                                color = Color.White, fontSize = 17.sp, shadow = null
                            )
                        )
                    }
                }
            }

        }


    }

    otpData?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true

            }

            is DataState.Error -> {
                isLoading.value = false

                val errorResponse: ErrorResponse =
                    Converter.fromJson(it.errorMessage, ErrorResponse::class.java)

//                ErrorState(context, it.errorMessage).handleError()
                if (errorResponse.code.equals("ERROR.TOTP_2FA_VERIFICATION_NOT_MATCH", true)) {

                    LaunchedEffect(Unit) {
                        coroutine.launch {
                            Message.showMessage(context, "Incorrect Google Authenticator Code")
                        }
                    }
                } else {

                }

                //on error remove keys
//                viewModel.session.delete(Keys.KEY_TOKEN)

            }

            is DataState.Success -> {
                isLoading.value = false

                val loginVerifyResponse = it.data as LoginVerifyResponse
                loginVerifyResponse?.apply {


                    //cache login verify response
                    val strJson = Converter.toJson(loginVerifyResponse)
                    viewModel.session.put(Keys.KEY_USER_DETAILS, strJson)


                    //check if pin already set
                    val pin = viewModel.session[Keys.KEY_USER_PIN]

                    //route to OTP
                    LaunchedEffect(Unit) {
                        if (pin.isNullOrEmpty()) {
                            navController.navigate(pinNavigationRoute) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(welcomePinScreen) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        }
                    }

                }

            }
        }
    }


    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }

}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun OtpScreenPreview() {
    val navController = rememberNavController()
    OtpScreen(navController)
}
