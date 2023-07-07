package com.app.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R

@Composable
fun SuccessfulRegistrationScreen() {
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
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .padding(top = 32.dp, start = 18.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    ) {

                        androidx.compose.material3.Icon(
                            painter = painterResource(id = R.drawable.clear),
                            modifier = Modifier
                                .align(Alignment.Top)
                                .padding(top = 6.dp),
                            contentDescription = ""
                        )
                        Column() {
                            Text(
                                text = "Successful Registration!",
                                style = TextStyle(color = Color.White, fontSize = 24.sp)
                            )
                            Text(
                                text = "PIN set",
                                style = TextStyle(color = Color.White, fontSize = 16.sp)
                            )
                        }

                    }


                }


            }
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.clear),
                contentDescription = "",
            )
            Button(
                onClick = { /* Button click action */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),// Optional: To override other button colors

                modifier = Modifier
                    .fillMaxWidth().padding(bottom = 22.dp)
                    .background(Color(0xFF203657), RoundedCornerShape(8.dp))
            ) {
                Text("Continue", modifier = Modifier.padding(vertical = 12.dp), color = Color.White)
            }

        }

    }

}


@Preview(device = Devices.PIXEL_4)
@Composable
fun RegistrationScreenPreview() {
    SuccessfulRegistrationScreen()
}