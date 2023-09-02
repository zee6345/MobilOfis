package com.app.home.main.trust

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.app.home.R

import com.app.home.main.subviews.trustsList
import com.app.network.models.responseModels.GetTrustsItem
import com.app.uikit.borders.dashedBorder
import com.app.uikit.borders.rightVerticalDashedBorder
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp

const val homeToTrustDepositDetails = "homeToTrustDepositDetails"

@Composable
fun DepositDetails(navController: NavController) {

    val data = trustsList[0]

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
                    text = stringResource(R.string.detailed_information_on_the_deposit),
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )

            }
        }


        //main content
        Column(
            modifier = Modifier
                .weight(0.9f)

        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 50.sdp),

                ) {
                item {
                    MainContent(navController, data)
                }

            }

        }

    }
}

@Composable
private fun MainContent(navController: NavController, data: GetTrustsItem) {

    val loanInfoOptions = rememberSaveable { mutableStateOf(false) }


    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {
        Column(

        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    text = if (data.nickName == null) "${data.PRODUCT_NAME}" else "${data.nickName}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                )


//                Image(
//                    painter = painterResource(id = R.drawable.ic_options), contentDescription = "",
//                    Modifier
//                        .size(height = 28.dp, width = 28.dp)
//                        .clickable {
//                            loanInfoOptions.value = true
//                        }
//                )
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(R.string.account_number), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.IBAN}", style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }


            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(id = R.string.branch), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),

                            )
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Text(
                            text = "${data.BRANCH_NAME}", style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.background_card_blue),

                                )
                        )

                    }


                }


            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(id = R.string.interest_rate), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.INTEREST_RATIO} %", style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)

                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(R.string.duration_months), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.periodMonth}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }

                Column(
                    Modifier
                        .weight(0.2f)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.currency), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.CCY_NAME}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(R.string.start_date), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.OPENDATE}", style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.end_date), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.MATURITY_DATE}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(R.string.remainder), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.BALANCE.toDouble()), style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)

                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.free_balance), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                        )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.INITIALAMOUNT.toDouble()), style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(R.string.initial_amount), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.INITIALAMOUNT.toDouble()), style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(R.string.amount_of_income_tax), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                        )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.taxAmount.toDouble()), style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }









            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = stringResource(R.string.interest_payment_scheme), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.PAYMENTPERIOD}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }


            }




            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = stringResource(R.string.account_to_which_interest_is_transferred),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(com.app.adjustment.R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.INTEREST_ACC}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }


            }




            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(R.string.calculated_interest_amount),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.totalIntCalcAmount}", style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(R.string.amount_of_interest_paid), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.totalIntPaidAmount}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }

        }
    }


}

@Preview
@Composable
fun PreviewDepositDetails() {
    DepositDetails(navController = rememberNavController())
}