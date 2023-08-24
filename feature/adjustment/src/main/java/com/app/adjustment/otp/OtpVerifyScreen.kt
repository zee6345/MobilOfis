package com.app.adjustment.otp


import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.adjustment.otp.otpScreen.loginType
import com.app.network.models.DataState
import com.app.network.models.requestModels.VerifyChangePasswordRequest
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.VerifyChangePasswordResponse
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.utils.Message
import com.app.network.viewmodel.AdjustmentViewModel
import ir.kaaveh.sdpcompose.sdp

object otpScreen {
    var loginType: Int = 1
}

@Composable
fun OtpVerifyScreen(navController: NavController, viewModel: AdjustmentViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val otpValue = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val changePswdVerify by viewModel.changePassword.collectAsState()
    var offset by remember { mutableStateOf(0f) }
    val otpCount = remember { mutableStateOf(6) }

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    val passChanged = rememberSaveable { mutableStateOf(false) }

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
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        text = stringResource(R.string.enter_otp_code), style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(vertical = 10.sdp)
                    )
                    Text(
                        text = if (loginType == 0) stringResource(R.string.text_send_otp) else if (loginType == 1) stringResource(
                            R.string.text_auth_otp
                        ) else "",
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            Column {
                com.app.uikit.views.OtpView(otpCount.value) {
                    otpValue.value = it
                }

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

                        com.app.uikit.views.TimerTextView()

                    }

                    ClickableText(modifier = Modifier.padding(5.dp),
                        text = AnnotatedString(text = stringResource(R.string.re_send_sms_code)),
                        onClick = {
                            Message.showMessage(context, "OTP send again!")
                        })
                }


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
//                            navController.navigate(loginNavigationRoute)
                        },
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
                            stringResource(R.string.close),
                            modifier = Modifier.padding(vertical = 6.dp),
                            style = TextStyle(
                                fontSize = 17.sp, shadow = null
                            )
                        )
                    }

                    Button(
                        onClick = {
//                    navController.navigate(pinNavigationRoute)
                            if (otpValue.value.isNotEmpty()) {
                                if (otpValue.value.length == otpCount.value) {

                                    viewModel.verifyChangePassword(
                                        VerifyChangePasswordRequest(
                                            userName = userDetails.userName,
                                            verfication = otpValue.value.toInt(),
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


//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 12.dp, bottom = 17.dp)
//                        .padding(horizontal = 18.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.Bottom
//                ) {
//                    Button(
//                        onClick = {
////                            navController.navigate(loginNavigationRoute)
//                        },
//                        shape = RoundedCornerShape(8.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color(0xFFE7EEFC), // Change the background color here
//                            contentColor = Color(0xFF203657) // Change the text color here if needed
//                        ),
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .weight(1f)
//
//                    ) {
//                        Text(
//                            stringResource(R.string.close),
//                            modifier = Modifier.padding(vertical = 6.dp),
//                            style = TextStyle(
//                                fontSize = 17.sp, shadow = null
//                            )
//                        )
//                    }
//
//                    Button(
//                        onClick = {
////                    navController.navigate(pinNavigationRoute)
//                            if (otpValue.value.isNotEmpty()) {
//                                if (otpValue.value.length == otpCount.value) {
//
////                                    val username = MainApp.session[Keys.KEY_USERNAME]
//
//                                    viewModel.loginAuthVerification(
//                                        LoginVerificationRequest(
//                                            userName = userName!!,
//                                            verfication = otpValue.value.toInt(),
//                                            channel = "INT",
//                                        )
//                                    )
//
//
//                                } else {
//                                    Message.showMessage(context, "OTP must be 6 digit..")
//                                }
//
//                            } else {
//                                Message.showMessage(context, "Please add your OTP..")
//                            }
//                        },
//                        shape = RoundedCornerShape(8.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color(0xFF203657), contentColor = Color.White
//                        ),
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .weight(1f)
//                    ) {
//                        Text(
//                            stringResource(R.string.next),
//                            modifier = Modifier.padding(vertical = 6.dp),
//                            style = TextStyle(
//                                color = Color.White, fontSize = 17.sp, shadow = null
//                            )
//                        )
//                    }
//                }
//            }

        }


    }

    changePswdVerify?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
                if (isLoading.value) {
                    com.app.uikit.dialogs.ShowProgressDialog(isLoading)
                } else {

                }
            }

            is DataState.Error -> {
//                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                val data = it.data as VerifyChangePasswordResponse

                data?.apply {
                    isLoading.value = false

                    if (errMessage.equals("SUCCESS", true)){

                        passChanged.value = true

                    } else {
                        Message.showMessage(context, "Failed to verify user!")
                    }


                }

            }
        }
    }

    com.app.uikit.bottomSheet.PassChangedBottomSheet(passChanged, navController)


}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun OtpScreenPreview() {
    val navController = rememberNavController()
    OtpVerifyScreen(navController)
}
