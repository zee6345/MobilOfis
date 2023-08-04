package com.app.uikit.views

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.uikit.R


@Composable
fun CustomKeyboard(navController: NavController, screen: String?, onKeyPressed: (String) -> Unit) {
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
                KeyButton(
                    stringResource(R.string._1),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string._2),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string._3),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton(
                    stringResource(R.string._4),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string._5),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string._6),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton(
                    stringResource(R.string._7),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string._8),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string._9),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                KeyButton(
                    "",
                    R.drawable.finger_print,
                    navController,
                    "fingerPrint",
                    screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string._0),
                    navController = navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
                KeyButton(
                    stringResource(R.string.del),
                    R.drawable.clear,
                    navController,
                    screen = screen,
                    onKeyPressed = onKeyPressed
                )
            }
        }
    }
}

@Composable
fun KeyButton(
    text: String,
    imageRes: Int? = null,
    navController: NavController,
    button: String? = null,
    screen: String?,
    onKeyPressed: (String) -> Unit
) {
    Box(modifier = Modifier
        .padding(8.dp)
        .size(64.dp)
        .aspectRatio(1f),
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
                            .padding(5.dp),
                        contentScale = ContentScale.Fit
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
    val navController = rememberNavController()
    CustomKeyboard(navController, "ResetPin") {

    }
}



