package com.app.home.main.recents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.home.R
import com.app.home.main.recentDetail
import com.app.uikit.borders.dashedBorder
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp

const val recentToDetails = "recentToDetails"

@Composable
fun RecentDetailed(navController: NavController) {

    val recent = recentDetail.value
    var isCredit by remember { mutableStateOf(false) }

    isCredit = recent!!.debit_credit_flag != "DR"

    val symbol = Utils.formatCurrency(recent.currency_name)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState(), enabled = true
            )
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
                    .padding(15.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(height = 25.dp, width = 32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.popBackStack()
                        },
                    contentDescription = ""
                )
                Text(
                    text = "Detailed",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }


        //main content
        LazyColumn(
            modifier = Modifier
                .weight(0.9f)
        ) {

            item {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.sdp, horizontal = 10.sdp),
                    backgroundColor = Color.White
                ) {
                    Column {

                        Column(
                            modifier = Modifier.dashedBorder(
                                2.sdp, colorResource(id = R.color.border_grey)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 5.sdp, horizontal = 10.sdp)
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(10.sdp)
                                ) {
                                    Text(
                                        "History",
                                        style = TextStyle(
                                            color = Color(0xFF747A83),
                                            fontSize = 12.sp
                                        )
                                    )
                                }


                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.sdp)
                                ) {
                                    Text("${recent!!.trn_date} ${recent!!.trn_time}")
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.dashedBorder(
                                2.sdp, colorResource(id = R.color.border_grey)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 5.sdp, horizontal = 10.sdp)
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(10.sdp)
                                ) {
                                    Text(
                                        "Sender/Recipient",
                                        style = TextStyle(
                                            color = Color(0xFF747A83),
                                            fontSize = 12.sp
                                        )
                                    )
                                }


                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.sdp)
                                ) {
                                    Text("${recent!!.receiver_name}")
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.dashedBorder(
                                2.sdp, colorResource(id = R.color.border_grey)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 5.sdp, horizontal = 10.sdp)
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(10.sdp)
                                ) {
                                    Text(
                                        "Description of operation",
                                        style = TextStyle(
                                            color = Color(0xFF747A83),
                                            fontSize = 12.sp
                                        )
                                    )
                                }


                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.sdp)
                                ) {
                                    Text("${recent!!.descrption}")
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.dashedBorder(
                                2.sdp, colorResource(id = R.color.border_grey)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 10.sdp, horizontal = 10.sdp)
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(10.sdp)
                                ) {
                                    Text(
                                        "Account",
                                        style = TextStyle(
                                            color = Color(0xFF747A83),
                                            fontSize = 12.sp
                                        )
                                    )
                                }


                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.sdp)
                                ) {
                                    Text("${recent!!.account_iban}")
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.dashedBorder(
                                2.sdp, colorResource(id = R.color.border_grey)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 5.sdp, horizontal = 10.sdp)
                            ) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(10.sdp)
                                ) {
                                    Text(
                                        "Amount",
                                        style = TextStyle(
                                            color = Color(0xFF747A83),
                                            fontSize = 12.sp
                                        )
                                    )
                                }


                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.sdp)
                                ) {
                                    Text(
                                        text = if (isCredit) "-${recent.amount} $symbol" else "${recent.amount} $symbol",
                                        style = TextStyle(
                                            color = if (isCredit) Color(0xFFFF4E57) else Color(
                                                0xFF203657
                                            )
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}