package com.app.auth.pin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.auth.R
import com.app.auth.pin.navigation.welcomePinScreen

@Composable
fun SuccessfulRegistrationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
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

                        Image(
                            painter = painterResource(id = R.drawable.success_icon),
                            modifier = Modifier
                                .align(Alignment.Top)
                                .padding(top = 6.dp)
                                .size(30.dp),
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.registration),
                modifier = Modifier
                    .padding(top = 6.dp)
                    .size(220.dp),
                contentDescription = ""
            )


            Button(
                onClick = { navController.navigate(welcomePinScreen) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 22.dp)
                    .background(Color(0xFF203657), RoundedCornerShape(8.dp)),

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF203657),
                    contentColor = Color.White
                )

            ) {
                Text("Continue", modifier = Modifier.padding(vertical = 12.dp), color = Color.White)
            }

        }

    }

}


@Preview(device = Devices.PIXEL_4)
@Composable
fun RegistrationScreenPreview() {

    SuccessfulRegistrationScreen(rememberNavController())
}