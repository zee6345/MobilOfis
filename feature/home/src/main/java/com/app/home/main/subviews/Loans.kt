package com.app.home.main.subviews

import android.content.Context
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.home.main.loan.navigation.homeToLoanInformation
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetLoans
import com.app.network.models.responseModels.GetLoansItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.viewmodel.HomeViewModel
import com.app.uikit.data.DataProvider
import com.app.uikit.models.CardFilters
import ir.kaaveh.sdpcompose.sdp


val loansList = mutableListOf<GetLoansItem>()

@Composable
fun LoansList(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val customerLoans by viewModel.customerLoans.collectAsState()
    val context: Context = LocalContext.current
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)
    val cardFilters = remember { DataProvider.filtersLoanList }
    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(false) }
    val selectedFilter = remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        viewModel.getLoans(
            userDetails.customerNo
        )
    }

    if (isLoading.value) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    } else {

        LazyColumn(
            modifier = Modifier.padding(horizontal = 2.sdp, vertical = 5.sdp)
        ) {


            item {
                Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

                Filters() {
                    selectedFilter.value = it
                }
            }

            item {
                if(!isEmpty.value) {
                    Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

                    LazyRow(
                        contentPadding = PaddingValues(vertical = 1.dp)
                    ) {
                        items(items = cardFilters, itemContent = {
                            Row {
                                FilterView(filter = it)
                                Box(modifier = Modifier.padding(end = 5.sdp))
                            }

                        })
                    }
                }
            }

            val filter = loansList.filter {
                it.STATUS.contains(selectedFilter.value, true)
            }

            if (!filter.isNullOrEmpty()){
                items(items = filter, itemContent = {
                    LoansListItem(obj = it, navController)
                })
                isEmpty.value = false
            } else {
                item {
                    Box(
                        Modifier.fillMaxSize()
                            .padding(20.sdp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No loan found!")
                    }
                }
                isEmpty.value = true
            }



        }
    }

    customerLoans?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                val data = it.data as GetLoans

                isLoading.value = false

                loansList.apply {
                    clear()
                    loansList.addAll(data)
                }

            }
        }

    }


}


@Composable
private fun LoansListItem(obj: GetLoansItem, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 2.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(homeToLoanInformation)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            ) {

                Row(

                ) {
                    var color = Color(0xFF0FBF1B)
                    when (obj.STATUS) {
                        //no delay
                        "Gecikmədə deyil" -> {
                            color = Color(0xFF0FBF1B)
                        }

                        //on delay
                        "Gecikmədədir" -> {
                            color = Color(R.color.red_2)
                        }

                        //closed
                        "Ödənilibdir" -> {
                            color = Color(0xFF2196F3)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .width(10.dp)
                            .height(10.dp)
                            .drawBehind {
                                drawCircle(
                                    color = color, radius = 5.dp.toPx()
                                )
                            }
                            .align(Alignment.CenterVertically)
                    ) {

                    }


                    Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

                    Text(
                        text = if (obj.nickName != null) obj.nickName.toString() else "SME Loan",
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Text(
                    obj.CONTRACT,
                    style = TextStyle(fontSize = 14.sp, color = Color(R.color.grey_text)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)

                )
            }

            Text(
                text = "${obj.MAIN_BALANCE}",
                style = TextStyle(
                    fontSize = 14.sp, color = Color(R.color.background_card_blue),
                    textAlign = TextAlign.End
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)

            )


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

            Image(
                painter = painterResource(id = filter.filterIcon),
                contentDescription = "",
                modifier = Modifier
                    .padding(1.dp)
                    .width(14.dp)
                    .height(14.dp)
            )
        }
    }


}


@Composable
private fun Filters(onFilter: (String) -> Unit) {

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
                onFilter("Gecikmədə deyil")
            }
        ) {
            Text(
                stringResource(R.string.current_loans), style = TextStyle(
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
                onFilter("Ödənilibdir")
            }
        ) {
            Text(
                stringResource(R.string.closed_loans), style = TextStyle(
                    fontSize = 12.sp,
                    color = if (selectedBoxIndex.value == 1) Color.White else colorResource(R.color.background_card_blue)
                )
            )
        }

    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun LoansPreview() {
    LoansList(rememberNavController())
}