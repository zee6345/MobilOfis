package com.app.adjustment.exchangerate

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetExchangeRates
import com.app.network.models.responseModels.GetExchangeRatesItem
import com.app.network.utils.Message
import com.app.network.viewmodel.AdjustmentViewModel
import com.app.uikit.borders.dashedBorder
import com.app.uikit.borders.rightVerticalDashedBorder
import com.app.uikit.dialogs.ShowProgressDialog


@Composable
fun ExchangeRatesScreen(
    navController: NavController,
    viewModel: AdjustmentViewModel = hiltViewModel()
) {

    val context: Context = LocalContext.current
    val exchangeList by viewModel.exchangeRates.collectAsState()
    val coroutine = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val exchangeData = remember { mutableListOf<GetExchangeRatesItem>() }
    val date = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getExchangeRates()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F7FA)),
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
                    .padding(20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(height = 25.dp, width = 32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { navController.popBackStack() },
                    contentDescription = ""
                )
                Text(
                    text = stringResource(id = R.string.exchange_rates),
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }

        Column(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            if (isLoading.value) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                }
//            } else {

                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 22.dp),
                    backgroundColor = Color.White
                ) {


                    Column {

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(CenterHorizontally)
                                .dashedBorder(
                                    3.dp, Color(0xFFE7EEFC)
                                )
                                .padding(horizontal = 12.dp, vertical = 12.dp),
                            text = "${date.value}"
                                .replace("/", "."),
                            style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center)
                        )

                        Row(
                            modifier = Modifier
                                .dashedBorder(
                                    3.dp, Color(0xFFE7EEFC)
                                )
                                .padding(end = 12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.7f)
                                    .rightVerticalDashedBorder(
                                        3.dp, Color(0xFFE7EEFC)
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 12.dp)
                                        .fillMaxWidth(),
                                    text = stringResource(R.string.bank_republic),
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .weight(0.3f)
                                    .dashedBorder(
                                        3.dp, Color(0xFFE7EEFC)
                                    )
                                    .padding(vertical = 12.dp),
                                text = stringResource(R.string.central_bank),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            )

                        }

                        Row(
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.7f)
                                    .rightVerticalDashedBorder(
                                        3.dp, Color(0xFFE7EEFC)
                                    )
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Spacer(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                    )
                                    Text(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        text = stringResource(R.string.purchase),
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF015CD1)
                                        )
                                    )
                                    Text(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        text = stringResource(R.string.sale),
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF015CD1)
                                        )
                                    )
                                }


                            }
                            Spacer(modifier = Modifier.weight(0.3f))
                        }

                        ExchangeRatesList(exchangeData)
                    }


                }
//            }


        }

    }



    exchangeList?.let {

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

                val data = it.data as GetExchangeRates

                if (data != null) {
                    exchangeData.apply {
                        clear()
                        date.value = data[0].ratedate
                        addAll(data)
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
fun ExchangeRatesList(exchangeData: MutableList<GetExchangeRatesItem>) {
    LazyColumn {
        items(items = exchangeData, itemContent = {
            ExchangeRatesListItem(data = it)
        })
    }
}

@Composable
fun ExchangeRatesListItem(data: GetExchangeRatesItem) {

    val purchaseRateIcon =
        if (data.buyTrend == 0) R.drawable.ic_rectangle else if (data.buyTrend == 1) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
    val sellRateIcon =
        if (data.sellTrend == 0) R.drawable.ic_rectangle else if (data.sellTrend == 1) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
    val cbRateIcon =
        if (data.cbTrend == 0) R.drawable.ic_rectangle else if (data.cbTrend == 1) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down

    Row(
        modifier = Modifier
            .padding(end = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Box(
            modifier = Modifier
                .weight(0.7f)
                .rightVerticalDashedBorder(
                    3.dp, Color(0xFFE7EEFC)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(1f),
                    text = "${data.ccy}",
                    style = TextStyle(
                        fontSize = 16.sp, textAlign = TextAlign.Center, color = Color(0xFF015CD1)
                    )
                )

                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(purchaseRateIcon),
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(top = 5.dp),
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                        text = "${data.buyrate}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF223142)
                        )
                    )
                }
                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(sellRateIcon),
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(top = 5.dp),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 7.dp),
                        text = "${data.sellrate}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF223142)
                        )
                    )
                }

            }


        }
        Row(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
        ) {
            Spacer(
                modifier = Modifier
                    .size(3.dp)
                    .weight(0.3f)
            )
            Row(modifier = Modifier.weight(0.7f)) {
                Image(
                    painter = painterResource(cbRateIcon),
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(top = 5.dp),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 7.dp),
                    text = "${data.cbrate}", style = TextStyle(
                        fontSize = 14.sp, textAlign = TextAlign.Center, color = Color(0xFF223142)
                    )
                )
            }


        }

    }


}


@Preview(device = Devices.PIXEL_4)
@Composable
fun ExchangeRatesScreenPreview() {
    ExchangeRatesScreen(rememberNavController())

}