package com.app.home.main.recents

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.app.uikit.bottomSheet.CurrencyBottomSheet
import com.app.uikit.data.DataProvider
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.Utils
import com.app.uikit.views.FilterView
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeableActionsBox

const val recentTransactions = "RecentTransactions"
private val isSearchEnable = mutableStateOf(false)
private val filterBySearch = mutableStateOf("")

@Composable
fun RecentTransactions(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    val recentOps by viewModel.recentOps.collectAsState()
    val recentData = remember { mutableListOf<GetRecentOpsItem>() }
    val isLoading = remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    val cardFilters = remember { DataProvider.filtersRecentList }
    val closeSearch = remember { mutableStateOf(false) }

    val showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }
    val filterCurrencyList = remember { mutableListOf<String>() }
    val filterByCurrency = remember { mutableStateOf("") }
    val context = LocalContext.current



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
                .then(Modifier.wrapContentHeight()),
            color = Color(0xFF203657),
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = com.app.transfer.R.drawable.ic_back_arrow),
                            modifier = Modifier
                                .size(width = 32.dp, height = 25.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    (context as ComponentActivity).finish()
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

                    if (!isSearchEnable.value) {
                        androidx.compose.material3.Icon(
                            painter = painterResource(id = com.app.transfer.R.drawable.ic_tansfer_search),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    isSearchEnable.value = true
                                }
                        )
                    }

                }


                if (isSearchEnable.value) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                    ) {
                        OutlinedTextField(
                            value = filterBySearch.value,
                            onValueChange = {
                                filterBySearch.value = it

                                closeSearch.value = it.isEmpty()
                            },
                            label = {
                                androidx.compose.material3.Text(
                                    text = "Search",
                                    fontSize = 14.sp
                                )
                            },
                            trailingIcon = {
                                androidx.compose.material3.Icon(
                                    painter = if (!closeSearch.value) painterResource(id = com.app.transfer.R.drawable.ic_transfer_close) else painterResource(
                                        id = com.app.transfer.R.drawable.ic_tansfer_search
                                    ),
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .clickable {
                                            isSearchEnable.value = false

                                            //reset search filter
                                            filterBySearch.value = ""
                                        }
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White,
                                focusedBorderColor = colorResource(com.app.transfer.R.color.background_card_blue),
                                unfocusedBorderColor = colorResource(com.app.transfer.R.color.border_grey),
                                unfocusedLabelColor = colorResource(com.app.transfer.R.color.grey_text),
                                focusedLabelColor = colorResource(com.app.transfer.R.color.background_card_blue)
                            ),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(com.app.transfer.R.font.roboto_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF223142),
                            )
                        )
                    }

                }

            }
        }


        //main content
        Column(
            modifier = Modifier
                .weight(0.8f)
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
                        text = "${Utils.formatAmount(sumDR!!)}",
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
                        text = if (sumCR == 0.0) "${Utils.formatAmount(sumCR!!)}" else "- ${
                            Utils.formatAmount(
                                sumCR!!
                            )
                        }",
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
                cardFilters.forEachIndexed { index, cardFilters ->

                    item {
                        Row {
                            FilterView(filter = cardFilters) {
                                when (index) {
                                    0 -> {
                                        viewModel.getRecentOps(userDetails.customerNo, "")
                                    }

                                    1 -> {
                                        viewModel.getRecentOps(userDetails.customerNo, "CR")
                                    }

                                    2 -> {
                                        viewModel.getRecentOps(userDetails.customerNo, "DR")
                                    }

                                    3 -> {

                                    }

                                    4 -> {
                                        isSearchEnable.value = true
                                    }

                                    5 -> {
                                        showCurrencyBottomSheet.value = true
                                    }
                                }
                            }

                            Box(modifier = Modifier.padding(end = 5.sdp))
                        }
                    }
                }


            }


            LazyColumn {
                val groupedItems = recentData.groupBy { it.trn_date }



                if (groupedItems.isEmpty()) {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "No recent operation found!")
                        }
                    }
                } else {


                    groupedItems.forEach { (dateStr, itemList) ->

                        item {

                            val filter = itemList.filter {
                                it.receiver_name.contains(filterBySearch.value, true)
                            }.filter {
                                it.currency_name.contains(filterByCurrency.value, true)
                            }.toList()

                            if (filter.isNotEmpty()) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(4.sdp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = Utils.formatInputDate(dateStr))
                                }
                            }


                            filter.forEachIndexed { index, getRecentOpsItem ->
                                CardsItems(getRecentOpsItem, navController, index)
                            }

                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.size(height = 20.dp, width = 1.dp))
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


                val data = it.data as GetRecentOps

                if (recentData.isNotEmpty()) {
                    recentData.clear()
                }
                recentData.addAll(data)


                //currency

                if (filterCurrencyList.isNotEmpty()) {
                    filterCurrencyList.clear()
                }

                data.forEach {
                    filterCurrencyList.add(it.currency_name)
                }


            }
        }

    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading = isLoading)
    }


    CurrencyBottomSheet(showCurrencyBottomSheet, filterCurrencyList) {

        showCurrencyBottomSheet.value = false

        filterByCurrency.value = it

    }

}


@Composable
private fun CardsItems(
    data: GetRecentOpsItem,
    navController: NavController,
    index: Int
) {

    var isCredit by remember { mutableStateOf(false) }
    isCredit = data.debit_credit_flag != "DR"
    val symbol = Utils.formatCurrency(data.currency_name)

//    SwipeableActionsBox(endActions = listOf(mMessage)) {
        Card(
            modifier = Modifier
                .padding(vertical = 4.sdp, horizontal = 8.sdp)
                .fillMaxWidth()
        ) {
            // Card content...
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.sdp, vertical = 5.sdp),
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
                        if (isCredit) "-${data.amount} $symbol" else "${data.amount} $symbol",
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
//    }
}





