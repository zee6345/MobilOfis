package com.app.home.main.component

import android.content.Context
import android.text.method.TextKeyListener.clear
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.home.data.CardFilters
import com.app.home.data.DataProvider
import com.app.home.data.TrustsData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.home.main.trust.navigation.homeToTrustDepositDetails
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetLoans
import com.app.network.data.responseModels.GetLoansItem
import com.app.network.data.responseModels.GetTrusts
import com.app.network.data.responseModels.GetTrustsItem
import com.app.network.data.responseModels.LoginVerifyResponse
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TrustsList(navController: NavController, viewModel: HomeViewModel = viewModel()) {

    val customerTrusts by viewModel.customerTrusts.collectAsState()
    val context: Context = LocalContext.current
    val userDetails = fetchUserDetails()

    val isLoading = remember { mutableStateOf(false) }
    val cardsList = remember { mutableListOf<GetTrustsItem>() }
    val cardFilters = remember { DataProvider.filtersTrustsList }

    LaunchedEffect(Unit) {
        viewModel.getTrusts(
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

        Column(
            modifier = Modifier.padding(horizontal = 3.sdp, vertical = 5.sdp)
        ) {

            Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

            Filters()

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



            LazyColumn(
                contentPadding = PaddingValues(vertical = 5.dp)
            ) {
                items(items = cardsList, itemContent = {
                    TrustsListItem(obj = it, navController)
                })
            }

        }

    }


    customerTrusts?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                val data = it.data as GetTrusts

                isLoading.value = false

                cardsList.apply {
                    clear()
                    cardsList.addAll(data)
                }

            }
        }

    }


}


@Composable
private fun TrustsListItem(obj: GetTrustsItem, navController: NavController) {
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
                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF859DB5))
                )
            }

            Column(

            ) {

                Text(
                    text = obj.BALANCE,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF223142),
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                )

                Text(
                    text = "${obj.INTEREST_RATIO} %",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF859DB5),
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
private fun Filters() {

    val selectedBoxIndex = remember { mutableStateOf(0) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Box(modifier = Modifier
            .background(
                if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(vertical = 5.sdp, horizontal = 10.sdp)
            .clickable { selectedBoxIndex.value = 0 }) {
            Text(
                stringResource(R.string.current_deposits), style = TextStyle(
                    fontSize = 12.sp,
                    color = if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142)
                )
            )
        }


        Box(modifier = Modifier
            .background(
                if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(0xFFE7EEFC),
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(vertical = 5.sdp, horizontal = 10.sdp)
            .clickable { selectedBoxIndex.value = 1 }) {
            Text(
                stringResource(R.string.closed_deposits), style = TextStyle(
                    fontSize = 12.sp,
                    color = if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142)
                )
            )
        }

    }
}

private fun fetchUserDetails(): LoginVerifyResponse {
    val str = MainApp.session[Keys.KEY_USER_DETAILS]
    return Converter.fromJson(str!!, LoginVerifyResponse::class.java)
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun TrustsPreview() {
    TrustsList(rememberNavController())
}