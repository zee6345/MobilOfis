package com.app.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.auth.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.2f),
            color = Color(0xFF203657),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(color = Color(0xFF223142)),
//                    verticalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {

                        Image(
                            painter = painterResource(id = R.drawable.business_icon),
                            modifier = Modifier
                                .size(38.dp)
                                .align(Alignment.Top)
                                .padding(start = 12.dp),
                            contentDescription = ""
                        )
                        Column(modifier = Modifier.wrapContentHeight()) {
                            Text(
                                text = "International Real \nEstate and Appraisal Agency",
                                style = TextStyle(color = Color.White, fontSize = 16.sp),
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .widthIn(max = 170.dp)
                                    .wrapContentWidth(align = Alignment.Start),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Semire", style = TextStyle(
                                    color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp
                                ), modifier = Modifier.padding(start = 12.dp)
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.dropdown_icon),
                            modifier = Modifier
                                .size(28.dp)
                                .align(Alignment.Top)
                                .padding(start = 12.dp),
                            contentDescription = ""
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 12.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.eye_icon),
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(top = 5.dp)
                                    .align(Alignment.Top),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Image(
                                painter = painterResource(id = R.drawable.notification_icon),
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(5.dp)
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
                ) {
                    var mExpanded by remember { mutableStateOf(false) }

                    val mCities = listOf("one", "two", "three", "four")

                    var mSelectedText by remember { mutableStateOf("") }

                    Column(Modifier.padding(20.dp)) {

                        // Create an Outlined Text Field
                        // with icon and not expanded
                        val annotatedString = buildAnnotatedString {
                            withStyle(style = SpanStyle(Color.White.copy(0.5f), fontSize = 16.sp)) {
                                append("Total accounts ")
                            }
                            withStyle(style = SpanStyle(Color.White, fontSize = 16.sp)) {
                                append("balance")
                            }
                        }

                        Row() {
                            Text(text = annotatedString)
                            Image(
                                painter = painterResource(id = R.drawable.dropdown_icon),
                                modifier = Modifier
                                    .size(28.dp)
                                    .align(Alignment.Top)
                                    .padding(start = 12.dp)
                                    .clickable { mExpanded = !mExpanded },
                                contentDescription = ""
                            )

                        }

                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },
                            modifier = Modifier
                                .padding(top = 32.dp)
                                .wrapContentWidth()
                        ) {
                            mCities.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    mSelectedText = label
                                    mExpanded = false
                                }) {}
                            }
                        }
                    }
                }

            }

        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 12.dp)
        ) {
            val (selected, setSelected) = remember {
                mutableStateOf(0)
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp), shape = RoundedCornerShape(8.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Signature\nWaiting",
                            modifier = Modifier
                                .padding(horizontal = 7.dp)
                                .align(CenterVertically),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "1", modifier = Modifier
                                .padding(16.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = Color(0xff2CCAD3), radius = 12.dp.toPx()
                                    )
                                }, fontSize = 14.sp, color = Color.White
                        )

                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp), shape = RoundedCornerShape(8.dp)

                ) {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Time\npast",
                            modifier = Modifier
                                .padding(horizontal = 7.dp)
                                .align(CenterVertically),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "1", modifier = Modifier
                                .padding(16.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = Color(0xffF48A1D), radius = 12.dp.toPx()
                                    )
                                }, fontSize = 14.sp, color = Color.White
                        )

                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp), shape = RoundedCornerShape(8.dp)

                ) {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Sent to\nBank",
                            modifier = Modifier
                                .padding(horizontal = 7.dp)
                                .align(CenterVertically),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "1", modifier = Modifier
                                .padding(16.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = Color.Red, radius = 12.dp.toPx()
                                    )
                                }, fontSize = 14.sp, color = Color.White
                        )

                    }
                }

            }
            androidx.compose.material3.TabRow(selectedTabIndex = selected,
                indicator = {},
                divider = {},
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                androidx.compose.material3.Tab(selected = selected == 0,
                    selectedContentColor = Color.Green,
                    unselectedContentColor = Color.Red,
                    onClick = {
                        setSelected(0)
                    }) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = "Login",
                        color = if (selected == 0) Color(0xff203657) else Color(0xff859DB5)
                    )

                }
                androidx.compose.material3.Tab(
                    selected = selected == 1,
                    onClick = { setSelected(1) }, selectedContentColor = Color.Green,
                    unselectedContentColor = Color.Red,
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        androidx.compose.animation.AnimatedVisibility(visible = selected == 1) {}
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = "Google",
                            color = if (selected == 1) Color(0xff203657) else Color(0xff859DB5)
                        )

                    }
                }
                androidx.compose.material3.Tab(selected = selected == 2,
                    selectedContentColor = Color.Green,
                    unselectedContentColor = Color.Red,
                    onClick = { setSelected(2) }) {
                    Box(contentAlignment = Alignment.Center) {
                        androidx.compose.animation.AnimatedVisibility(visible = selected == 2) {
//
                        }
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = "Easy Signature",
                            color = if (selected == 2) Color(0xff203657) else Color(0xff859DB5)
                        )
                    }
                }
            }


        }


    }
}

@Composable
fun SimpleTabRow() {
    val tabOptions = listOf("Accounts", "Cards", "Loans")
    var selectedTabIndex by remember { mutableStateOf(0) }

    TabRow(selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color(0xFF203657),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = 2.dp,
                color = Color(0xFF203657)
            )
        }) {
        tabOptions.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                selectedContentColor = Color(0x203657),
                unselectedContentColor = Color(0x203657)
            ) {
                Text(
                    text = title, modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
//SimpleTabRow()
    HomeScreen()
}