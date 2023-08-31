package com.app.uikit.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.app.uikit.R


@Composable
fun CustomKeyboard(onKeyPressed: (String) -> Unit) {
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
                KeyButton(stringResource(R.string._1)) {
                    onKeyPressed(it)
                }
                KeyButton(stringResource(R.string._2)) {
                    onKeyPressed(it)
                }
                KeyButton(stringResource(R.string._3)) {
                    onKeyPressed(it)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton(stringResource(R.string._4)) {
                    onKeyPressed(it)
                }
                KeyButton(stringResource(R.string._5)) {
                    onKeyPressed(it)
                }
                KeyButton(stringResource(R.string._6)) {
                    onKeyPressed(it)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton(stringResource(R.string._7)) {
                    onKeyPressed(it)
                }
                KeyButton(
                    "8"
                ) {
                    onKeyPressed(it)
                }
                KeyButton(
                    stringResource(R.string._9),
                ) {
                    onKeyPressed(it)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton("fngr", R.drawable.finger_print) {
                    onKeyPressed(it)
                }
                KeyButton(stringResource(R.string._0)) {
                    onKeyPressed(it)
                }
                KeyButton(stringResource(R.string.del), R.drawable.clear) {
                    onKeyPressed(it)
                }
            }
        }
    }
}

@Composable
fun KeyButton(
    text: String,
    imageRes: Int? = null,
    onKeyPressed: (String) -> Unit
) {
    Box(modifier = Modifier.padding(8.dp).size(70.dp).aspectRatio(1f),
        contentAlignment = Alignment.Center,
        content = {
            Button(
                onClick = {
                    onKeyPressed(text)
                },
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                if (imageRes != null) {
                    val imagePainter = painterResource(imageRes)
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(10.dp),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text(
                        text = text,
                        style = TextStyle(
                            fontSize = 32.sp,
                            lineHeight = 30.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF223142),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        })
}


@Preview
@Composable
fun PreviewCustomKeyboard() {
    val navController = rememberNavController()
    CustomKeyboard {

    }
}



