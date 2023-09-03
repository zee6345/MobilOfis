package com.app.auth.login


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
import com.app.network.models.errorResponse.ErrorResponse
import com.app.network.models.requestModels.LoginAsanRequest
import com.app.network.models.requestModels.LoginRequest
import com.app.network.models.responseModels.GetStartMessage
import com.app.network.models.responseModels.LoginAsanResponse
import com.app.network.models.responseModels.LoginResponse
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.CurvedBottomBox
import com.app.uikit.borders.dashedBorder
import com.app.uikit.bottomSheet.ForgetPasswordModalBottomSheet
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.SharedModel
import com.app.uikit.views.CountdownTimer
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var usernameState: MutableState<String>
private lateinit var paswdState: MutableState<String>

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    val (selected, setSelected) = remember { mutableStateOf(0) }

    usernameState = remember { mutableStateOf("") }
    paswdState = remember { mutableStateOf("") }

    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    var userErrorCheck by remember { mutableStateOf(false) }
    var pswdErrorCheck by remember { mutableStateOf(false) }
    var isPswdVisible by remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val message = remember { mutableStateOf("") }

    val context = LocalContext.current
    val enableLoginWithPin = viewModel.session.getBoolean(Keys.KEY_ENABLE_PIN_LOGIN)
    val passwordVisualTransformation =
        if (isPswdVisible) VisualTransformation.None else PasswordVisualTransformation()

    val loginData by viewModel.data.collectAsState()
    val asanLogin by viewModel.asanLogin.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutine = rememberCoroutineScope()

    val startMessage by viewModel.getDashboardMessage.collectAsState()


    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getDashBoardMessage()
        }
    }


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 50.sdp,
        sheetShape = RoundedCornerShape(10.dp),
        sheetElevation = 20.sdp,
        sheetContent = {

            Column(
                Modifier.fillMaxWidth()

            ) {

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

                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.information_and_content),
                        textAlign = TextAlign.Center,
                        modifier = Modifier

                            .padding(top = 10.sdp)
                            .clickable {
                                coroutine.launch {
                                    if (scaffoldState.bottomSheetState.isExpanded) {
                                        scaffoldState.bottomSheetState.collapse()
                                    } else {
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            },
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )

                }

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
                            text = stringResource(R.string.mobile_office_welcome_),
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
                        onValueChange = {
                            val formatedInput = if (selected == 2) it.take(12) else it
                            usernameState.value = formatedInput
                        },
                        visualTransformation = if (selected == 2) PhoneNumberVisualTransformation else VisualTransformation.None,
                        keyboardOptions = if (selected == 2) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
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
                        shape = RoundedCornerShape(8.dp)
                    )

                    OutlinedTextField(
                        value = paswdState.value,
                        onValueChange = { paswdState.value = it },
                        visualTransformation = passwordVisualTransformation,
                        keyboardOptions = if (selected == 2) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        label = {
                            Text(
                                text = if (selected == 2) stringResource(R.string.user_id) else stringResource(
                                    R.string.password
                                ), fontSize = 14.sp
                            )
                        }, trailingIcon = {

                            Image(
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
                        shape = RoundedCornerShape(8.dp)
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

                                            coroutine.launch {

                                                //handle success
                                                viewModel.loginWithUserName(
                                                    LoginRequest(
                                                        userName = usernameState.value,
                                                        password = paswdState.value,
                                                        authType = "OTP",
                                                        channel = "MOBILE"
                                                    )
                                                )

                                            }

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

                                            coroutine.launch {
                                                //handle success
                                                viewModel.loginWithUserName(
                                                    LoginRequest(
                                                        userName = usernameState.value,
                                                        password = paswdState.value,
                                                        authType = "TOTP",
                                                        channel = "MOBILE"
                                                    )
                                                )
                                            }


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

                                            coroutine.launch {
                                                //handle success
                                                viewModel.asanLogin(
                                                    LoginAsanRequest(
                                                        phoneNumber = usernameState.value,
                                                        userId = paswdState.value,
                                                        channel = "INT"
                                                    )
                                                )
                                            }


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


                if (enableLoginWithPin) {
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
                                        popUpTo(navController.graph.id) {
                                            inclusive = true
                                        }
                                    }
                                },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_login_pin),
                                    contentDescription = "",
                                    Modifier
                                        .padding(2.dp)
                                        .size(26.dp)
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


    //show empty field toast
    if (userErrorCheck or pswdErrorCheck) {
        RoundedCornerToast("Please fill in all fields", Toast.LENGTH_SHORT, context)

        LaunchedEffect(Unit) {
            delay(3000)
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

                        navController.navigate(otpNavigationRoute) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }

                    }

                }

            }
        }
    }

    asanLogin?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
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


                val loginResponse = it.data as LoginAsanResponse
                loginResponse?.apply {

                    LaunchedEffect(Unit) {

                        //route to OTP
                        otpScreen.userName = usernameState.value
                        SharedModel.init().loginType.value = selected

                        //easy verification code
                        SharedModel.init().easyVerificationCode.value =
                            "${loginResponse.gniAuthResponseType.verfication}"


                        navController.navigate(loginToEasySignature) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }

                    }

                }

            }
        }
    }

    startMessage?.let {
        when (it) {
            is DataState.Loading -> {
            }

            is DataState.Error -> {
            }

            is DataState.Success -> {
                val data = it.data as GetStartMessage
                data?.apply {

                    message.value = messageText

                    if (isVisible.equals("y", true)) {
                        LaunchedEffect(Unit) {
                            showDialog.value = true
                        }
                    }

                }

            }
        }
    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }

    if (showDialog.value) {
        showDialog(showDialog.value, message.value) {
            showDialog.value = false
        }
    }

}


@Composable
private fun showDialog(
    shown: Boolean,
    message: String,
    onClose: () -> Unit
) {

    AnimatedVisibility(
        visible = shown,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight }
        )
    ) {

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        Color(0xFF203657),
                        shape = RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp
                        )
                    ),
            ) {

                Column(
                    modifier = Modifier
                        .padding(vertical = 10.sdp, horizontal = 15.sdp)
                        .fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material.Text(
                            text = stringResource(R.string.attention),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(com.app.uikit.R.color.grey_text),
                            modifier = Modifier.padding(end = 16.dp),
                            fontSize = 18.sp,
                        )
                        IconButton(onClick = {
                            onClose()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close",
                                tint = colorResource(com.app.uikit.R.color.grey_text)
                            )
                        }
                    }


                    Box() {

                        ClickableLinkTextView(message)
//                        Text(
//                            text = message,
//                            style = TextStyle(
//                                color = Color.White
//                            )
//                        )


                    }
                }
            }
        }
    }
}

@Composable
fun ClickableLinkTextView(text: String) {
    val context = LocalContext.current

//    val annotatedString = buildAnnotatedString {
//        val regex = """https?://\S+""".toRegex()
//        val matches = regex.findAll(text)
//
//        var currentStart = 0
//
//        for (match in matches) {
//            val linkText = match.value
//            val linkStart = match.range.first
//            val linkEnd = match.range.last + 1
//
//            // Append the text before the link
//            append(text.subSequence(currentStart, linkStart))
//
//            // Append the link with a clickable annotation
//            withStyle(style = SpanStyle(color = Color.Blue, fontSize = 16.sp)) {
//                val startIndex = length
//                append(linkText)
//                addStringAnnotation("URL", linkText, startIndex, startIndex + linkText.length)
//            }
//
//            currentStart = linkEnd
//        }
//
//        // Append the remaining text after the last link
//        if (currentStart < text.length) {
//            append(text.subSequence(currentStart, text.length))
//        }
//    }

    val annotatedString = buildAnnotatedString {
        val regex = """https?://\S+""".toRegex()
        val matches = regex.findAll(text)

        var currentStart = 0

        for (match in matches) {
            val linkText = match.value
            val linkStart = match.range.first
            val linkEnd = match.range.last + 1

            // Append the text before the link
            append(text.subSequence(currentStart, linkStart))

            // Append the link with a clickable annotation but no style changes
            val startIndex = length
            append(linkText)
            addStringAnnotation("URL", linkText, startIndex, startIndex + linkText.length)

            currentStart = linkEnd
        }

        // Append the remaining text after the last link
        if (currentStart < text.length) {
            append(text.subSequence(currentStart, text.length))
        }
    }


    ClickableText(
        text = annotatedString,
        style = TextStyle(
            color = Color.White
        ),
        onClick = { offset ->
            val annotations = annotatedString.getStringAnnotations("URL", offset, offset)
            if (annotations.isNotEmpty()) {
                val clickedLink = annotations[0].item

                // Handle the clicked link, e.g., open it in a web browser
                // You can use clickedLink to determine which link was clicked.
                // Example:
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(clickedLink))
                context.startActivity(intent)
            }
        },
        modifier = Modifier.padding(8.dp)
    )
}


fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


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
                onClick = {
                    setSelected(0)
                    //reset values
                    usernameState.value = ""
                    paswdState.value = ""
                },
                modifier = Modifier
                    .background(
                        color = if (selected == 0) Color(0xFF203657) else Color(0xFFF3F7FA),
                        shape = RoundedCornerShape(8.dp)
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
                onClick = {
                    setSelected(1)
                    //reset values
                    usernameState.value = ""
                    paswdState.value = ""
                },
                modifier = Modifier
                    .background(
                        color = if (selected == 1) Color(0xFF203657) else Color(0xFFF3F7FA),
                        shape = RoundedCornerShape(8.dp)
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
                onClick = {
                    setSelected(2)
                    //reset values
                    usernameState.value = ""
                    paswdState.value = ""
                },
                modifier = Modifier
                    .background(
                        color = if (selected == 2) Color(0xFF203657) else Color(0xFFF3F7FA),
                        shape = RoundedCornerShape(8.dp)
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

    val selectedBoxIndex = remember { mutableStateOf(1) }

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

private object PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }

        val formattedText = buildString {
            if (trimmed.length >= 2) {
                append("(${trimmed.take(2)}) ")
            } else if (trimmed.isNotEmpty()) {
                append("(")
                append(trimmed)
            }

            if (trimmed.length >= 4) {
                append("${trimmed.substring(2, 4)}")
            } else if (trimmed.length > 2) {
                append("${trimmed.substring(2)}")
            }

            if (trimmed.length >= 6) {
                append("-${trimmed.substring(4, 6)}")
            } else if (trimmed.length > 4) {
                append("-${trimmed.substring(4)}")
            }

            if (trimmed.length >= 8) {
                append("-${trimmed.substring(6, 8)}")
            } else if (trimmed.length > 6) {
                append("-${trimmed.substring(6)}")
            }

            if (trimmed.length >= 10) {
                append("-${trimmed.substring(8, 10)}")
            } else if (trimmed.length > 8) {
                append("-${trimmed.substring(8)}")
            }

            if (trimmed.length >= 12) {
                append("-${trimmed.substring(10, 12)}")
            } else if (trimmed.length > 10) {
                append("-${trimmed.substring(10)}")
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Adjust the mapping based on formatting changes
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                val originalLength = trimmed.length
                val transformedLength = formattedText.length
                val originalPosition = when {
                    offset <= 2 -> offset
                    offset <= 7 -> offset - 1
                    offset <= 10 -> offset - 2
                    offset <= 13 -> offset - 3
                    else -> offset - 4
                }
                return originalPosition.coerceIn(0, originalLength)
                    .coerceIn(0, transformedLength)
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}


private fun formatPhoneNumber(input: String): String {
    val builder = StringBuilder(input)

    while (builder.length < 11) {
        builder.append("0")
    }

    return buildString {
        append("(")
        append(builder.substring(0, 2))
        append(") ")
        append(builder.substring(2, 5))
        append("-")
        append(builder.substring(5, 7))
        append("-")
        append(builder.substring(7, 9))
        append("-")
        append(builder.substring(9, 11))
    }
}


//@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    RoundedCornerToast("Please fill in all fields", Toast.LENGTH_SHORT, LocalContext.current)
}

