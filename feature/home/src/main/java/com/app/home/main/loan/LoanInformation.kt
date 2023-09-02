package com.app.home.main.loan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.drawBehind
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
import com.app.home.main.subviews.loanItem
import com.app.network.models.responseModels.GetLoansItem
import com.app.uikit.borders.dashedBorder
import com.app.uikit.borders.rightVerticalDashedBorder
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp

const val homeToLoanInformation = "homeToLoanInformation"

@Composable
fun LoanInformationDetails(navController: NavController) {

    val data = loanItem.value

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
                    text = stringResource(R.string.detailed_information_of_loan),
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
                    MainContent(navController, data!!)
                }
            }

        }

    }
}

@Composable
private fun MainContent(navController: NavController, data: GetLoansItem) {

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
                    text = if (data.nickName == null) "${data.CREDIT_NAME}" else "${data.nickName}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                )


                Image(
                    painter = painterResource(id = R.drawable.ic_options), contentDescription = "",
                    Modifier
                        .size(height = 28.dp, width = 28.dp)
                        .clickable {
                            loanInfoOptions.value = true
                        }
                )
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
                        text = stringResource(R.string.n_of_the_loan_agreement), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.CONTRACT}", style = TextStyle(

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
                        text = stringResource(id = R.string.currency), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
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
                        text = stringResource(id = R.string.status), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    var color: Color? = null
                    var status: String? = null

                    when (data.STATUS) {
                        "Gecikmədə deyil" -> {
                            color = Color(0xFF0FBF1B)
                            status = "No delay"
                        }

                        "Gecikmədədir" -> {
                            color = Color(R.color.red_2)
                            status = "On delay"
                        }

                        "Ödənilibdir" -> {
                            color = Color(0xFF2196F3)
                            status = "Closed"
                        }
                    }


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .width(10.dp)
                                .height(10.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = color!!, radius = 5.dp.toPx()
                                    )
                                }
                        ) {

                        }

                        Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

                        Text(
                            text = "$status", style = TextStyle(
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
                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .fillMaxWidth()
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)

                ) {

                    Text(
                        text = stringResource(R.string.next_payment_date), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = if (data.NEXT_PAYMENT_DATE != null) "${data.NEXT_PAYMENT_DATE}".replace("/", ".") else "",
                        style = TextStyle(

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
                        text = stringResource(R.string.next_payment_fee), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.NEXT_PAYMENT_AMOUNT), style = TextStyle(
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
                        text = stringResource(R.string.paid_total_months), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.PAID_TOTAL_MONTH}", style = TextStyle(

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
                        text = stringResource(R.string.remaining_principal_amount),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.MAIN_BALANCE), style = TextStyle(
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
                        text = stringResource(id = R.string.branch), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.BRANCH_NAME}", style = TextStyle(
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
                        text = stringResource(R.string.loan_amount), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.CREDIT_AMOUNT), style = TextStyle(
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
                        text = stringResource(R.string.interest_rate), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.INT_RATE} %", style = TextStyle(
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
                        text = stringResource(R.string.credit_issuance_date), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = if (data.START_DATE != null) "${data.START_DATE}".replace(
                            "/",
                            "."
                        ) else "", style = TextStyle(

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
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = if (data.END_DATE != null) "${data.END_DATE}".replace("/", ".") else "", style = TextStyle(
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
                        text = stringResource(R.string.payment_account), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.PAYMENT_ACCOUNT}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }


//                Image(
//                    painter = painterResource(id = R.drawable.ic_share), contentDescription = "",
//                    Modifier.size(height = 20.dp, width = 20.dp)
//                )

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
                        text = stringResource(R.string.balance_in_payment_account),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.PAYMENT_ACC_BALANCE), style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(R.string.total_interest_amount), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.CUR_INT_AMOUNT), style = TextStyle(
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
                        text = "Amount of interest paid", style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.CUR_INT_AMOUNT), style = TextStyle(
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
                        text = "Current interest amount", style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.CUR_INT_AMOUNT), style = TextStyle(
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
                        text = "Number of days left on next payment date", style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.DELAY_DAY_COUNT}", style = TextStyle(

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
                        text = "Total overdue amount", style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "0", style = TextStyle(
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
                        text = "Number of days delayed", style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = "827", style = TextStyle(

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
                        text = "Calculated fines", style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "0.00", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }


        }
    }

    com.app.uikit.bottomSheet.LoanInfoOptionSheet(loanInfoOptions)

}

@Preview
@Composable
fun PreviewLoadInformation() {
    LoanInformationDetails(navController = rememberNavController())
}