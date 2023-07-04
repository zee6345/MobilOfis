package com.app.auth.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R


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
                Tab(
                    selectedContentColor = Color.Cyan,
                    selected = selected == 0, onClick = {
                        setSelected(0)
                    }) {

                    Box(contentAlignment = Alignment.Center) {
                        androidx.compose.animation.AnimatedVisibility(visible = selected == 0) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    shape = RoundedCornerShape(10.dp),
                                )
                                .background(
                                    color = Color(0xFF203657),
                                ))
                        }
                        Text(modifier = Modifier.padding(12.dp), text = "Login", color = if(selected == 0) Color.White else Color.Black)
                    }


                }
                Tab(selected = selected == 1, onClick = { setSelected(1) }) {
                    Box(contentAlignment = Alignment.Center) {
                        androidx.compose.animation.AnimatedVisibility(visible = selected == 1) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    shape = RoundedCornerShape(10.dp),
                                )
                                .background(
                                    color = Color(0xFF203657),
                                ))
                        }
                        Text(modifier = Modifier.padding(12.dp), text = "Google", color = if(selected == 1) Color.White else Color.Black)
                    }
                }
                Tab(selected = selected == 2, onClick = { setSelected(2) }) {
                    Box(contentAlignment = Alignment.Center) {
                        androidx.compose.animation.AnimatedVisibility(visible = selected == 2) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    shape = RoundedCornerShape(10.dp),
                                )
                                .background(
                                    color = Color(0xFF203657),
                                ))
                        }
                        Text(modifier = Modifier.padding(12.dp), text = "Easy Signature", color = if(selected == 2) Color.White else Color.Black)
                    }
                }
            }

            TextField(
                value = "",
                onValueChange = { /* Handle value change */ },
                label = { Text(text = "Username") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}