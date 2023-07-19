package com.app.adjustment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.sky.adjustment.R

@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
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
                    painter = painterResource(id = R.drawable.back_icon),
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = ""
                )
                Text(
                    text = "User's profile",
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


            }


        }

    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen()
}