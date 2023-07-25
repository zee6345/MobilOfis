package com.app.auth.login


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.login.components.alertdialog.CallTopAlertDialog
import com.app.auth.login.components.alertdialog.ShowProgressDialog
import com.app.auth.login.components.bottomSheet.ForgetPasswordModalBottomSheet
import com.app.auth.login.components.bottomSheet.dashedBorder
import com.app.auth.login.components.utils.TimerTextView
import com.app.auth.login.navigation.otpNavigationRoute
import com.app.auth.utils.Message
import com.app.network.data.DataState
import com.app.network.data.callModels.LoginRequest
import com.app.network.data.responseModels.LoginResponse
import com.app.network.helper.MainApp
import com.app.network.viewmodel.LoginViewModel
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {

    val (selected, setSelected) = remember { mutableStateOf(0) }
    val usernameState = remember { mutableStateOf("") }
    val paswdState = remember { mutableStateOf("") }
    val showForgetPassBottomSheetSheet = rememberSaveable { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    var userErrorCheck by remember { mutableStateOf(false) }
    var pswdErrorCheck by remember { mutableStateOf(false) }
    var isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val loginData by viewModel.data.collectAsState()

    BottomSheetScaffold(
        sheetPeekHeight = 50.sdp,
        sheetShape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
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
                                color = Color(0xFFEDEEFB), shape = RoundedCornerShape(size = 10.sdp)
                            )
                            .align(Alignment.CenterVertically)
                    )
                }


                Text(
                    text = "information And Content",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.sdp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )

                BottomSheetItems(R.drawable.ic_location, "Branches and ATMs", true)
                BottomSheetItems(R.drawable.ic_tariff, "Tariffs", true)
                BottomSheetItems(R.drawable.ic_whatsapp_support, "WhatsApp support", true)
                BottomSheetItems(R.drawable.ic_call_support, "Call Center", true)

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
                        text = "Application Language",
                        modifier = Modifier.padding(vertical = 12.dp)
                    )


                    LanguageOptions()

                }

            }


        }) {

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
                    Text(
                        modifier = Modifier.align(Alignment.BottomStart),
                        text = "Mobile Office\nWelcome",
                        style = TextStyle(color = Color.White, fontSize = 29.sp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(0.7f)
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
                            text = if (selected == 2) "Mobile Number" else "Username",
                            fontSize = 14.sp
                        )
                    }, trailingIcon = {
                        if (userErrorCheck)
                            Icon(Icons.Filled.Info, "Error", tint = Color.Red)
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF223142),
                        unfocusedBorderColor = Color(0xFFE7EEFC),
                        unfocusedLabelColor = Color(0xFF859DB5),
                        focusedLabelColor = Color(0xFF223142),
                    ),
                    singleLine = true
                )

                OutlinedTextField(
                    value = paswdState.value,
                    onValueChange = { paswdState.value = it },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = {
                        Text(
                            text = if (selected == 2) "User ID" else "Password",
                            fontSize = 14.sp
                        )
                    }, trailingIcon = {
                        if (pswdErrorCheck)
                            Icon(Icons.Filled.Info, "Error", tint = Color.Red)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF223142),
                        unfocusedBorderColor = Color(0xFFE7EEFC),
                        unfocusedLabelColor = Color(0xFF859DB5),
                        focusedLabelColor = Color(0xFF223142)
                    ),
                    singleLine = true
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

                            TimerTextView()

                        }

                        ClickableText(modifier = Modifier.padding(5.dp),
                            text = AnnotatedString(text = "Forgot your password?"),
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

//                        navController.navigate(otpNavigationRoute)

                        when (selected) {
                            0 -> {
                                Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT)
                                    .show()
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

                            2 -> {
                                Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }



                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),// Optional: To override other button colors
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF203657), RoundedCornerShape(8.dp))

                ) {
                    Text(
                        "Login", modifier = Modifier.padding(vertical = 12.dp), color = Color.White
                    )
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
            title = "Attention!",
            message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu.",
            onClose = { showDialog.value = false }
        )
    }


    /**
     * handle login response data
     */
    loginData?.let {
        when (it) {
            is DataState.Loading -> {
                Log.e("mTAG", "LoginScreen: loading...")
                isLoading.value = true
                if (isLoading.value) {
                    ShowProgressDialog(isLoading)
                } else {

                }
            }

            is DataState.Error -> {
                Message.showMessage(context, "Failed to login!")
            }

            is DataState.Success -> {
                val loginResponse = it.data as LoginResponse
                loginResponse?.apply {
                    isLoading.value = false


                    LaunchedEffect(Unit) {
                        //route to OTP
                        MainApp.session.put("username", usernameState.value)
                        navController.navigate(otpNavigationRoute)
                    }

                }

            }
        }
    }


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

        Tab(
            selectedContentColor = Color.Cyan,
            selected = selected == 0,
            onClick = { setSelected(0) },
            modifier = Modifier
                .background(color = if (selected == 0) Color(0xFF203657) else Color(0xFFF3F7FA))
                .clip(shape = RoundedCornerShape(16.dp))
        ) {

            Text(
                modifier = Modifier.padding(12.dp),
                text = "Login",
                style = TextStyle(fontSize = 12.sp),
                color = if (selected == 0) Color.White else Color.Black
            )

        }



        Tab(
            selected = selected == 1,
            onClick = { setSelected(1) },
            modifier = Modifier
                .background(color = if (selected == 1) Color(0xFF203657) else Color(0xFFF3F7FA))
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Google",
                style = TextStyle(fontSize = 12.sp),
                color = if (selected == 1) Color.White else Color.Black
            )
        }

        Tab(
            selected = selected == 2,
            onClick = { setSelected(2) },
            modifier = Modifier
                .background(color = if (selected == 2) Color(0xFF203657) else Color(0xFFF3F7FA))
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Easy Signature",
                style = TextStyle(fontSize = 12.sp),
                color = if (selected == 2) Color.White else Color.Black
            )
        }
    }
}


@Composable
private fun BottomSheetItems(iconRes: Int, title: String, showBorder: Boolean) {

    val borderModify = Modifier
        .dashedBorder(3.sdp, Color(0xFFE7EEFC))
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
                    .height(32.dp)
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
                if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 0 }) {
            androidx.compose.material.Text(
                text = "AZ", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 1 }) {
            androidx.compose.material.Text(
                text = "EN", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 2) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 2 }) {
            androidx.compose.material.Text(
                text = "RU", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 2) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}