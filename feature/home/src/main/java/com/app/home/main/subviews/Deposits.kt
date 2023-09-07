package com.app.home.main.subviews


import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.home.main.enableSearch
import com.app.home.main.isShowBalance
import com.app.home.main.model.Search
import com.app.home.main.model.SearchBy
import com.app.home.main.searchAccountNo
import com.app.home.main.searchBy
import com.app.home.main.searchDepositName
import com.app.home.main.searchFrom
import com.app.home.main.trust.homeToTrustDepositDetails
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetTrusts
import com.app.network.models.responseModels.GetTrustsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.viewmodel.HomeViewModel
import com.app.uikit.bottomSheet.CurrencyBottomSheet
import com.app.uikit.data.DataProvider
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.Utils
import com.app.uikit.views.AutoResizedText
import com.app.uikit.views.FilterView
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

val trustsList = mutableListOf<GetTrustsItem>()

@Composable
fun TrustsList(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val customerTrusts by viewModel.customerTrusts.collectAsState()
    val context: Context = LocalContext.current
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    val isLoading = remember { mutableStateOf(false) }
    val cardFilters = remember { DataProvider.filtersTrustsList }
    val onFilter = remember { mutableStateOf("1") }
    val isEmpty = remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    val selectedFilterIndex = remember { mutableStateOf(0) }

    val showCurrencyBottomSheet = rememberSaveable { mutableStateOf(false) }
    val filterCurrencyList = remember { mutableListOf<String>() }
    val filterByCurrency = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getTrusts(
                userDetails.customerNo
            )
        }
    }
//
//    if (isLoading.value) {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//        }
//    } else {

    LazyColumn(
        modifier = Modifier.padding(horizontal = 10.sdp, vertical = 5.sdp)
    ) {

        item {

            Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

            Filters(
                onFilter = {
                    onFilter.value = it
                },
                onSelectedFilter = {
                    selectedFilterIndex.value = it
                }
            )
        }

        item {
            if (!isEmpty.value) {
                Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

                LazyRow(
                    contentPadding = PaddingValues(vertical = 1.dp)
                ) {

                    cardFilters.forEachIndexed { index, cardFilters ->

                        item {
                            Row {
                                FilterView(filter = cardFilters) {

                                    when (index) {
                                        0 -> {
                                            enableSearch.value = true
                                            searchFrom.value = Search.FromDeposits
                                            searchBy.value = SearchBy.ByDepositName
                                        }

                                        1 -> {
                                            enableSearch.value = true
                                            searchFrom.value = Search.FromDeposits
                                            searchBy.value = SearchBy.ByAccount
                                        }

                                        2 -> {
                                            showCurrencyBottomSheet.value = true
                                        }

                                    }

                                }
                                Box(modifier = Modifier.padding(end = 5.sdp))
                            }
                        }

                    }


//                    items(items = cardFilters, itemContent = {
//                        Row {
//                            FilterView(filter = it)
//                            Box(modifier = Modifier.padding(end = 5.sdp))
//                        }
//
//                    })
                }
            }
        }


        val filter = trustsList.filter {
            it.STATUS.contains(onFilter.value, true)
        }

        if (filter.isNotEmpty()) {

            val filterList = trustsList.filter {
                when (searchBy.value) {
                    SearchBy.ByDepositName -> {
                        it.depositId.contains(searchDepositName.value, true)
                    }

                    SearchBy.ByAccount -> {
                        it.ACCOUNT_NO.contains(searchAccountNo.value, true)
                    }

                    else -> {
                        true
                    }
                }
            }.filter {
                it.CCY_NAME.contains(filterByCurrency.value, true)
            }.toList()


            filterList.forEachIndexed { index, getTrustsItem ->
                item {
                    TrustsListItem(obj = getTrustsItem, navController)
                }
            }
//            items(items = trustsList, itemContent = {
//
//            })

            item {
                Spacer(modifier = Modifier.size(width = 1.dp, height = 50.sdp))
            }

            isEmpty.value = false
        } else {
            item {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(20.sdp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(if (selectedFilterIndex.value == 0) "No current deposit found!" else "No closed deposit found!")
                }
            }

            isEmpty.value = true
        }


    }

//    }


    customerTrusts?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

//                ErrorState(context = context, it.errorMessage).handleError()
            }

            is DataState.Success -> {
                val data = it.data as GetTrusts

                isLoading.value = false

                trustsList.apply {
                    clear()
                    trustsList.addAll(data)
                }

                //currency filter
                if (filterCurrencyList.isNotEmpty()) {
                    filterCurrencyList.clear()
                }

                trustsList.forEach { trust ->
                    filterCurrencyList.add(trust.CCY_NAME)
                }

            }
        }

    }

    CurrencyBottomSheet(showCurrencyBottomSheet, filterCurrencyList) {

        showCurrencyBottomSheet.value = false

        filterByCurrency.value = it

    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }


}


@Composable
private fun TrustsListItem(obj: GetTrustsItem, navController: NavController) {

    val symbol = Utils.formatCurrency(obj.CCY_NAME)

    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(homeToTrustDepositDetails)
            },
        elevation = 1.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 5.sdp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column() {

                Text(
                    text = if (obj.nickName == null) obj.PRODUCT_NAME else obj.nickName,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(vertical = 5.dp)
                )

                Text(
                    obj.IBAN,
                    style = TextStyle(fontSize = 12.sp, color = Color(R.color.grey_text))
                )
            }

            Column(

            ) {

                AutoResizedText(
                    text = if (isShowBalance.value) "****" else "${Utils.formatAmountWithSpaces(obj.BALANCE.toDouble())} $symbol",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF223142),
                        textAlign = TextAlign.Right,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                )

                Text(
                    text = "${obj.INTEREST_RATIO} %",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(R.color.grey_text),
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier
                        .fillMaxWidth()

                )


            }


        }
    }
}


@Composable
private fun Filters(onFilter: (String) -> Unit, onSelectedFilter: (Int) -> Unit) {

    val selectedBoxIndex = remember { mutableStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Box(modifier = Modifier
            .background(
                if (selectedBoxIndex.value == 0) colorResource(R.color.background_card_blue) else colorResource(
                    R.color.border_grey
                ),
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(vertical = 5.sdp, horizontal = 10.sdp)
            .clickable {
                selectedBoxIndex.value = 0
                onFilter("1")
                onSelectedFilter(selectedBoxIndex.value)
            }) {
            Text(
                stringResource(R.string.current_deposits), style = TextStyle(
                    fontSize = 12.sp,
                    color = if (selectedBoxIndex.value == 0) Color.White else colorResource(R.color.background_card_blue)
                )
            )
        }


        Box(modifier = Modifier
            .background(
                if (selectedBoxIndex.value == 1) colorResource(R.color.background_card_blue) else colorResource(
                    R.color.border_grey
                ),
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(vertical = 5.sdp, horizontal = 10.sdp)
            .clickable {
                selectedBoxIndex.value = 1
                onFilter("3")
                onSelectedFilter(selectedBoxIndex.value)
            }) {
            Text(
                stringResource(R.string.closed_deposits), style = TextStyle(
                    fontSize = 12.sp,
                    color = if (selectedBoxIndex.value == 1) Color.White else colorResource(R.color.background_card_blue)
                )
            )
        }

    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun TrustsPreview() {
    TrustsList(rememberNavController())
}