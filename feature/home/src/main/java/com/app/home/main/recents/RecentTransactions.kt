package com.app.home.main.recents

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.home.R
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetRecentOpsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.viewmodel.HomeViewModel
import com.app.uikit.data.DataProvider
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.models.CardFilters
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

const val recentTransactions = "RecentTransactions"

@Composable
fun RecentTransactions(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    val recentOps by viewModel.recentOps.collectAsState()
    val recentData = remember { mutableListOf<GetRecentOpsItem>() }
    val isLoading = remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    val cardFilters = remember { DataProvider.filtersRecentList }


    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getRecentOps(userDetails.customerNo, "")
        }
    }

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
                    text = "Recent operations on accounts",
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.sdp, bottom = 10.sdp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                val sumDR = recentData
                    .filter { it.debit_credit_flag == "DR" }
                    .sumByDouble { it.amount.toDoubleOrNull() ?: 0.0 }

                val sumCR = recentData
                    .filter { it.debit_credit_flag == "CR" }
                    .sumByDouble { it.amount.toDoubleOrNull() ?: 0.0 }

                Row {

                    Icon(
                        painterResource(id = R.drawable.ic_options_arrow_in),
                        modifier = Modifier.size(width = 18.sdp, height = 8.sdp),
                        tint = colorResource(R.color.background_card_blue),
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                    )

                    Text(
                        text = "${sumDR}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF203657),
                            textAlign = TextAlign.Right,
                        )
                    )

                }

                Spacer(
                    modifier = Modifier.size(width = 20.sdp, height = 1.sdp)
                )

                Row {

                    Icon(
                        painterResource(id = R.drawable.ic_options_arrow_out),
                        modifier = Modifier.size(width = 18.sdp, height = 8.sdp),
                        tint = Color(0xFFFF4E57),
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                    )


                    Text(
                        text = if (sumCR == 0.0) "$sumCR" else "- $sumCR",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF4E57),
                            textAlign = TextAlign.Right,
                        )
                    )

                }
            }



            LazyRow(
                contentPadding = PaddingValues(horizontal = 10.sdp)
            ) {
                items(items = cardFilters, itemContent = {
                    Row {
                        FilterView(filter = it)
                        Box(modifier = Modifier.padding(end = 5.sdp))
                    }
                })
            }


            LazyColumn {
                val groupedItems = recentData.groupBy { it.trn_date }

                groupedItems.forEach { (dateStr, itemList) ->

                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(4.sdp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = Utils.formatInputDate(dateStr))
                        }

                        itemList.forEach { ops ->
                            CardsItem(ops, navController)
                        }

                    }
                }

                item {
                    Spacer(modifier = Modifier.size(height = 80.dp, width = 1.dp))
                }

            }
        }
    }


    recentOps?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                try {
                    val data = it.data as GetRecentOps

                    if (!recentData.isNullOrEmpty()) {
                        recentData.clear()
                    }

                    recentData.addAll(data)

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading = isLoading)
    }

}

@Composable
private fun CardsItem(data: GetRecentOpsItem, navController: NavController) {

    var isCredit by remember { mutableStateOf(false) }

    isCredit = data.debit_credit_flag != "DR"

    Card(
        modifier = Modifier
            .padding(vertical = 4.sdp, horizontal = 8.sdp)
            .fillMaxWidth()
//            .clickable {
//                recentDetail.value = data
//                navController.navigate(recentToDetails)
//            }
        ,
        elevation = 1.dp,
        backgroundColor = Color(0xFFF3F7FA),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 5.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(0.7f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "${data.receiver_name}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF203657),
                        textAlign = TextAlign.Left,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {

                Text(
                    if (isCredit) "-${data.amount} ₼" else "${data.amount} ₼",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(600),
                        color = if (isCredit) Color(0xFFFF4E57) else Color(0xFF203657),
                        textAlign = TextAlign.Right,
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.sdp)
                        .fillMaxWidth()
                )


                Text(
                    "${data.trn_time}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.1.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(400),
                        color = colorResource(R.color.grey_text),
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.sdp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun FilterView(filter: CardFilters) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable {
            },
        elevation = 1.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))

    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(6.sdp)
        ) {

            Text(
                text = filter.filterName, style = TextStyle(fontSize = 12.sp)
            )

            if (filter.filterIcon != null) {
                Image(
                    painter = painterResource(id = filter.filterIcon!!),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(1.dp)
                        .width(14.dp)
                        .height(14.dp)
                )
            }
        }
    }


}