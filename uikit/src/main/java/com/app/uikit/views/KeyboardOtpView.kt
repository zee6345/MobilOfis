package com.app.uikit.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R

@Composable
fun CustomKeyboardOtp() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 6.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButtonOtp("1")
                KeyButtonOtp("2")
                KeyButtonOtp("3")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButtonOtp("4")
                KeyButtonOtp("5")
                KeyButtonOtp("6")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButtonOtp("7")
                KeyButtonOtp("8")
                KeyButtonOtp("9")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                KeyButtonOtp("", R.drawable.clear)
                KeyButtonOtp("0")
            }

        }
    }
}

@Composable
fun KeyButtonOtp(text: String, imageRes: Int? = null) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(64.dp),
        contentAlignment = Alignment.Center,
        content = {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                if (imageRes != null) {
                    val imagePainter = painterResource(imageRes)
                    Image(
                        painter = imagePainter,
                        contentDescription = null, contentScale = ContentScale.Fit
                    )
                } else {
                    Text(text = text, fontSize = 32.sp)
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewCustomKeyboardOtp() {
    CustomKeyboardOtp()
}

