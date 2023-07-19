package com.app.adjustment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.navigation.changePassScreen
import com.sky.adjustment.R


@Composable
fun SecurityScreen(navController: NavController) {
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
                        .size(32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.popBackStack()
                        }
                    ,
                    contentDescription = ""
                )
                Text(
                    text = "Security",
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
                .padding(horizontal = 12.dp),
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clickable { navController.navigate(changePassScreen) },
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Change Password",
                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.next_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 12.dp),
                    )
                }


            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Change PIN",
                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.next_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.CenterVertically)
                            .padding(end = 12.dp),
                    )
                }


            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Fingerprint login",
                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                    )
                    Switch()
                }
            }
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun SecurityScreenPreview() {
    SecurityScreen(rememberNavController())
}