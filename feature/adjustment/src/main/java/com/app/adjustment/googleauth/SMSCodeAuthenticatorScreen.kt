package com.app.adjustment.googleauth

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorResponse
import com.app.network.models.requestModels.VerifyChangePasswordRequest
import com.app.network.models.requestModels.VerifyRequest
import com.app.network.models.responseModels.GetVerify2FA
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.viewmodel.AdjustmentViewModel
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.views.OtpView
import com.app.uikit.views.TimerTextView
import com.app.uikit.views.TimerTextsView20S
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val googleAuthToOtpVerify = "googleAuthToOtpVerify"

@Composable
fun SMSCodeAuthenticatorScreen(
    navController: NavHostController,
    viewModel: AdjustmentViewModel = hiltViewModel()
) {

    val isTimerStarted = remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val otpValue = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val verify2FA = viewModel.verify2FA.collectAsState()

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), enabled = true)
            .background(color = colorResource(R.color.border_light_grey))
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.1f),
            color = Color(0xFF203657),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(height = 25.dp, width = 32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.popBackStack()
                        },
                    contentDescription = ""
                )
                Text(
                    text = "Google Authenticator",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }
        Column(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                backgroundColor = Color.White
            ) {

                Column {


                    Text(
                        text = "Please confirm the start of the activation process of Google Authenticator through the SMS code sent to your mobile number",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color(0xff223142),
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp)

                    )
                    OtpView(5) {
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

                            TimerTextsView20S(onTimerStart = {
                                isTimerStarted.value = true
                            }, onTimerStop = {
                                isTimerStarted.value = false
                            })

                        }

                        ClickableText(modifier = Modifier
                            .padding(5.dp)
                            .align(CenterVertically),
                            text = AnnotatedString(text = "Re-send SMS code"),
                            style = TextStyle(
                                color = if (!isTimerStarted.value) Color(0xFF203657) else Color(
                                    0xFF859DB5
                                ),
                                fontSize = 14.sp
                            ),
                            onClick = {

                                if (!isTimerStarted.value) {
                                    coroutine.launch {
                                        errorMessage = "OTP send again!"
                                        showError = true
                                    }
                                }

                            })
                    }


                    Button(
                        onClick = {

                            if (otpValue.value.isNotEmpty()) {
                                if (otpValue.value.length == 5) {

                                    viewModel.verify2FA(
                                        VerifyRequest(
                                            userName = userDetails.userName,
                                            verfication = otpValue.value
                                        )
                                    )


                                } else {
                                    errorMessage = "OTP must be 5 digit.."
                                    showError = true
                                }

                            } else {
                                errorMessage = "Please add your OTP.."
                                showError = true

                            }


                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF203657), // Change the background color here
                            contentColor = Color.White // Change the text color here if needed
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 22.dp)
                            .padding(bottom = 12.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            "Confirm it",
                            modifier = Modifier.padding(vertical = 10.dp),
                            style = TextStyle(
                                fontSize = 16.sp, shadow = null
                            )
                        )
                    }

                }


            }


        }

    }

    verify2FA.value?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

                val errorResponse: ErrorResponse =
                    Converter.fromJson(it.errorMessage, ErrorResponse::class.java)
                if (errorResponse.code.equals("ERROR.SMS_2FA_VERIFICATION_NOT_MATCH", true)) {

                    errorMessage = "Invalid SMS Code"
                    showError = true

                } else {

                }
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as GetVerify2FA

                data?.apply {
                    secretMessage.value = messages[0].MESSAGE
                }

                navController.navigate(otpToConfirmGoogleAuthOtp)

            }
        }
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

@Preview(device = Devices.PIXEL_4)
@Composable
fun SMSCodeAuthenticatorScreenPreview() {
    SMSCodeAuthenticatorScreen(rememberNavController())
}