package com.app.transfer.transfers.transferdetails

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetTransactionDetails
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.R
import com.app.uikit.borders.dashedBorder
import com.app.uikit.borders.rightVerticalDashedBorder
import com.app.uikit.utils.SharedModel
import ir.kaaveh.sdpcompose.sdp



@Composable
fun Details(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val isLoading = remember { mutableStateOf(true) }
    val ibankRef = SharedModel.init().ibankRef.value
    val detailsData by viewModel.getTransactionDetails.collectAsState()
    val context: Context = LocalContext.current
    val sourceOfOrigin  = remember { mutableStateOf("") }
    val documentNo  = remember { mutableStateOf("") }
    val transferType  = remember { mutableStateOf("") }
    val sender  = remember { mutableStateOf("") }
    val fromAccount  = remember { mutableStateOf("") }
    val amount  = remember { mutableStateOf("") }
    val commAmount  = remember { mutableStateOf("") }
    val purpose  = remember { mutableStateOf("") }
    val note  = remember { mutableStateOf("") }
//    val pdfList  = remember { mutableListOf(Any) }




    LaunchedEffect(Unit) {
        viewModel.getTransactionDetails(ibankRef)
    }

    if (!isLoading.value) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),

            ) {

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
                                text = stringResource(R.string.source_of_origin), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),

                                    )
                            )

                            Text(
                                text = if ("${sourceOfOrigin.value}" == "WEB") "Internet Office" else "${sourceOfOrigin.value}",
                                style = TextStyle(

                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
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
                                text = stringResource(R.string.document_no), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${documentNo.value}", style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),

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
                                text = stringResource(R.string.transfer_type), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${transferType.value}", style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
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
                                text = stringResource(R.string.sender), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${sender.value}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
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
                                text = stringResource(id = R.string.from_the_account),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${fromAccount.value}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
                                )
                            )
                        }

                    }


                }
            }

            CardInfo2(navController = navController)

            CardInfo3(navController = navController)


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
                            .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier
                                .weight(0.7f)
                                .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                .fillMaxWidth()

                        ) {

                            Text(
                                text = stringResource(id = R.string.amount), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),

                                    )
                            )

                            Text(
                                text = "${amount.value}", style = TextStyle(

                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
                                )
                            )
                        }

                        Column(
                            Modifier
                                .weight(0.3f)
                                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                .fillMaxWidth()
                        ) {

                            Text(
                                text = stringResource(R.string.commission), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${commAmount.value}", style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),

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
                                text = stringResource(R.string.the_purpose_of_the_payment),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${purpose.value}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
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
                                text = stringResource(R.string.information_for_alan),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${note.value}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
                                )
                            )
                        }

                    }

//                if (!transactionDetails.PDF_LIST.isEmpty()) {
//
//                    Row(
//                        Modifier
//                            .fillMaxWidth()
//                            .dashedBorder(3.dp, colorResource(R.color.border_grey))
//                            .padding(horizontal = 10.sdp, vertical = 8.sdp),
//
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//
//                    ) {
//
//                        Column {
//
//                            Text(
//                                text = stringResource(R.string.attached_pdf), style = TextStyle(
//                                    fontSize = 12.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                    color = colorResource(R.color.grey_text),
//                                )
//                            )
//
//                            Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))
//
//
//                            LazyRow(
//                                contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp)
//
//                            ) {
//
//                                item {
//                                    PdfAttachmentItem(stringResource(R.string.salary_template_value_132_pdf))
//                                }
//
//                                item {
//                                    PdfAttachmentItem(stringResource(R.string.salary_template_value_133_pdf))
//                                }
//                            }
//
//
//                        }
//
//                    }
//                }


                }
            }

            PdfAttachmentItem(stringResource(R.string.pdf))

            CardInfo5(navController = navController)

            Spacer(modifier = Modifier.size(height = 100.dp, width = 1.dp))

        }
    } else {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }

    detailsData?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as GetTransactionDetails
                data?.apply {
                    sourceOfOrigin.value = source
                    documentNo.value = PMTCUSTID
                    transferType.value = trnType
                    sender.value = CUSTNAME
                    fromAccount.value = CUSTACC
                    amount.value = "$AMOUNT"
                    commAmount.value = "$COMMAMOUNT"
                    purpose.value = PMTDET
                    note.value = "$NOTE"
                }

            }
        }
    }


}


@Composable
private fun CardInfo1(navController: NavController, transactionDetails: GetTransactionDetails) {

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
                        text = stringResource(R.string.source_of_origin), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = if ("${transactionDetails.source}" == "WEB") "Internet Office" else "${transactionDetails.source}",
                        style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),
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
                        text = stringResource(R.string.document_no), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${transactionDetails.PMTCUSTID}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),

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
                        text = stringResource(R.string.transfer_type), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${transactionDetails.trnType}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),
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
                        text = stringResource(R.string.sender), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${transactionDetails.CUSTNAME}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),
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
                        text = stringResource(id = R.string.from_the_account), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${transactionDetails.CUSTACC}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),
                        )
                    )
                }

            }


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

        ) {


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(R.string.field),
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


            if (expanded) {

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
                            text = stringResource(R.string.name), style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.grey_text),
                            )
                        )

                        Text(
                            text = stringResource(R.string.farmaskop_m_hdud_m_sul_yy_tl_c_m_yy_t),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = Color(0xFF223142),
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
                            .weight(0.7f)
                            .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                            .padding(horizontal = 10.sdp, vertical = 8.sdp)
                            .fillMaxWidth()

                    ) {

                        Text(
                            text = stringResource(R.string.account_number), style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.grey_text),

                                )
                        )

                        Text(
                            text = stringResource(R.string.az78bres00380394400262924504),
                            style = TextStyle(

                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = Color(0xFF223142),
                            )
                        )
                    }

                    Column(
                        Modifier
                            .weight(0.3f)
                            .padding(horizontal = 10.sdp, vertical = 8.sdp)
                            .fillMaxWidth()
                    ) {

                        Text(
                            text = stringResource(R.string.tin), style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.grey_text),
                            )
                        )

                        Text(
                            text = stringResource(R.string._1700986181), style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = Color(0xFF223142),

                                )
                        )
                    }
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

        ) {


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                    .clickable {
                        expanded = !expanded
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(R.string.alan_s_bank),
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


            if (expanded) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.sdp, vertical = 8.sdp),

                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Column {

                        Text(
                            text = stringResource(R.string.code_and_name), style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.grey_text),
                            )
                        )

                        Text(
                            text = stringResource(R.string._805722_international_bank_of_azerbaijan_ojsc_central_branch),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = Color(0xFF223142),
                            )
                        )
                    }


                }


            }


        }
    }

}

//@Composable
//private fun CardInfo4(navController: NavController, transactionDetails: GetTransactionDetails) {
//
//    var expanded by remember { mutableStateOf(true) }
//
//    Card(
//        shape = RoundedCornerShape(10.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 5.sdp, horizontal = 10.sdp),
//        backgroundColor = Color.White
//    ) {
//        Column(
//
//        ) {
//
//
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column(
//                    Modifier
//                        .weight(0.7f)
//                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
//                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
//                        .fillMaxWidth()
//
//                ) {
//
//                    Text(
//                        text = stringResource(id = R.string.amount), style = TextStyle(
//                            fontSize = 12.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = colorResource(R.color.grey_text),
//
//                            )
//                    )
//
//                    Text(
//                        text = "${transactionDetails.AMOUNT}", style = TextStyle(
//
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = Color(0xFF223142),
//                        )
//                    )
//                }
//
//                Column(
//                    Modifier
//                        .weight(0.3f)
//                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
//                        .fillMaxWidth()
//                ) {
//
//                    Text(
//                        text = stringResource(R.string.commission), style = TextStyle(
//                            fontSize = 12.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = colorResource(R.color.grey_text),
//                        )
//                    )
//
//                    Text(
//                        text = "${transactionDetails.COMMAMOUNT}", style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = Color(0xFF223142),
//
//                            )
//                    )
//                }
//            }
//
//
//
//
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
//                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
//
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//
//            ) {
//
//                Column {
//
//                    Text(
//                        text = stringResource(R.string.the_purpose_of_the_payment),
//                        style = TextStyle(
//                            fontSize = 12.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = colorResource(R.color.grey_text),
//                        )
//                    )
//
//                    Text(
//                        text = "${transactionDetails.PMTDET}",
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = Color(0xFF223142),
//                        )
//                    )
//                }
//
//            }
//
//
//
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
//                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
//
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//
//            ) {
//
//                Column {
//
//                    Text(
//                        text = stringResource(R.string.information_for_alan), style = TextStyle(
//                            fontSize = 12.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = colorResource(R.color.grey_text),
//                        )
//                    )
//
//                    Text(
//                        text = "${transactionDetails.NOTE}",
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                            color = Color(0xFF223142),
//                        )
//                    )
//                }
//
//            }
//
//            if (!transactionDetails.PDF_LIST.isEmpty()) {
//
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .dashedBorder(3.dp, colorResource(R.color.border_grey))
//                        .padding(horizontal = 10.sdp, vertical = 8.sdp),
//
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//
//                ) {
//
//                    Column {
//
//                        Text(
//                            text = stringResource(R.string.attached_pdf), style = TextStyle(
//                                fontSize = 12.sp,
//                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                color = colorResource(R.color.grey_text),
//                            )
//                        )
//
//                        Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))
//
//
//                        LazyRow(
//                            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp)
//
//                        ) {
//
//                            item {
//                                PdfAttachmentItem(stringResource(R.string.salary_template_value_132_pdf))
//                            }
//
//                            item {
//                                PdfAttachmentItem(stringResource(R.string.salary_template_value_133_pdf))
//                            }
//                        }
//
//
//                    }
//
//                }
//            }
//
//
//        }
//    }
//
//}

@Composable
private fun PdfAttachmentItem(title: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 5.dp)

    ) {


        Box(
            Modifier
                .shadow(
                    elevation = 9.dp, spotColor = Color(0xE6000000), ambientColor = Color(
                        0xE6000000
                    )
                )
                .width(56.dp)
                .height(56.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp)),

            contentAlignment = Alignment.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_option_attach_pdf),
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
        }


        Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

        Text(
            text = title,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                color = Color(0xFFAEAFC9),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.width(100.dp)
        )

    }

}

@Composable
private fun CardInfo5(navController: NavController) {

    Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

    Button(
        onClick = { },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF3F7FA), // Change the background color here
            contentColor = Color(0xFF203657) // Change the text color here if needed
        ),
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFF203657), // Change the border color here
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            stringResource(R.string.back),
            modifier = Modifier.padding(vertical = 10.dp),
            style = TextStyle(
                fontSize = 17.sp, shadow = null
            )
        )
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetails() {
    Details(rememberNavController())
}