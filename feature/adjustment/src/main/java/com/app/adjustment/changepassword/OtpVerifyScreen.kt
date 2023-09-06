package com.app.adjustment.changepassword


import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.app.adjustment.R
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorResponse
import com.app.network.models.requestModels.VerifyChangePasswordRequest
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.VerifyChangePasswordResponse
import com.app.network.viewmodel.AdjustmentViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.bottomSheet.PassChangedBottomSheet
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.views.OtpView
import com.app.uikit.views.TimerTextsView
import com.app.uikit.views.TimerTextsView20S
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val changePasswordToOTP = "changePasswordToOTP"

@Composable
fun OtpVerifyScreen(
    navController: NavController,
    viewModel: AdjustmentViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val otpValue = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val changePswdVerify by viewModel.verifyChangePassword.collectAsState()
    var offset by remember { mutableStateOf(0f) }
    val otpCount = remember { mutableStateOf(6) }
    val isTimerStarted = remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    val passChanged = rememberSaveable { mutableStateOf(false) }

    val loginType = viewModel.session.getInt(Keys.KEY_LOGIN_TYPE)


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
                                            errorMessage = "OTP send again!"
                                            showError = true
//                                            Message.showMessage(context, "OTP send again!")
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
                            navController.navigate(securityToChangePassword) {
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


                                    viewModel.verifyChangePassword(
                                        VerifyChangePasswordRequest(
                                            userName = userDetails.userName,
                                            verfication = otpValue.value.toInt(),
                                        )
                                    )


                                } else {
                                    errorMessage = "OTP must be ${otpCount.value} digit.."
                                    showError = true
//                                    Message.showMessage(context, "OTP must be 6 digit..")
                                }

                            } else {
                                errorMessage = "Please add your OTP.."
                                showError = true

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

    changePswdVerify?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

                val errorResponse: ErrorResponse =
                    Converter.fromJson(it.errorMessage, ErrorResponse::class.java)
                if (errorResponse.code.equals("ERROR.TOTP_2FA_VERIFICATION_NOT_MATCH", true)) {
                    errorMessage = "Incorrect Google Authenticator Code"
                    showError = true

                } else if (errorResponse.code.equals("ERROR.TIME_OUT_VERIFICATION", true)) {
                    errorMessage = "Verification timeout"
                    showError = true
                } else if (errorResponse.code.equals("ERROR.FREE_TEXT", true)) {
                    errorMessage = "Wrong current password"
                    showError = true
                } else {

                }
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as VerifyChangePasswordResponse
                data.apply {
                    if (errMessage.equals("SUCCESS", true)) {
                        passChanged.value = true
                    }
                }

            }
        }
    }

    PassChangedBottomSheet(passChanged) {
        (context as ComponentActivity).finish()
    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }

    if (showError) {
        RoundedCornerToast(errorMessage, Toast.LENGTH_SHORT, context)

        LaunchedEffect(Unit) {
            delay(3000)
            showError = false

        }
    }

}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun OtpScreenPreview() {
    val navController = rememberNavController()
    OtpVerifyScreen(navController)
}
