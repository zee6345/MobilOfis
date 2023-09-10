package com.app.transfer.signatureauth.auth

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorResponse
import com.app.network.models.requestModels.FileDescriptor
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.requestModels.LoginVerificationRequest
import com.app.network.models.requestModels.SignApproveRequest
import com.app.network.models.responseModels.LoginResponse
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.transferModels.TransferListResponseItem
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel
import com.app.network.viewmodel.LoginViewModel
import com.app.transfer.R
import com.app.transfer.signatureauth.navigation.signatureSuccess
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.bottomSheet.ForgetPasswordModalBottomSheet
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.SharedModel
import com.app.uikit.views.BackHandler
import com.app.uikit.views.CountdownTimer
import com.app.uikit.views.OtpView
import com.app.uikit.views.TimerTextsView
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var googleAuthSelectedIndex = mutableStateOf(0)

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun SignAuthGoogle(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    homeModel: HomeViewModel = hiltViewModel()
) {

    val index0 = remember { mutableStateOf(true) }
    val index1 = remember { mutableStateOf(false) }
    val index2 = remember { mutableStateOf(false) }

    val usernameState = remember { mutableStateOf("") }
    val paswdState = remember { mutableStateOf("") }
    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    var isPswdVisible by remember { mutableStateOf(false) }
    val otpCount = remember { mutableStateOf(6) }
    val coroutine = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val otpValue = remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    val isSigned = remember { mutableStateOf(false) }

    var userErrorCheck by remember { mutableStateOf(false) }
    var pswdErrorCheck by remember { mutableStateOf(false) }


    var usernameForOtp by remember { mutableStateOf("") }
    val context = LocalContext.current
    val passwordVisualTransformation =
        if (isPswdVisible) VisualTransformation.None else PasswordVisualTransformation()

    val loginData by viewModel.data.collectAsState()
    val otpData by viewModel.otp.collectAsState()
    val signOrApprove by homeModel.getSignOrApprove.collectAsState()
    val transactionStatus by homeModel.getTransactionStatus.collectAsState()

    var showError by remember { mutableStateOf(false) }
    var errorsMessage by remember { mutableStateOf("") }


    val data = SharedModel.init().signatureData.value
    isSigned.value = data!!.isSignRequired

    val ibanList = remember { mutableListOf<FileDescriptor>() }
    data.transferList?.forEachIndexed { index, transferListResponseItem ->
        ibanList.add(FileDescriptor(transferListResponseItem.ibankRef))
    }

    //back press
    BackHandler(true) {
        (context as ComponentActivity).finish()

    }


    when (googleAuthSelectedIndex.value) {
        0 -> {
            index0.value = true
            index1.value = false
            index2.value = false
        }

        1 -> {
            index0.value = true
            index1.value = true
            index2.value = false
        }

        2 -> {
            index0.value = true
            index1.value = true
            index2.value = true
        }
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
                        .padding(horizontal = 20.sdp, vertical = 10.sdp)
                        .background(color = Color(0xFF203657))
                ) {

                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = "Access with Google Auth",
                        style = TextStyle(color = Color.White, fontSize = 26.sp)
                    )
                }

            }

        }

        Column(
            modifier = Modifier.weight(0.7f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.sdp)
                ) {

                    Box(
                        Modifier
                            .background(
                                if (index0.value) Color(0xFF203657) else Color(0xFFCCD6DE),
                                shape = RoundedCornerShape(12.sdp)
                            )
                            .weight(1f)
                            .padding(vertical = 2.sdp, horizontal = 1.sdp)
                    ) {

                    }

                    Spacer(modifier = Modifier.size(width = 2.sdp, height = 1.sdp))

                    Box(
                        Modifier
                            .background(
                                if (index1.value) Color(0xFF203657) else Color(0xFFCCD6DE),
                                shape = RoundedCornerShape(12.sdp)
                            )
                            .weight(1f)
                            .padding(vertical = 2.sdp, horizontal = 1.sdp)
                    ) {

                    }

                    Spacer(modifier = Modifier.size(width = 2.sdp, height = 1.sdp))

                    Box(
                        Modifier
                            .background(
                                if (index2.value) Color(0xFF203657) else Color(0xFFCCD6DE),
                                shape = RoundedCornerShape(12.sdp)
                            )
                            .weight(1f)
                            .padding(vertical = 2.sdp, horizontal = 1.sdp)
                    ) {

                    }

                }


                when (googleAuthSelectedIndex.value) {
                    0 -> {

                        //todo:: LOGIN

                        Column {

                            OutlinedTextField(
                                value = usernameState.value,
                                modifier = Modifier.fillMaxWidth(),
                                onValueChange = { usernameState.value = it },
                                label = {
                                    Text(
                                        text = "Username",
                                        fontSize = 14.sp
                                    )
                                }, trailingIcon = {},
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    backgroundColor = Color.White,
                                    focusedBorderColor = colorResource(R.color.background_card_blue),
                                    unfocusedBorderColor = colorResource(R.color.border_grey),
                                    unfocusedLabelColor = colorResource(R.color.grey_text),
                                    focusedLabelColor = colorResource(R.color.background_card_blue),
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(10.sdp)
                            )

                            OutlinedTextField(
                                value = paswdState.value,
                                onValueChange = { paswdState.value = it },
                                visualTransformation = passwordVisualTransformation,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                label = {
                                    Text(
                                        text = "Password",
                                        fontSize = 14.sp
                                    )
                                }, trailingIcon = {

                                    Icon(
                                        painter = painterResource(id = if (isPswdVisible) R.drawable.ic_pswd_visible else R.drawable.ic_pswd_visible),
                                        contentDescription = if (isPswdVisible) "Hide Password" else "Show Password",
                                        modifier = Modifier
                                            .pointerInteropFilter { event ->
                                                when (event.action) {
                                                    MotionEvent.ACTION_DOWN -> isPswdVisible = true
                                                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isPswdVisible =
                                                        false
                                                }
                                                true
                                            }
                                    )

                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    backgroundColor = Color.White,
                                    focusedBorderColor = colorResource(R.color.background_card_blue),
                                    unfocusedBorderColor = colorResource(R.color.border_grey),
                                    unfocusedLabelColor = colorResource(R.color.grey_text),
                                    focusedLabelColor = colorResource(R.color.background_card_blue)
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(10.sdp)
                            )


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 17.dp),
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
                                    CountdownTimer(usernameState.value)
                                }

                                ClickableText(modifier = Modifier.padding(5.dp),
                                    text = AnnotatedString(text = "Forgot password?"),
                                    onClick = {
                                        showForgetPassBottomSheetSheet.value =
                                            !showForgetPassBottomSheetSheet.value
                                    })
                            }


                            Button(
                                onClick = {

                                    if (usernameState.value.isNotEmpty()) {
                                        userErrorCheck = false
                                        if (paswdState.value.isNotEmpty()) {
                                            pswdErrorCheck = false

                                            //handle success
                                            viewModel.loginWithUserName(
                                                LoginRequest(
                                                    userName = usernameState.value,
                                                    password = paswdState.value,
                                                    authType = "TOTP",
                                                    channel = "MOBILE"
                                                )
                                            )

                                        } else {
                                            pswdErrorCheck = !pswdErrorCheck
                                        }
                                    } else {
                                        userErrorCheck = !userErrorCheck
                                    }

                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),// Optional: To override other button colors
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF203657), RoundedCornerShape(8.dp))
                            ) {
                                Text(
                                    "Log in",
                                    modifier = Modifier.padding(vertical = 12.dp),
                                    color = Color.White
                                )
                            }

                        }

                    }

                    1 -> {
                        //todo:: OTP

                        Column {

                            Column {
                                OtpView(otpCount.value) {
                                    otpValue.value = it
                                }

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
                                    androidx.compose.material.Button(
                                        onClick = {
                                            (context as ComponentActivity).finish()
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(R.color.border_grey), // Change the background color here
                                            contentColor = Color(0xFF203657) // Change the text color here if needed
                                        ),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .weight(1f)

                                    ) {
                                        androidx.compose.material.Text(
                                            "Close",
                                            modifier = Modifier.padding(vertical = 6.dp),
                                            style = TextStyle(
                                                fontSize = 17.sp, shadow = null
                                            )
                                        )
                                    }

                                    androidx.compose.material.Button(
                                        onClick = {
                                            if (otpValue.value.isNotEmpty()) {
                                                if (otpValue.value.length == otpCount.value) {

                                                    viewModel.loginAuthVerification(
                                                        LoginVerificationRequest(
                                                            userName = usernameForOtp,
                                                            verfication = otpValue.value.toInt(),
                                                            channel = "INT",
                                                        )
                                                    )

                                                    otp = otpValue.value

                                                } else {
                                                    showError = true
                                                    errorsMessage = "OTP must be 6 digit.."
                                                }

                                            } else {
                                                showError = true
                                                errorsMessage = "Please add your OTP.."
                                            }


                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0xFF203657),
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .weight(1f)
                                    ) {
                                        androidx.compose.material.Text(
                                            "Continue",
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

                    2 -> {

                        Column {

                            Column {
                                OtpView(otpCount.value, otp) {
                                    otpValue.value = it
                                }

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
                                    androidx.compose.material.Button(
                                        onClick = {
                                            (context as ComponentActivity).finish()
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                            backgroundColor = colorResource(R.color.border_grey), // Change the background color here
                                            contentColor = Color(0xFF203657) // Change the text color here if needed
                                        ),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .weight(1f)

                                    ) {
                                        androidx.compose.material.Text(
                                            "Close",
                                            modifier = Modifier.padding(vertical = 6.dp),
                                            style = TextStyle(
                                                fontSize = 17.sp, shadow = null
                                            )
                                        )
                                    }

                                    androidx.compose.material.Button(
                                        onClick = {
                                            if (otpValue.value.isNotEmpty()) {
                                                if (otpValue.value.length == otpCount.value) {

                                                    homeModel.signOrApprove(
                                                        SignApproveRequest(
                                                            ibanList,
                                                            if (isSigned.value) "SIGN" else "APPROVE"
                                                        )
                                                    )

                                                } else {
                                                    showError = true
                                                    errorsMessage = "OTP must be 6 digit.."
                                                }
                                            } else {
                                                showError = true
                                                errorsMessage = "Please add your OTP.."
                                            }
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0xFF203657),
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .weight(1f)
                                    ) {
                                        androidx.compose.material.Text(
                                            "Confirm",
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
                }


            }

        }

    }


    //show empty field toast
    if (userErrorCheck or pswdErrorCheck) {
        RoundedCornerToast("Please fill in all fields", Toast.LENGTH_SHORT, context)

        LaunchedEffect(Unit) {
            delay(1000)
            userErrorCheck = false
            pswdErrorCheck = false
        }

    }

    ForgetPasswordModalBottomSheet(showForgetPassBottomSheetSheet)

    /**
     * handle response data
     */

    loginData?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

                val errorMessage = Converter.fromJson(it.errorMessage, ErrorResponse::class.java)
                errorMessage?.let { error ->
                    if (error.code.equals("ERROR.FREE_TEXT", true)) {
                        showError = true
                        errorsMessage = "Wrong username or password!"

                    }
                }
            }

            is DataState.Success -> {
                isLoading.value = false


                val loginResponse = it.data as LoginResponse
                loginResponse?.apply {

                    usernameForOtp = usernameState.value

                    LaunchedEffect(it) {
                        //route to OTP
                        index1.value = true
                        googleAuthSelectedIndex.value = 1

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

                //on error remove keys
//                viewModel.session.delete(Keys.KEY_TOKEN)

                val errorResponse = Converter.fromJson(it.errorMessage, ErrorResponse::class.java)
                if (errorResponse.code.equals("ERROR.TOTP_2FA_VERIFICATION_NOT_MATCH", true)) {
                    errorsMessage = "Incorrect Google Authenticator Code"
                    showError = true
                } else if (errorResponse.code.equals("ERROR.INVALID_TOKEN", true)) {
                    errorsMessage = "Invalid token!"
                    showError = true

                    LaunchedEffect(it) {

                        googleAuthSelectedIndex.value = 0

                    }


                } else {

                }

            }

            is DataState.Success -> {

                val loginVerifyResponse = it.data as LoginVerifyResponse
                loginVerifyResponse?.apply {
                    isLoading.value = false

                    //cache login verify response
                    val strJson = Converter.toJson(loginVerifyResponse)
                    viewModel.session.put(Keys.KEY_USER_DETAILS, strJson)

                    //sign API
                    LaunchedEffect(it) {
                        homeModel.signOrApprove(
                            SignApproveRequest(
                                ibanList,
                                if (isSigned.value) "SIGN" else "APPROVE"

                            )
                        )
                    }

                }
            }
        }
    }

    signOrApprove?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true

            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                LaunchedEffect(it) {
                    index2.value = true
                    googleAuthSelectedIndex.value = 2

                    homeModel.transactionStatus(otpValue.value.toInt())
                }

            }
        }
    }

    transactionStatus?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                LaunchedEffect(it) {
                    navController.navigate(signatureSuccess)
                }

            }
        }
    }


    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }


    if (showError) {
        RoundedCornerToast(errorsMessage, Toast.LENGTH_SHORT, context)

        LaunchedEffect(Unit) {
            delay(3000)
            showError = false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignAuthGoogle() {
    SignAuthGoogle(navController = rememberNavController())
}