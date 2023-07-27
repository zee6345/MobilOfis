package com.app.adjustment.exchangerate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R

import com.app.adjustment.components.dashedBorder
import com.app.adjustment.data.DataProvider
import com.app.adjustment.data.ExchangeRatesModel


@Composable
fun ExchangeRatesScreen(navController: NavController) {
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
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        text = stringResource(R.string._17_10_2022),
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
                                style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
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
                            style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
                        )

                    }

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
                    ExchangeRatesList()
                }


            }


        }

    }
}

fun Modifier.rightVerticalDashedBorder(strokeWidth: Dp, color: Color) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }
    this.then(Modifier.drawWithCache {
        onDrawBehind {
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            val strokeWidthWithDensity = strokeWidthPx / density.density
            val start = Offset(size.width, 0f)
            val end = Offset(size.width, size.height)

            // Draw the dashed line
            drawLine(
                color = color,
                start = start,
                end = end,
                strokeWidth = strokeWidthWithDensity,
                pathEffect = pathEffect
            )
        }
    })
})




@Composable
fun ExchangeRatesList() {
    val exchangeList = remember { DataProvider.exchangeList }
    LazyColumn(
//        contentPadding = PaddingValues()
    ) {
        items(items = exchangeList, itemContent = {
            ExchangeRatesListItem(list = it)
        })
    }
}

@Composable
fun ExchangeRatesListItem(list: ExchangeRatesModel) {

    Row(
        modifier = Modifier
            .dashedBorder(
                3.dp, Color(0xFFE7EEFC)
            )
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
//                    .padding(horizontal = 2.dp)
            ) {

                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(1f),
                    text = list.country_name,
                    style = TextStyle(
                        fontSize = 16.sp, textAlign = TextAlign.Center, color = Color(0xFF015CD1)
                    )
                )

                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(id = list.purchase_icon),
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(top = 5.dp),
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                        text = list.purchase_rate,
                        style = TextStyle(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF223142)
                        )
                    )
                }
                Row(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(id = list.sale_icon),
                        modifier = Modifier
                            .align(CenterVertically)
                            .padding(top = 5.dp),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 7.dp),
                        text = list.sale_rate,
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
                    painter = painterResource(id = list.bank_icon),
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(top = 5.dp),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 7.dp),
//                    .weight(0.3f),
                    text = list.bank_rate, style = TextStyle(
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