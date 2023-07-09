package com.app.auth.pin

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R

@Composable
fun CustomKeyboard() {
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
                KeyButton("1")
                KeyButton("2")
                KeyButton("3")
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("4")
                KeyButton("5")
                KeyButton("6")
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("7")
                KeyButton("8")
                KeyButton("9")
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("", R.drawable.finger_print, )
                KeyButton("0")
                KeyButton("", R.drawable.clear)
            }
        }
    }
}

@Composable
fun KeyButton(text: String, imageRes: Int? = null,imageSize: Dp = 52.dp) {
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
                    Image(
                        painter = imagePainter, contentDescription = null, modifier = Modifier.size(imageSize)

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
    CustomKeyboard()
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
fun com.app.auth.pin.CustomKeyboard() {
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
                com.app.auth.pin.KeyButton("1")
                com.app.auth.pin.KeyButton("2")
                com.app.auth.pin.KeyButton("3")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                com.app.auth.pin.KeyButton("4")
                com.app.auth.pin.KeyButton("5")
                com.app.auth.pin.KeyButton("6")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                com.app.auth.pin.KeyButton("7")
                com.app.auth.pin.KeyButton("8")
                com.app.auth.pin.KeyButton("9")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                com.app.auth.pin.KeyButton("0")
                com.app.auth.pin.KeyButton("<")
            }
        }
    }
}

@Composable
fun com.app.auth.pin.KeyButton(text: String) {
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
fun com.app.auth.pin.PreviewCustomKeyboard() {
    com.app.auth.pin.CustomKeyboard()
}

 */
