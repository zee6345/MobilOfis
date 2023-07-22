package com.app.transfer.transfers.transferdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.transfer.R
import com.app.transfer.components.dashedBorder
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HistoryNnotes(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        ) {


        CardInfo1(navController = navController)

        CardInfo2(navController = navController)

        CardInfo3(navController = navController)

        CardInfo4(navController = navController)

        CardInfo5(navController = navController)



    }


}


@Composable
private fun CardInfo1(navController: NavController) {

    var expanded by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .dashedBorder(3.dp, Color(0xFFE7EEFC))
                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                .clickable {
                    expanded = !expanded
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            androidx.compose.material.Text(
                text = "Transfer history",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),

                    )
            )

            if (expanded)
                Image(
                    painterResource(id = R.drawable.ic_option_expand),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                ) else Image(
                painterResource(id = R.drawable.ic_option_collapse),
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
        }

    }

}

@Composable
private fun CardInfo2(navController: NavController) {

    var expanded by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                androidx.compose.material.Text(
                    text = "Eyyubov, son of Samir Kurdoglu",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),

                        )
                )


                androidx.compose.material.Text(
                    text = "Sameer",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF859DB5),
                        textAlign = TextAlign.Right,

                        )
                )


            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {


                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .width(10.dp)
                            .height(10.dp)
                            .drawBehind {
                                drawCircle(
                                    color = Color(0xFFD7C20A), radius = 5.dp.toPx()
                                )
                            }
                            .align(Alignment.CenterVertically)
                    ) {

                    }


                    androidx.compose.material.Text(
                        text = "Sent to the bank",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),

                            )
                    )

                }


                Row {


                    androidx.compose.material.Text(
                        text = "01.11.2021",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    androidx.compose.material.Text(
                        text = "14:34",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                }


            }

        }

    }

}

@Composable
private fun CardInfo3(navController: NavController) {

    var expanded by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                androidx.compose.material.Text(
                    text = "Hajiyev Yusif Adil oglu",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),

                        )
                )


                androidx.compose.material.Text(
                    text = "Yusif",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF859DB5),
                        textAlign = TextAlign.Right,

                        )
                )


            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {


                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .width(10.dp)
                            .height(10.dp)
                            .drawBehind {
                                drawCircle(
                                    color = Color(0xFF268ED9), radius = 5.dp.toPx()
                                )
                            }
                            .align(Alignment.CenterVertically)
                    ) {

                    }


                    androidx.compose.material.Text(
                        text = "Signature and approval",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),

                            )
                    )

                }


                Row {


                    androidx.compose.material.Text(
                        text = "01.11.2021",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    androidx.compose.material.Text(
                        text = "14:34",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                }


            }

        }

    }

}

@Composable
private fun CardInfo4(navController: NavController) {

    var expanded by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .dashedBorder(3.dp, Color(0xFFE7EEFC))
                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                .clickable {
                    expanded = !expanded
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            androidx.compose.material.Text(
                text = "Notes",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),

                    )
            )

            if (expanded)
                Image(
                    painterResource(id = R.drawable.ic_option_expand),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                ) else Image(
                painterResource(id = R.drawable.ic_option_collapse),
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
        }

    }

}

@Composable
private fun CardInfo5(navController: NavController) {

    var expanded by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                androidx.compose.material.Text(
                    text = "Not sent.",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),

                        )
                )


                androidx.compose.material.Text(
                    text = "samirmss",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF859DB5),
                        textAlign = TextAlign.Right,

                        )
                )


            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {


//                    Box(
//                        modifier = Modifier
//                            .padding(2.dp)
//                            .width(10.dp)
//                            .height(10.dp)
//                            .drawBehind {
//                                drawCircle(
//                                    color = Color(0xFFD7C20A), radius = 5.dp.toPx()
//                                )
//                            }
//                            .align(Alignment.CenterVertically)
//                    ) {
//
//                    }


                    androidx.compose.material.Text(
                        text = "Please resend",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),

                            )
                    )

                }


                Row {


                    androidx.compose.material.Text(
                        text = "01.11.2021",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    androidx.compose.material.Text(
                        text = "14:34",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                }


            }

        }

    }

}

