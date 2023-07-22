package com.app.adjustment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.companies.companydisplay.navigation.displayDuringLogin

import com.app.adjustment.navigation.securityScreen
import com.app.adjustment.userprofile.navigation.adjustmentToUserProfile


@Composable
fun AdjustmentsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F7FA))
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
                        .size(width = 32.dp, height = 25.dp)
                        .align(CenterVertically)
                        .clickable {
                                   navController.popBackStack()
                        },
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
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
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
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                            )
                            ThreeBoxComponent()

                        }

                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clickable {
                                navController.navigate(displayDuringLogin)
                            },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Company to display during login",
                                style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_option_arrow_forward),
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
                            .padding(top = 10.dp)
                            .clickable {
                                       navController.navigate(adjustmentToUserProfile)
                            },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "User's profile",
                                style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_option_arrow_forward),
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
                            .padding(top = 10.dp), shape = RoundedCornerShape(12.dp)

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(securityScreen)
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Security",
                                style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_option_arrow_forward),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(35.dp)
                                    .align(CenterVertically)
                                    .padding(end = 12.dp)
                                    .clickable {

                                    },
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
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
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
                                painter = painterResource(id = R.drawable.ic_option_arrow_forward),
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
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_option_arrow_forward),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(35.dp)
                                    .align(CenterVertically)
                                    .padding(end = 12.dp),
                            )
                        }

                    }

                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(shape = RoundedCornerShape(12.dp)) {
                        Row() {
                            Text(
                                text = "Safe Exit",
                                style = TextStyle(color = Color(0xFFFF4E57), fontSize = 16.sp),
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
                Spacer(modifier = Modifier.height(20.dp))
            }


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
private fun ThreeBoxComponent() {
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
    val navController = rememberNavController()
    AdjustmentsScreen(navController)
}