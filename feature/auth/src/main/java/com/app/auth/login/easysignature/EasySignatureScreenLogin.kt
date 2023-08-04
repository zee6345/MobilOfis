package com.app.auth.login.easysignature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R
import com.app.uikit.borders.dashedBorder

@Composable
fun EasySignatureLoginScreen() {

    val usernameState = remember {
        mutableStateOf("")
    }

    val paswdState = remember {
        mutableStateOf("")
    }
    val selectedBoxIndex = remember { mutableStateOf(0) }



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
                    text = if (selectedBoxIndex.value == 0) "Login with Easy Signup" else "Login",
                    style = TextStyle(color = Color.White, fontSize = 24.sp)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))





            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // ... (existing code)

                Box(modifier = Modifier
                    .padding(6.dp)
                    .height(6.dp)
                    .weight(1f)
                    .background(
                        if (selectedBoxIndex.value >= 0) Color(R.color.background_card_blue) else Color(0xFFCCD6DE),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { selectedBoxIndex.value = 0 }) {

                }
                Box(modifier = Modifier
                    .padding(6.dp)
                    .height(6.dp)
                    .weight(1f)
                    .background(
                        if (selectedBoxIndex.value >= 1) Color(R.color.background_card_blue) else Color(0xFFCCD6DE),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { selectedBoxIndex.value = 1 }) {

                }
                Box(modifier = Modifier
                    .padding(6.dp)
                    .height(6.dp)
                    .weight(1f)
                    .background(
                        if (selectedBoxIndex.value >= 2) Color(R.color.background_card_blue) else Color(0xFFCCD6DE),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { selectedBoxIndex.value = 2 }) {}
            }

            if (selectedBoxIndex.value == 0) {
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = usernameState.value,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        usernameState.value = it
                    },
                    label = {
                        Text(
                            text = "Mobile number",
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(R.color.background_card_blue),
                        unfocusedBorderColor = Color(com.app.home.R.color.border_grey),
                        unfocusedLabelColor = Color(com.app.adjustment.R.color.grey_text),
                        focusedLabelColor = Color(R.color.background_card_blue),
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
                            text = "User ID", fontSize = 14.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(R.color.background_card_blue),
                        unfocusedBorderColor = Color(com.app.home.R.color.border_grey),
                        unfocusedLabelColor = Color(com.app.adjustment.R.color.grey_text),
                        focusedLabelColor = Color(R.color.background_card_blue)
                    ),
                    singleLine = true
                )



                    Spacer(modifier = Modifier.padding(top = 10.dp))

                Button(
                    onClick = { selectedBoxIndex.value = 1 },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),// Optional: To override other button colors

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF203657), RoundedCornerShape(8.dp))

                ) {
                    Text(
                        "Login", modifier = Modifier.padding(vertical = 12.dp), color = Color.White
                    )
                }
            }

            if (selectedBoxIndex.value == 1 || selectedBoxIndex.value == 2) {

                Column(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                                        3.dp, Color(com.app.home.R.color.border_grey)
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
                                        .height(80.dp).width(100.dp),
                                    contentDescription = ""
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.question_icon),
                                    modifier = Modifier.size(50.dp)
                                        .align(Alignment.CenterVertically).padding(end = 12.dp),
                                    contentDescription = ""
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .dashedBorder(
                                        3.dp, Color(com.app.home.R.color.border_grey)
                                    )
                                    .padding(horizontal = 12.dp , vertical = 22.dp),
                                text = "Please accept the query sent to your phone. Compare the checking code of the survey to the same code as the following code.",
                                style = TextStyle(fontSize = 16.sp)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 32.dp, bottom = 5.dp)
                                    .fillMaxWidth(),
                                text = "Check code",
                                style = TextStyle(
                                    color = Color(com.app.adjustment.R.color.grey_text),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            )

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "7960",
                                style = TextStyle(fontSize = 32.sp, textAlign = TextAlign.Center)
                            )

                        }
                    }


                }



            }


        }
    }


}

@Composable
private fun ScrollableButtons(selectedBoxIndex: MutableState<Int>) {
    var offset by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(orientation = Orientation.Vertical,
                // Scrollable state: describes how to consume
                // scrolling delta and update offset
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }),
//        contentAlignment = Alignment.BottomCenter
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
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(com.app.home.R.color.border_grey), // Change the background color here
                    contentColor = Color(0xFF203657) // Change the text color here if needed
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)

            ) {
                Text(
                    "Close", modifier = Modifier.padding(vertical = 6.dp), style = TextStyle(
                        fontSize = 17.sp, shadow = null
                    )
                )
            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF203657), contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    if (selectedBoxIndex.value == 1) "Continue" else "Confirm",
                    modifier = Modifier.padding(vertical = 6.dp),
                    style = TextStyle(
                        color = Color.White, fontSize = 17.sp, shadow = null
                    )
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun EasySignatureLoginScreenPreview() {
    EasySignatureLoginScreen()
}