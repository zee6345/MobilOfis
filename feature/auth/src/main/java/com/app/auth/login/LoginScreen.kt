package com.app.auth.login

import CustomKeyboard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.login.components.bottomSheet.ForgetPasswordBottomSheetScreen
import com.app.auth.login.components.bottomSheet.InformationBottomSheetScreen


@Composable
fun LoginScreen() {
    val (selected, setSelected) = remember {
        mutableStateOf(0)
    }

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
                .weight(0.8f)
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
                Tab(selectedContentColor = Color.Cyan, selected = selected == 0, onClick = {
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
//
            OutlinedTextField(
                value = "",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { /* Handle value change */ },
                label = { Text(text = "Username", fontSize = 14.sp) },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = Color(0xFF223142),
                    unfocusedBorderColor = Color(0xFFE7EEFC),
                    unfocusedLabelColor = Color(0xFF859DB5),
                    focusedLabelColor = Color(0xFF223142),
                ),
            )
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle value change */ },
                label = { Text(text = "Password", fontSize = 14.sp) },
                visualTransformation = PasswordVisualTransformation(),
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
//                trailingIcon = EndIconDrawable(painter = painterResource(R.drawable.app_logo)),
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
                    Text(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                        text = "01:00",
                        color = Color(0xFF223142)
                    )

                }

                ClickableText(
                    modifier = Modifier.padding(5.dp),
                    text = AnnotatedString(text = "Forgot your password?"),
//                    color = Color(0xFF203657),
                    onClick = { }
                )
            }
            androidx.compose.material.Button(
                onClick = { /* Button click action */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),// Optional: To override other button colors

                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF203657), RoundedCornerShape(8.dp))
            ) {
                Text("Login", modifier = Modifier.padding(vertical = 12.dp), color = Color.White)
            }
//            ForgetPasswordBottomSheetScreen()
            InformationBottomSheet()

        }
    }

}

@Composable
fun EndIconDrawable(painter: Painter) {
    Box(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
    ) {
        // Adjust the position and size of the drawable as needed
        androidx.compose.material.Icon(
            painter = painter,
            contentDescription = null, // Set appropriate content description
            tint = Color.Gray,
            modifier = Modifier
                .align(alignment = androidx.compose.ui.Alignment.CenterEnd)
                .padding(end = 8.dp)
        )
    }
}


@Composable
fun InformationBottomSheet() {
    InformationBottomSheetScreen()
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}