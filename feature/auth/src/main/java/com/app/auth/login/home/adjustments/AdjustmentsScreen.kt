package com.app.auth.login.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
fun AdjustmentsScreen() {
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
                        .align(CenterVertically),
                    contentDescription = ""
                )
                Text(
                    text = "Adjustments",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }
        Column(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 12.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
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
                        val (selected, setSelected) = remember {
                            mutableStateOf(true)
                        }
                        Text(
                            text = "Language of the application",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        ThreeBoxComponent()

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
                            text = "Company to display during login",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .align(CenterVertically)
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
                            text = "User's profile",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .align(CenterVertically)
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
                            text = "Security",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .align(CenterVertically)
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
                            text = "Receive push notifications",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        Switch()

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
                            text = "Night mode",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        Switch()
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
                            text = "About the bank",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .align(CenterVertically)
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
                            text = "Contact information",
                            style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .align(CenterVertically)
                                .padding(end = 12.dp),
                        )
                    }

                }

            }


        }
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(shape = RoundedCornerShape(12.dp)) {
                Row() {
                    Text(
                        text = "Safe Exit",
                        style = TextStyle(color = Color(0xFFFF4E57), fontSize = 14.sp),
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.exit_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(35.dp)
                            .align(CenterVertically)
                            .padding(end = 12.dp),
                    )
                }
            }
            Text(
                text = "Application version: 00001",
                style = TextStyle(color = Color.Black, fontSize = 12.sp),
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
            )
        }

    }


}


@Composable
fun Switch() {
    var switchCheckedState by remember { mutableStateOf(false) }

    Switch(
        checked = switchCheckedState,
        onCheckedChange = { switchCheckedState = it },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = Color(0xFF1DD580),
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = Color(0xFFE7EEFC),

            )
    )

}


@Composable
fun ThreeBoxComponent() {
    val selectedBoxIndex = remember { mutableStateOf(-1) }

    Row() {
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 0 }) {
            Text(
                text = "AZ", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 1 }) {
            Text(
                text = "EN", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 2) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 2 }) {
            Text(
                text = "RU", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 2) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun AdjustmentsScreenPreview() {
    AdjustmentsScreen()
//    Demo_SwitchComponent()
}