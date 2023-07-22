package com.app.adjustment.changepassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R


@Composable
fun ForgetPasswordScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F7FA)),
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.1f),
            color = Color(0xFF203657),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(height = 25.dp, width = 32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { navController.popBackStack() },
                    contentDescription = ""
                )
                Text(
                    text = "Changing the password",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }
        Column(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 12.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                TextFieldWithEndDrawable("Current password")
                TextFieldWithEndDrawable("New password")
                TextFieldWithEndDrawable("Repeat the new password")
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFE7EEFC), // Change the background color here
                        contentColor = Color(0xFF203657) // Change the text color here if needed
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF203657), // Change the border color here
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        "Back", modifier = Modifier.padding(vertical = 10.dp), style = TextStyle(
                            fontSize = 17.sp, shadow = null
                        )
                    )
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF203657), // Change the background color here
                        contentColor = Color.White // Change the text color here if needed
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Text(
                        "Refresh", modifier = Modifier.padding(vertical = 10.dp), style = TextStyle(
                            color = Color.White, fontSize = 17.sp, shadow = null
                        )
                    )
                }

            }


        }
    }
}


@Composable
fun TextFieldWithEndDrawable(hint: String) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    TextField(value = textState.value,
        onValueChange = { textState.value = it },
        label = {
            Text(
                text = hint, style = TextStyle(fontSize = 14.sp, color = Color(0xFF859DB5))
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .background(Color.Transparent)
            .border(1.dp, Color(0xFFE7EEFC), shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_options_visible),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        })
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun ChangePasswordScreenPreview() {
    ForgetPasswordScreen(rememberNavController())
}