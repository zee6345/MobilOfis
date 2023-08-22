package com.app.auth.login


import android.content.Context
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.auth.R
import com.app.auth.login.easysignature.navigation.loginToEasySignature
import com.app.auth.login.navigation.otpNavigationRoute
import com.app.auth.login.otp.otpScreen
import com.app.auth.pin.navigation.welcomePinScreen
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.ErrorResponse
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.borders.dashedBorder
import com.app.uikit.bottomSheet.ForgetPasswordModalBottomSheet
import com.app.uikit.dialogs.CallTopAlertDialog
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.SharedModel
import com.app.uikit.views.CountdownTimer
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    val (selected, setSelected) = remember { mutableStateOf(0) }
    val usernameState = remember { mutableStateOf("") }
    val paswdState = remember { mutableStateOf("") }
    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    var userErrorCheck by remember { mutableStateOf(false) }
    var pswdErrorCheck by remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val pin = viewModel.session[Keys.KEY_USER_PIN]
    val coroutine = rememberCoroutineScope()

    var isPswdVisible by remember { mutableStateOf(false) }
    val passwordVisualTransformation = if (isPswdVisible) VisualTransformation.None else PasswordVisualTransformation()

    val loginData by viewModel.data.collectAsState()
    val asanLogin by viewModel.asanLogin.collectAsState()

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = 50.sdp,
        sheetShape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
        sheetElevation = 20.sdp,
        sheetContent = {

            Column(Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.padding(top = 10.sdp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .width(50.sdp)
                            .height(4.sdp)
                            .background(
                                color = Color(0xFFEDEEFB),
                                shape = RoundedCornerShape(size = 10.sdp)
                            )
                            .align(Alignment.CenterVertically)
                    )
                }


                Text(
                    text = stringResource(R.string.information_and_content),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.sdp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )

                BottomSheetItems(
                    R.drawable.ic_location,
                    stringResource(R.string.branches_and_atms),
                    true
                )
                BottomSheetItems(R.drawable.ic_tariff, stringResource(R.string.tariffs), true)
                BottomSheetItems(
                    R.drawable.ic_whatsapp_support,
                    stringResource(R.string.whatsapp_support),
                    true
                )
                BottomSheetItems(
                    R.drawable.ic_call_support,
                    stringResource(R.string.call_center),
                    true
                )

                Row(
                    modifier = Modifier
                        .padding(top = 10.sdp, start = 18.sdp, bottom = 10.sdp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.ic_language),
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(R.string.application_language),
                        modifier = Modifier.padding(vertical = 12.dp)
                    )


                    LanguageOptions()

                }

            }

        }) {

        //main content
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
                            .padding(horizontal = 20.sdp, vertical = 15.sdp)
                            .background(color = Color(0xFF203657))
                    ) {

                        Text(
                            modifier = Modifier.align(Alignment.BottomStart),
                            text = stringResource(R.string.mobile_office_welcome),
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

                    LoginTabsRow(selected, setSelected)

                    OutlinedTextField(
                        value = usernameState.value,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { usernameState.value = it },
                        label = {
                            Text(
                                text = if (selected == 2) stringResource(R.string.mobile_number) else stringResource(
                                    R.string.username
                                ),
                                fontSize = 14.sp
                            )
                        }, trailingIcon = {},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = colorResource(R.color.background_card_blue),
                            unfocusedBorderColor = colorResource(com.app.home.R.color.border_grey),
                            unfocusedLabelColor = colorResource(com.app.adjustment.R.color.grey_text),
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
                                text = if (selected == 2) stringResource(R.string.user_id) else stringResource(
                                    R.string.password
                                ), fontSize = 14.sp
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


                    if (selected != 2) {
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
                                text = AnnotatedString(text = stringResource(R.string.forgot_your_password)),
                                onClick = {
                                    showForgetPassBottomSheetSheet.value =
                                        !showForgetPassBottomSheetSheet.value
                                })
                        }

                    } else {
                        Spacer(modifier = Modifier.padding(top = 10.sdp))
                    }

                    Button(
                        onClick = {
                            when (selected) {
                                0 -> {
                                    if (usernameState.value.isNotEmpty()) {
                                        userErrorCheck = false
                                        if (paswdState.value.isNotEmpty()) {
                                            pswdErrorCheck = false

                                            //handle success
                                            viewModel.loginWithUserName(
                                                LoginRequest(
                                                    userName = usernameState.value,
                                                    password = paswdState.value,
                                                    authType = "OTP",
                                                    channel = "MOBILE"
                                                )
                                            )

                                        } else {
                                            pswdErrorCheck = !pswdErrorCheck
                                        }
                                    } else {
                                        userErrorCheck = !userErrorCheck
                                    }
                                }

                                1 -> {

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

                                }

                                2 -> {
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

                                }
                            }


                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),// Optional: To override other button colors
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF203657), RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            stringResource(R.string.login),
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = Color.White
                        )
                    }

                }


                if (!pin.isNullOrEmpty()) {
                    Column {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xffD1D4D9))
                                .height(1.sdp)
                        ) {

                        }

                        Spacer(modifier = Modifier.size(height = 20.sdp, width = 1.sdp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 60.sdp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Row(
                                Modifier.clickable {
                                    navController.navigate(welcomePinScreen) {
//                                        popUpTo(loginNavigationRoute){
//                                            inclusive = true
//                                        }
                                    }
                                }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_login_pin),
                                    contentDescription = ""
                                )
                                Text(text = "Login with PIN code")
                            }
                        }
                    }
                }
            }


        }

    }

    ForgetPasswordModalBottomSheet(showForgetPassBottomSheetSheet)


    LaunchedEffect(Unit) {
        delay(1000)
        showDialog.value = true
    }

    if (showDialog.value) {
        CallTopAlertDialog(
            backgroundColor = colorResource(R.color.background_card_blue),
            cornerRadius = 12.dp,
            title = stringResource(R.string.attention),
            message = stringResource(R.string.demo_text),
            onClose = { showDialog.value = false }
        )
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


    /**
     * handle login response data
     */
    loginData?.let { it ->
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
                            showMessage(context, "Wrong username or password!")
                        }


                    }
                }
            }

            is DataState.Success -> {
                isLoading.value = false

                val loginResponse = it.data as LoginResponse
                loginResponse?.apply {

                    LaunchedEffect(Unit) {
                        //route to OTP
                        otpScreen.userName = usernameState.value
                        SharedModel.init().loginType.value = selected

                        navController.navigate(otpNavigationRoute)

                    }

                }

            }
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
                            showMessage(context, "Wrong username or password!")
                        }
                    }
                }
            }

            is DataState.Success -> {

                val loginResponse = it.data as LoginAsanResponse
                loginResponse?.apply {

                    LaunchedEffect(Unit) {

                        //route to OTP
                        otpScreen.userName = usernameState.value
                        SharedModel.init().loginType.value = selected

                        //easy verification code
                        SharedModel.init().easyVerificationCode.value =
                            "${loginResponse.gniAuthResponseType.verfication}"


                        navController.navigate(loginToEasySignature)

                    }

                }

            }
        }
    }


}


fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

//@Composable
//fun RoundedCornerToast(
//    text: String,
//    duration: Int = Toast.LENGTH_SHORT,
//    context: Context
//) {
//    val coroutineScope = rememberCoroutineScope()
//
//
////    LaunchedEffect(Unit) {
////        coroutineScope.launch {
////            Toast.makeText(context, text, duration).show()
////        }
////    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(vertical = 50.sdp, horizontal = 20.sdp)
//            .background(Color.Transparent, RoundedCornerShape(16.dp))
//            .wrapContentSize(Alignment.TopCenter)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFFFFDCDE), RoundedCornerShape(16.dp))
//                .border(2.dp, Color(0xFFF3646C), RoundedCornerShape(16.dp)),
//            contentAlignment = Alignment.CenterStart
//        ) {
//            Text(
//                text = text,
//                style = TextStyle(fontSize = 16.sp, color = Color(0xFFF3646C)),
//                modifier = Modifier.padding(16.dp)
//            )
//        }
//    }
//}


@Composable
fun LoginTabsRow(selected: Int, setSelected: (Int) -> Unit) {

    TabRow(
        selectedTabIndex = selected,
        indicator = {},
        divider = {},
        modifier = Modifier
            .padding(bottom = 16.dp)
            .background(Color(0xFFF3F7FA))
    ) {


        Box(
            Modifier.background(Color(0xFFF3F7FA))
        ) {
            Tab(
                selectedContentColor = Color.Cyan,
                selected = selected == 0,
                onClick = { setSelected(0) },
                modifier = Modifier
                    .background(
                        color = if (selected == 0) Color(0xFF203657) else Color(0xFFF3F7FA),
                        shape = RoundedCornerShape(10.dp)
                    )

            ) {

                Text(
                    modifier = Modifier.padding(12.dp),
                    text = stringResource(id = R.string.login),
                    style = TextStyle(fontSize = 12.sp),
                    color = if (selected == 0) Color.White else Color.Black
                )

            }
        }

        Box(
            Modifier.background(Color(0xFFF3F7FA))
        ) {

            Tab(
                selected = selected == 1,
                onClick = { setSelected(1) },
                modifier = Modifier
                    .background(
                        color = if (selected == 1) Color(0xFF203657) else Color(0xFFF3F7FA),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = stringResource(R.string.google),
                    style = TextStyle(fontSize = 12.sp),
                    color = if (selected == 1) Color.White else Color.Black
                )
            }
        }


        Box(
            Modifier.background(Color(0xFFF3F7FA))
        ) {
            Tab(
                selected = selected == 2,
                onClick = { setSelected(2) },
                modifier = Modifier
                    .background(
                        color = if (selected == 2) Color(0xFF203657) else Color(0xFFF3F7FA),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = stringResource(R.string.easy_signature),
                    style = TextStyle(fontSize = 12.sp),
                    color = if (selected == 2) Color.White else Color.Black
                )
            }
        }
    }
}


@Composable
private fun BottomSheetItems(iconRes: Int, title: String, showBorder: Boolean) {

    val borderModify = Modifier
        .dashedBorder(3.sdp, colorResource(R.color.border_grey))
        .fillMaxWidth()
    val nonBorderModify = Modifier.fillMaxWidth()

    Row(
        modifier = if (showBorder) borderModify else nonBorderModify,
    ) {

        Row(
            modifier = Modifier.padding(top = 5.sdp, start = 16.sdp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(id = iconRes),
                modifier = Modifier
                    .height(28.dp)
                    .width(32.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = ""
            )
            Text(
                text = title, modifier = Modifier.padding(vertical = 12.dp), style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )
            )
        }

    }
}

@Composable
private fun LanguageOptions() {

    val selectedBoxIndex = remember { mutableStateOf(-1) }

    Row() {
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 0) colorResource(R.color.background_card_blue) else colorResource(
                    R.color.border_grey
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 0 }) {
            androidx.compose.material.Text(
                text = stringResource(R.string.az),
                modifier = Modifier.padding(6.dp),
                style = TextStyle(
                    if (selectedBoxIndex.value == 0) Color.White else colorResource(R.color.background_card_blue),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 1) colorResource(R.color.background_card_blue) else colorResource(
                    R.color.border_grey
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 1 }) {
            androidx.compose.material.Text(
                text = stringResource(R.string.en),
                modifier = Modifier.padding(6.dp),
                style = TextStyle(
                    if (selectedBoxIndex.value == 1) Color.White else colorResource(R.color.background_card_blue),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 2) colorResource(R.color.background_card_blue) else colorResource(
                    R.color.border_grey
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 2 }) {
            androidx.compose.material.Text(
                text = stringResource(R.string.ru),
                modifier = Modifier.padding(6.dp),
                style = TextStyle(
                    if (selectedBoxIndex.value == 2) Color.White else colorResource(R.color.background_card_blue),
                    fontSize = 12.sp
                )
            )
        }
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
//    LoginScreen(rememberNavController())

    RoundedCornerToast("Please fill in all fields", Toast.LENGTH_SHORT, LocalContext.current)
}

