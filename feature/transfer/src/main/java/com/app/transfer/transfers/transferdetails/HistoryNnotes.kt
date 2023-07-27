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
import androidx.compose.ui.res.stringResource
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
    var expanded1 by remember { mutableStateOf(true) }
    var expanded2 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

        ) {


//        CardInfo1(navController = navController)


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
                        expanded1 = !expanded1
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                androidx.compose.material.Text(
                    text = stringResource(R.string.transfer_history),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),

                        )
                )

                if (expanded1)
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

        if(expanded1)
            CardInfo2(navController = navController)

        if (expanded1)
            CardInfo3(navController = navController)

//        CardInfo4(navController = navController)


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
                        expanded2 = !expanded2
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                androidx.compose.material.Text(
                    text = stringResource(R.string.notes),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),

                        )
                )

                if (expanded2)
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


        if (expanded2)
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
                text = stringResource(id = R.string.transfer_history),
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
                    text = stringResource(R.string.eyyubov_son_of_samir_kurdoglu),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),

                        )
                )


                androidx.compose.material.Text(
                    text = stringResource(R.string.sameer),
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

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    androidx.compose.material.Text(
                        text = stringResource(R.string.sent_to_the_bank),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),

                            )
                    )

                }


                Row {


                    androidx.compose.material.Text(
                        text = stringResource(R.string._01_11_2021),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    androidx.compose.material.Text(
                        text = stringResource(R.string._14_34),
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
                    text = stringResource(R.string.hajiyev_yusif_adil_oglu),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),

                        )
                )


                androidx.compose.material.Text(
                    text = stringResource(R.string.yusif),
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

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    androidx.compose.material.Text(
                        text = stringResource(R.string.signature_and_approval),
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
                        text = stringResource(id = R.string._14_34),
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
                text = stringResource(id = R.string.notes),
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
                    text = stringResource(R.string.not_sent),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),

                        )
                )


                androidx.compose.material.Text(
                    text = stringResource(R.string.samirmss),
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
                        text = stringResource(R.string.please_resend),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),

                            )
                    )

                }


                Row {


                    androidx.compose.material.Text(
                        text = stringResource(id = R.string._01_11_2021),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                            textAlign = TextAlign.Right,

                            )
                    )

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    androidx.compose.material.Text(
                        text = stringResource(id = R.string._14_34),
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

