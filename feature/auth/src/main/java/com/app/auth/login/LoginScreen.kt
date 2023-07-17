package com.app.auth.login


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.login.components.alertdialog.CallTopAlertDialog
import com.app.auth.login.components.bottomSheet.dashedBorder
import com.app.auth.login.components.utils.TimerTextView
import com.app.auth.login.navigation.otpNavigationRoute
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
var bottomSheetState: BottomSheetScaffoldState ?=null
var coroutineScope: CoroutineScope ?=null

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(navController: NavController) {
    val (selected, setSelected) = remember {
        mutableStateOf(0)
    }

    val usernameState = remember {
        mutableStateOf("")
    }

    val paswdState = remember {
        mutableStateOf("")
    }

    BottomSheetScaffold(sheetPeekHeight = 50.sdp,
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
                                    .width(80.sdp)
                                    .height(4.sdp)
                                    .background(
                                        color = Color(0xFFEDEEFB),
                                        shape = RoundedCornerShape(size = 10.sdp)
                                    )
                                    .align(Alignment.CenterVertically)
                            )
                        }


                        androidx.compose.material3.Text(
                            text = "information And Content",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.sdp),
                            fontWeight = FontWeight.Bold,
                        )

                BottomSheetItems(
                    R.drawable.ic_location, "Branches and ATMs"
                )
                BottomSheetItems(
                    R.drawable.ic_tariff, "Tariffs"
                )
                BottomSheetItems(
                    R.drawable.ic_whatsapp_support, "WhatsApp support"
                )
                BottomSheetItems(
                    R.drawable.ic_call_support, "Call Center"
                )
//                BottomSheetItems(
//                    R.drawable.language, "Application Language"
//                )

                Row(
                    modifier = Modifier
                        .padding(top = 10.sdp, start = 18.sdp, bottom = 10.sdp)
                        .dashedBorder(
                            3.sdp, Color(0xFFE7EEFC)
                        )
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
                    androidx.compose.material3.Text(
                        text = "Application Language",
                        modifier = Modifier.padding(vertical = 12.dp)
                    )


                    LanguageOptions()

                }

            }


        }) {

                Column(modifier = Modifier.fillMaxSize()) {
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


                        TabRow(
                            selectedTabIndex = selected,
                            indicator = {},
                            divider = {},
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Tab(
                                selectedContentColor = Color.Cyan,
                                selected = selected == 0,
                                onClick = {
                                    setSelected(0)
                                }) {

                                Box(contentAlignment = Alignment.Center) {
                                    androidx.compose.animation.AnimatedVisibility(visible = selected == 0) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(
                                                    shape = RoundedCornerShape(10.dp),
                                                )
                                                .background(
                                                    color = Color(0xFF203657),
                                                )
                                        )
                                    }
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = "Login",
                                        color = if (selected == 0) Color.White else Color.Black
                                    )
                                }


                            }
                            Tab(selected = selected == 1, onClick = { setSelected(1) }) {
                                Box(contentAlignment = Alignment.Center) {
                                    androidx.compose.animation.AnimatedVisibility(visible = selected == 1) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(
                                                    shape = RoundedCornerShape(10.dp),
                                                )
                                                .background(
                                                    color = Color(0xFF203657),
                                                )
                                        )
                                    }
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = "Google",
                                        color = if (selected == 1) Color.White else Color.Black
                                    )
                                }
                            }
                            Tab(selected = selected == 2, onClick = { setSelected(2) }) {
                                Box(contentAlignment = Alignment.Center) {
                                    androidx.compose.animation.AnimatedVisibility(visible = selected == 2) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(
                                                    shape = RoundedCornerShape(10.dp),
                                                )
                                                .background(
                                                    color = Color(0xFF203657),
                                                )
                                        )
                                    }
                                    Text(
                                        modifier = Modifier.padding(12.dp),
                                        text = "Easy Signature",
                                        color = if (selected == 2) Color.White else Color.Black
                                    )
                                }
                            }
                        }


                        OutlinedTextField(
                            value = usernameState.value,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                /* Handle value change */
                                usernameState.value = it
                            },
                            label = {
                                Text(
                                    text = if (selected == 2) "Mobile Number" else "Username",
                                    fontSize = 14.sp
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
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
                            onValueChange = { /* Handle value change */
                                paswdState.value = it
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            label = {
                                Text(
                                    text = if (selected == 2) "User ID" else "Password",
                                    fontSize = 14.sp
                                )
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
                                        coroutineScope!!.launch {
                                            bottomSheetState!!.bottomSheetState.expand()
                                        }
                                    })
                            }

                        } else {
                            Spacer(modifier = Modifier.padding(top = 10.sdp))
                        }

                        Button(
                            onClick = {
                                navController.navigate(otpNavigationRoute)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),// Optional: To override other button colors

                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF203657), RoundedCornerShape(8.dp))

                        ) {
                            Text(
                                "Login",
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = Color.White
                            )
                        }

                    }
                }

            }
//        }
//    }
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
            onClose = { showDialog.value = false}
        )
    }

}


@Composable
private fun BottomSheetItems(iconRes: Int, title: String) {
    Row(
        modifier = Modifier
            .padding(top = 10.sdp, start = 16.sdp)
            .dashedBorder(
                3.sdp, Color(0xFFE7EEFC)
            )
            .fillMaxWidth(),
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
        androidx.compose.material3.Text(text = title, modifier = Modifier.padding(vertical = 12.dp))

    }
}


@Composable
fun LanguageOptions() {
    val languageOptions = listOf("AZ", "EN", "RU")
    var selectedLanguage by remember { mutableStateOf(languageOptions[0]) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        languageOptions.forEach { language ->
            val isSelected = language == selectedLanguage

            Box(
                Modifier
                    .width(36.sdp)
                    .height(25.sdp)
                    .background(
                        color = if (isSelected) Color(0xFF203657) else Color(0xFFE7EEFC),
                        shape = RoundedCornerShape(size = 6.sdp)
                    )
                    .clickable { selectedLanguage = language }
                    .align(Alignment.CenterVertically)

            ) {
                Text(
                    language,
                    color = if (isSelected) Color.White else Color(0xFF203657),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 4.sdp)
                )
            }

            Spacer(modifier = Modifier.width(2.sdp))

        }
    }
}



@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}