package com.app.home.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.app.home.menu.component.CardMenuContent
import com.app.home.R
import com.app.home.menu.component.TabLayoutMenu

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F7FA))
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.25f),
//            color = Color(0xFF203657).copy(alpha = 0.5f),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(color = Color(0xFF203657).copy(alpha = 0.9f)),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 22.dp, end = 12.dp)
                    ) {
                        Row(modifier = Modifier.weight(0.7f)) {
                            Image(
                                painter = painterResource(id = R.drawable.business_icon),
                                modifier = Modifier
                                    .size(25.dp)
                                    .align(Alignment.Top),
                                contentDescription = ""
                            )
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(start = 5.dp)
                            ) {
                                Text(
                                    text = "International Real \nEstate and Appraisal Agency",
                                    style = TextStyle(color = Color.White, fontSize = 14.sp),
                                    modifier = Modifier
                                        .padding(start = 3.dp)
                                        .widthIn(max = 140.dp)
                                        .wrapContentWidth(align = Alignment.Start),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Semire", style = TextStyle(
                                        color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp
                                    )
                                )
                            }

                            Image(
                                painter = painterResource(id = R.drawable.dropdown_icon),
                                modifier = Modifier
                                    .size(15.dp)
                                    .align(Alignment.Top),
                                contentDescription = ""
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.3f)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Top
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.eye_icon),
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Top),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Image(
                                painter = painterResource(id = R.drawable.notification_icon),
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Top),
                                contentDescription = ""
                            )
                        }

                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(color = Color(0xFF203657)),
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        Modifier.padding(horizontal = 22.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        val annotatedString = buildAnnotatedString {
                            withStyle(style = SpanStyle(Color.White.copy(0.5f), fontSize = 14.sp)) {
                                append("Total accounts ")
                            }
                            withStyle(style = SpanStyle(Color.White, fontSize = 14.sp)) {
                                append("balance")
                            }
                        }

                        Row() {
                            Text(
                                text = annotatedString,
                                modifier = Modifier
                                    .align(Bottom)
                                    .padding(end = 8.dp, top = 3.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.dropdown_icon),
                                modifier = Modifier
                                    .size(15.dp)
                                    .align(Bottom),
                                contentDescription = ""
                            )

                        }
                        Row() {
                            Text(
                                text = "10 000 000.",
                                modifier = Modifier.align(Top),
                                style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                            Text(
                                text = "00",
                                modifier = Modifier
                                    .align(Bottom)
                                    .padding(vertical = 3.dp),
                                style = TextStyle(fontSize = 24.sp),
                                color = Color.White
                            )
                            Text(
                                text = "₼",
                                modifier = Modifier
                                    .padding(end = 22.dp)
                                    .padding(3.dp)
                                    .align(Bottom),
                                style = TextStyle(fontSize = 24.sp),
                                color = Color.White
                            )
                        }

                    }
                }

            }

        }
        Column(
            modifier = Modifier
                .weight(0.75f)
                .padding(horizontal = 5.dp)
        ) {
            CardMenuContent()
            TabLayoutMenu(navController)

        }


    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
//SimpleTabRow()
    MenuScreen(rememberNavController())
}