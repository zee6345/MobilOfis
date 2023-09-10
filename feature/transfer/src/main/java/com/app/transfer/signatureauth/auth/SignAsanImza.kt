package com.app.transfer.signatureauth.auth

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.network.helper.Converter
import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorResponse
import com.app.network.models.requestModels.FileDescriptor
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.SignApproveRequest
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.transferModels.TransferListResponseItem
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel
import com.app.network.viewmodel.LoginViewModel
import com.app.transfer.R
import com.app.transfer.signatureauth.navigation.signatureSuccess
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.borders.dashedBorder
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.SharedModel
import com.app.uikit.views.BackHandler
import com.app.uikit.views.CountdownTimer
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var asanImzaSelectedIndex = mutableStateOf(0)

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun SignAsanImza(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    homeModel: HomeViewModel = hiltViewModel()
) {

//    var selected by remember { mutableStateOf(0) }

//    val signInfo = SharedModel.init().signInfo
    val index0 = remember { mutableStateOf(true) }
    val index1 = remember { mutableStateOf(false) }
    val index2 = remember { mutableStateOf(false) }
    val usernameState = remember { mutableStateOf("") }
    val paswdState = remember { mutableStateOf("") }
    val easyCode = remember { mutableStateOf("") }
    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    var isPswdVisible by remember { mutableStateOf(false) }
    var userErrorCheck by remember { mutableStateOf(false) }
    var pswdErrorCheck by remember { mutableStateOf(false) }
    val transfer = remember { mutableStateOf<TransferListResponseItem?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val passwordVisualTransformation =
        if (isPswdVisible) VisualTransformation.None else PasswordVisualTransformation()

    val asanLogin by viewModel.asanLogin.collectAsState()
    val signOrApprove by homeModel.getSignOrApprove.collectAsState()
    val transactionStatus by homeModel.getTransactionStatus.collectAsState()

    val data = SharedModel.init().signatureData.value
    data?.let {
        it.transfer?.let { obj ->
            transfer.value = obj
        }
    }

    BackHandler(true) {

        (context as ComponentActivity).finish()

    }

    when (asanImzaSelectedIndex.value) {
        0 -> {
            index0.value = true
        }

        1 -> {
            index0.value = true
            index1.value = true
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
                        text = "Access with Asan Imza",
                        style = TextStyle(color = Color.White, fontSize = 29.sp)
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


                if (asanImzaSelectedIndex.value == 0) {

                    //todo:: LOGIN

                    Column {

                        OutlinedTextField(
                            value = usernameState.value,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { usernameState.value = it },
                            label = {
                                Text(
                                    text = "Mobile Number",
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
                                    text = "User ID",
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
                                        viewModel.asanLogin(
                                            LoginAsanRequest(
                                                phoneNumber = usernameState.value,
                                                userId = paswdState.value,
                                                channel = "INT"
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

                } else if (asanImzaSelectedIndex.value == 1) {
                    //todo:: ASAN service

                    Column {

                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 22.dp),
                            backgroundColor = Color.White
                        ) {

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .dashedBorder(
                                            3.dp, colorResource(R.color.border_grey)
                                        )
                                        .height(80.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Image(
                                        painter = painterResource(id = R.drawable.print_design),
                                        modifier = Modifier
                                            .padding(start = 12.dp)
                                            .height(80.dp)
                                            .width(100.dp),
                                        contentDescription = ""
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.question_icon),
                                        modifier = Modifier
                                            .size(50.dp)
                                            .align(Alignment.CenterVertically)
                                            .padding(end = 12.dp),
                                        contentDescription = ""
                                    )
                                }
                                androidx.compose.material.Text(
                                    modifier = Modifier
                                        .dashedBorder(
                                            3.dp, colorResource(R.color.border_grey)
                                        )
                                        .padding(horizontal = 12.dp, vertical = 22.dp),
                                    text = "Please accept the query sent to your phone. Compare the checking code of the survey to the same code as the following code.",
                                    style = TextStyle(fontSize = 16.sp)
                                )
                                androidx.compose.material.Text(
                                    modifier = Modifier
                                        .padding(top = 32.dp, bottom = 5.dp)
                                        .fillMaxWidth(),
                                    text = "Check code",
                                    style = TextStyle(
                                        color = colorResource(R.color.grey_text),
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                androidx.compose.material.Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = easyCode.value,
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                Spacer(modifier = Modifier.size(height = 20.sdp, width = 1.sdp))
                                CircularTimer()

                            }
                        }

                    }

                } else if (asanImzaSelectedIndex.value == 2) {
                    LaunchedEffect(Unit){
                        homeModel.transactionStatus(easyCode.value.toInt())
                    }

                    Column {

                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 22.dp),
                            backgroundColor = Color.White
                        ) {

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .dashedBorder(
                                            3.dp, colorResource(R.color.border_grey)
                                        )
                                        .height(80.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Image(
                                        painter = painterResource(id = R.drawable.print_design),
                                        modifier = Modifier
                                            .padding(start = 12.dp)
                                            .height(80.dp)
                                            .width(100.dp),
                                        contentDescription = ""
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.question_icon),
                                        modifier = Modifier
                                            .size(50.dp)
                                            .align(Alignment.CenterVertically)
                                            .padding(end = 12.dp),
                                        contentDescription = ""
                                    )
                                }
                                androidx.compose.material.Text(
                                    modifier = Modifier
                                        .dashedBorder(
                                            3.dp, colorResource(R.color.border_grey)
                                        )
                                        .padding(horizontal = 12.dp, vertical = 22.dp),
                                    text = "Please accept the query sent to your phone. Compare the checking code of the survey to the same code as the following code.",
                                    style = TextStyle(fontSize = 16.sp)
                                )
                                androidx.compose.material.Text(
                                    modifier = Modifier
                                        .padding(top = 32.dp, bottom = 5.dp)
                                        .fillMaxWidth(),
                                    text = "Check code",
                                    style = TextStyle(
                                        color = colorResource(R.color.grey_text),
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                androidx.compose.material.Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = easyCode.value,
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                Spacer(modifier = Modifier.size(height = 20.sdp, width = 1.sdp))
                                CircularTimer()

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


    asanLogin?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
                if (isLoading.value) {
                    ShowProgressDialog(isLoading)
                } else {

                }
            }

            is DataState.Error -> {
                isLoading.value = false

                val errorMessage = Converter.fromJson(it.errorMessage, ErrorResponse::class.java)
                errorMessage?.let { error ->
                    if (error.code.equals("ERROR.FREE_TEXT", true)) {
                        LaunchedEffect(error.code) {
                            Message.showMessage(context, "Wrong username or password!")
                        }
                    }
                }
            }

            is DataState.Success -> {

                val loginResponse = it.data as LoginAsanResponse
                loginResponse?.apply {


                    LaunchedEffect(Unit) {
                        asanImzaSelectedIndex.value = 1
                        index1.value = true


                        //verification code
                        easyCode.value = loginResponse.gniAuthResponseType.verfication


                        //sign API
                        homeModel.signOrApprove(
                            SignApproveRequest(
                                listOf(FileDescriptor("${transfer.value!!.ibankRef}")),
                                "SIGN"
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
                if (isLoading.value) {
                    ShowProgressDialog(isLoading)
                } else {

                }
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false


                LaunchedEffect(Unit) {
                    index2.value = true
                    asanImzaSelectedIndex.value = 2

                }

            }
        }
    }

    transactionStatus?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
                if (isLoading.value) {
                    ShowProgressDialog(isLoading)
                } else {

                }
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                LaunchedEffect(Unit) {
                    navController.navigate(signatureSuccess)
                }
            }
        }
    }


}

@Composable
fun CircularTimer() {
    val totalTimeSeconds = 2 * 60 // 2 minutes in seconds
    var remainingTime by remember { mutableStateOf(totalTimeSeconds) }

    val progress = (totalTimeSeconds - remainingTime) / totalTimeSeconds.toFloat()

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val timerJob = coroutineScope.launch {
            while (remainingTime > 0) {
                delay(1000) // Delay for 1 second
                remainingTime -= 1
            }
        }

        onDispose {
            timerJob.cancel()
        }
    }

    Box(
        modifier = Modifier
            .background(
                color = Color(0xffECE9FF),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier
                .size(150.dp)
        )
        androidx.compose.material.Text(
            text = "${remainingTime / 60}:${String.format("%02d", remainingTime % 60)}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff7B61FF)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignAsanImza() {
    SignAsanImza(navController = rememberNavController())
}