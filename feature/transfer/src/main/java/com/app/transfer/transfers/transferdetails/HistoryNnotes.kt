package com.app.transfer.transfers.transferdetails


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetTransactionDetails
import com.app.network.models.responseModels.HISTORYDETAILS
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.R
import com.app.uikit.borders.dashedBorder
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.SharedModel
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@Composable
fun HistoryNnotes(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    var expanded1 by remember { mutableStateOf(true) }
    var expanded2 by remember { mutableStateOf(true) }
    val isLoading = remember { mutableStateOf(false) }
    val history = remember { mutableListOf<HISTORYDETAILS>() }
    val details = remember { mutableStateOf<GetTransactionDetails?>(null) }
    val isSigned = remember { mutableStateOf(false) }

    val context: Context = LocalContext.current
    val detailsData by viewModel.getTransactionDetails.collectAsState()

    //fetch item data
    val data = SharedModel.init().signatureData.value
    isSigned.value = data!!.isSignRequired
    val ibankRef = data.transfer.ibankRef

    val coroutine = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getTransactionDetails(ibankRef)
        }
    }

    LazyColumn {

        item {
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
                        .padding(horizontal = 10.sdp, vertical = 8.sdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = stringResource(R.string.transfer_history),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),

                            )
                    )


                    Image(
                        if (expanded1) painterResource(id = R.drawable.ic_option_expand) else painterResource(
                            id = R.drawable.ic_option_collapse
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(26.dp)
                            .padding(3.dp)
                            .clickable {
                                expanded1 = !expanded1
                            }
                    )
                }

            }


        }

        if (expanded1)
            history.forEachIndexed { index, historydetails ->
                item {
                    CardInfo2(navController = navController, historydetails)
                }
            }




        if ( details.value != null && details.value!!.NOTE != null) {

            item {
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
                            .padding(horizontal = 10.sdp, vertical = 8.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(R.string.notes),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),

                                )
                        )


                        Image(
                            if (expanded2) painterResource(id = R.drawable.ic_option_expand) else painterResource(
                                id = R.drawable.ic_option_collapse
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(26.dp)
                                .padding(3.dp)
                                .clickable {
                                    expanded2 = !expanded2
                                }
                        )
                    }

                }
            }

            if (expanded2)
                history.forEachIndexed { index, historydetails ->
                    item {
                        CardInfo5(navController = navController, historydetails)
                    }
                }
        }

        item {
            Spacer(modifier = Modifier.size(height = 50.sdp, width = 1.sdp))
        }


        item {
            if (isSigned.value) {
                CardInfo6(navController = navController)
            }
        }


    }


    detailsData?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as GetTransactionDetails
                data?.apply {

                    details.value = this

                    history.apply {
                        clear()
                        addAll(HISTORY_DETAILS)
                    }

                }

            }
        }
    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }

}

@Composable
private fun CardInfo2(navController: NavController, historydetails: HISTORYDETAILS) {

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
                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "${historydetails.userLongName}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),

                        )
                )


                Text(
                    text = "${historydetails.userName}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = colorResource(R.color.grey_text),
                        textAlign = TextAlign.Right,

                        )
                )


            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
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
                                    color = Utils.headerStatus(
                                        historydetails.trnStatusAfter
                                    ).color, radius = 5.dp.toPx()
                                )
                            }
                            .align(Alignment.CenterVertically)
                    ) {

                    }

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    Text(
                        text = Utils.headerStatus(historydetails.trnStatusAfter).status,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),

                            )
                    )

                }


                Row {


                    Text(
                        text = "${historydetails.transactionDate}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.grey_text),
                            textAlign = TextAlign.Right,

                            )
                    )

                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                    Text(
                        text = "${historydetails.transactionTime}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.grey_text),
                            textAlign = TextAlign.Right,

                            )
                    )

                }


            }

        }

    }

}

@Composable
private fun CardInfo5(navController: NavController, historydetails: HISTORYDETAILS) {
    if (historydetails.errorText != null) {

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
                        .dashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .clickable {
                            expanded = !expanded
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = stringResource(R.string.not_sent),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),

                            )
                    )

                    Text(
                        text = stringResource(R.string.samirmss),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = colorResource(R.color.grey_text),
                            textAlign = TextAlign.Right,

                            )
                    )


                }

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

                    Row {

                        Text(
                            text = stringResource(R.string.please_resend),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),

                                )
                        )

                    }


                    Row {


                        Text(
                            text = stringResource(id = R.string._01_11_2021),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = colorResource(R.color.grey_text),
                                textAlign = TextAlign.Right,

                                )
                        )

                        Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))


                        Text(
                            text = stringResource(id = R.string._14_34),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = colorResource(R.color.grey_text),
                                textAlign = TextAlign.Right,

                                )
                        )

                    }


                }

            }

        }
    }

}

@Composable
private fun CardInfo6(navController: NavController) {

    Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF3F7FA),
                contentColor = Color(0xFF203657)
            ),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth()
                .weight(1f)
                .border(
                    width = 2.dp,
                    color = Color(0xFF203657),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                stringResource(R.string.back),
                modifier = Modifier.padding(vertical = 10.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    shadow = null
                )
            )
        }

        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF203657),
                contentColor = Color(0xFF203657)
            ),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth()
                .weight(1f)
                .border(
                    width = 2.dp,
                    color = Color(0xFF203657),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                "Sign",
                modifier = Modifier.padding(vertical = 10.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    shadow = null,
                    color = Color.White
                )
            )
        }
    }


}

