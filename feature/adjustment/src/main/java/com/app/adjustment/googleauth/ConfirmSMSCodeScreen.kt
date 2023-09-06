package com.app.adjustment.googleauth

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.network.helper.Converter
import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorResponse
import com.app.network.models.requestModels.VerifyRequest
import com.app.network.models.requestModels.VerifySecretRequest
import com.app.network.viewmodel.AdjustmentViewModel
import com.app.uikit.bottomSheet.GAuthActiveBottomSheet
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.views.OtpView
import kotlinx.coroutines.delay


const val otpToConfirmGoogleAuthOtp = "otpToConfirmGoogleAuthOtp"

val secretMessage = mutableStateOf("")

@Composable
fun ConfirmSMSCodeScreen(
    navController: NavHostController,
    viewModel: AdjustmentViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val otpValue = remember { mutableStateOf("") }
    val verifySecret = viewModel.verify2FASecret.collectAsState()
    val isLoading = remember { mutableStateOf(false) }
    var successSheet = remember{ mutableStateOf(false) }

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
                    painter = painterResource(id = R.drawable.back_icon),
                    modifier = Modifier
                        .size(height = 26.dp, width = 32.dp)
                        .align(CenterVertically)
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
            Column() {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    backgroundColor = Color.White
                ) {
                    Column() {
                        Text(
                            text = "Copy the code shown here and enter it in the Google Authenticator app",
                            style = TextStyle(fontSize = 16.sp),
                            color = Color(0xff223142),
                            modifier = Modifier
                                .padding(horizontal = 22.dp)
                                .padding(top = 22.dp)

                        )
                        Box(
                            modifier = Modifier
                                .padding(22.dp)
                                .fillMaxWidth()
                                .background(
                                    Color.White
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFE7EEFC),
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp, horizontal = 12.dp)
                                    .padding(end = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val scrollState = rememberScrollState()
                                Text(
                                    text = secretMessage.value,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 12.dp)
                                        .horizontalScroll(scrollState),
                                    style = TextStyle(
                                        Color(0xFF223142), fontSize = 16.sp

                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_copy),
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .clickable {

                                            try {
                                                val clipboardManager: ClipboardManager? =
                                                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
                                                val clipData = ClipData.newPlainText(
                                                    "Copied!",
                                                    secretMessage.value
                                                )
                                                clipboardManager!!.setPrimaryClip(clipData)

                                                errorMessage = "Copied!"
                                                showError = true


                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                            }

                                        },
                                    contentDescription = ""
                                )
                            }


                        }

                    }
                }
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    backgroundColor = Color.White
                ) {

                    Column {
                        Text(
                            text = "After the new line containing the 6-digit code is added in the Google Authenticator app, please enter it and click \"Confirm\".\n\nEnter the Google code",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF223142),
                            ),
                            modifier = Modifier
                                .padding(horizontal = 22.dp)
                                .padding(top = 22.dp)

                        )

                        OtpView(viewCount = 6) {
                            otpValue.value = it
                        }

                        Button(
                            onClick = {

                                if (otpValue.value.isNotEmpty()) {
                                    if (otpValue.value.length == 6) {


                                        viewModel.verify2FASecret(
                                            VerifySecretRequest(
                                                secretMessage.value,
                                                otpValue.value
                                            )
                                        )


                                    } else {
                                        errorMessage = "OTP must be 6 digit.."
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

    }

    verifySecret.value?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

                val errorResponse: ErrorResponse =
                    Converter.fromJson(it.errorMessage, ErrorResponse::class.java)
                if (errorResponse.code.equals("ERROR.TOTP_NOT_VALID", true)) {

                    errorMessage = "Wrong Google Authenticator code"
                    showError = true

                } else {

                }
            }

            is DataState.Success -> {
                isLoading.value = false
//                val data = it.data

                successSheet.value = true


            }
        }
    }


    GAuthActiveBottomSheet(successSheet){
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

@Preview(device = Devices.PIXEL_4)
@Composable
fun ConfirmSMSCodeScreenPreview() {
    ConfirmSMSCodeScreen(rememberNavController())
}