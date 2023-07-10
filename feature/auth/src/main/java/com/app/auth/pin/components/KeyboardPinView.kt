package com.app.auth.pin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.auth.R
import com.app.auth.login.navigation.resetPinNavigationRoute

@Composable
fun CustomKeyboard(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("1", navController = navController)
                KeyButton("2", navController = navController)
                KeyButton("3", navController = navController)
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("4", navController = navController)
                KeyButton("5", navController = navController)
                KeyButton("6", navController = navController)
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("7", navController = navController)
                KeyButton("8", navController = navController)
                KeyButton("9", navController = navController)
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("", R.drawable.finger_print, navController)
                KeyButton("0", navController = navController)
                KeyButton("", R.drawable.clear, navController)
            }
        }
    }
}

@Composable
fun KeyButton(text: String, imageRes: Int? = null, navController: NavController) {
    Box(modifier = Modifier
        .padding(8.dp)
        .size(64.dp)
        .aspectRatio(1f),
        contentAlignment = Alignment.Center,
        content = {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                if (imageRes != null) {
                    val imagePainter = painterResource(imageRes)
                    Image(painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clickable {
                                navController.navigate(resetPinNavigationRoute)
                            }

                    )
                } else {
                    Text(text = text, fontSize = 32.sp)
                }
            }
        })
}

@Preview
@Composable
fun PreviewCustomKeyboard() {
//    CustomKeyboard(navController)
}


/*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun com.app.auth.pin.components.CustomKeyboard() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                com.app.auth.pin.components.KeyButton("1")
                com.app.auth.pin.components.KeyButton("2")
                com.app.auth.pin.components.KeyButton("3")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                com.app.auth.pin.components.KeyButton("4")
                com.app.auth.pin.components.KeyButton("5")
                com.app.auth.pin.components.KeyButton("6")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                com.app.auth.pin.components.KeyButton("7")
                com.app.auth.pin.components.KeyButton("8")
                com.app.auth.pin.components.KeyButton("9")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                com.app.auth.pin.components.KeyButton("0")
                com.app.auth.pin.components.KeyButton("<")
            }
        }
    }
}

@Composable
fun com.app.auth.pin.components.KeyButton(text: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(64.dp)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center,
        content = {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxSize(),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Text(text = text)
            }
        }
    )
}

@Preview
@Composable
fun com.app.auth.pin.components.PreviewCustomKeyboard() {
    com.app.auth.pin.components.CustomKeyboard()
}

 */
