package com.app.home.main.cards

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.app.home.main.subviews.selectedOldCard
import com.app.network.models.responseModels.AdditionCard
import com.app.network.models.responseModels.MainCard
import com.app.uikit.borders.dashedBorder
import com.app.uikit.borders.rightVerticalDashedBorder
import com.app.uikit.bottomSheet.BusinessCardOptionsSheet
import com.app.uikit.data.DataProvider
import com.app.uikit.models.CardFilters
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


const val homeToOldCardDetails = "homeToOldCardDetails"

@Composable
fun CardDetailsOld(navController: NavController) {

    val data = selectedOldCard.value


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
                    text = stringResource(R.string.business_cards),
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
//                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    MainContent(navController, data!!)
                }

                if (data!!.AdditionCards.isNotEmpty() && data!!.AdditionCards != null) {

                    item {

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = stringResource(R.string.additional_cards),
                                modifier = Modifier.padding(vertical = 5.sdp, horizontal = 10.sdp)
                            )

                            Text(
                                text = "${data.AdditionNumb}",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .drawBehind {
                                        drawCircle(
                                            color = Color(0xFF203657), radius = 12.dp.toPx()
                                        )
                                    },
                                fontSize = 12.sp,
                                color = Color.White,
                            )
                        }

                    }

                    item {
                        CardDetailsFilters(navController)
                        Spacer(modifier = Modifier.padding(bottom = 5.dp))
                    }

                    item {
                        data!!.AdditionCards.forEachIndexed { index, additionCard ->
                            AdditionalCards(additionCard)
                        }
                    }

                }
            }

        }

    }
}

@Composable
private fun MainContent(navController: NavController, data: MainCard) {

    val businessCardOptions = rememberSaveable { mutableStateOf(false) }

    val icon = if (data!!.PaymentSys.equals("Master", true)) {
        painterResource(id = R.drawable.ic_master_card)
    } else if (data!!.PaymentSys.equals("VISA", true)) {
        painterResource(id = R.drawable.ic_visa_business)
    } else {
        painterResource(id = R.drawable.ic_master_card)
    }

    val subIcon = if (data!!.PaymentSys.equals("Master", true)) {
        painterResource(id = R.drawable.ic_master_card_icon)
    } else if (data!!.PaymentSys.equals("VISA", true)) {
        painterResource(id = R.drawable.ic_visa_icon)
    } else {
        painterResource(id = R.drawable.ic_master_card_icon)
    }

    val shareIntentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        // This block will be executed after returning from the sharing activity
        // You can perform any necessary actions here
    }


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
                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row {
                    Box(
                        modifier = Modifier
                            .size(width = 36.dp, height = 24.dp)
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(5.dp))
                    ) {
                        Image(
                            painter = icon,
                            contentDescription = "",
                            modifier = Modifier.size(width = 36.dp, height = 24.dp)
                        )

                    }

                    Spacer(
                        Modifier.size(width = 5.dp, height = 1.dp)
                    )

                    Text(
                        text = if (data.nickName == null) "${data.Name}" else "${data.nickName}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium))
                        )
                    )

                }


                Image(
                    painter = painterResource(id = R.drawable.ic_options), contentDescription = "",
                    Modifier
                        .size(height = 28.dp, width = 28.dp)
                        .clickable {
//                            businessCardOptions.value = true
                        }
                )
            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Row {

                    Image(
                        painter = subIcon,
                        contentDescription = "",
                        modifier = Modifier.size(width = 36.dp, height = 24.dp)
                    )

                    Spacer(
                        Modifier.size(width = 5.dp, height = 1.dp)
                    )

                    Text(
                        text = Utils.formatCardNumber(data.Pan), style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium))
                        )
                    )

                }


                if (data.AdditionNumb > 0) {
                    Row {

                        Text(
                            text = stringResource(R.string.additional_card), style = TextStyle(
                                fontSize = 14.sp,
                                color = colorResource(R.color.grey_text),
                                fontFamily = FontFamily(
                                    Font(R.font.roboto_medium),

                                    )
                            )
                        )

                        Spacer(
                            Modifier.size(width = 5.dp, height = 1.dp)
                        )

                        Text(
                            text = "${data.AdditionNumb}", style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_medium))
                            )
                        )
                    }
                }

            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = stringResource(R.string.user), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.CardHolder}", style = TextStyle(
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
                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = stringResource(R.string.card_account_iban), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.Iban}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }


                Image(
                    painter = painterResource(id = R.drawable.ic_share), contentDescription = "",
                    Modifier
                        .size(height = 20.dp, width = 20.dp)
                        .clickable {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "${data.Iban}")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            shareIntentLauncher.launch(shareIntent)
                        }
                )

            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(2.dp, colorResource(R.color.border_grey)),
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
                        text = stringResource(R.string.status), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.CardStat}", style = TextStyle(

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
                        text = stringResource(R.string.end_date), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.ExpDate}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF7B61FF),


                            )
                    )
                }
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(2.dp, colorResource(R.color.border_grey)),
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
                        text = stringResource(id = R.string.balance), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = if (data.Balance != null) Utils.formatAmountWithSpaces(data.Balance!!) else "0.00",
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
                        text = stringResource(id = R.string.currency), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.Currency}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }
            }


        }
    }

    BusinessCardOptionsSheet(businessCardOptions)


}

@Composable
private fun CardDetailsFilters(navController: NavController) {

    val cardFilters = remember { DataProvider.filtersList }

    LazyRow(
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp)
    ) {
        items(items = cardFilters, itemContent = {
            Row {
                FilterItem(filter = it)
                Box(modifier = Modifier.padding(end = 5.sdp))
            }
        })
    }


}

@Composable
private fun FilterItem(filter: CardFilters) {
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

@Composable
private fun AdditionalCards(additionCard: AdditionCard) {

    val isExpanded = remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()


    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color(0xFFE9ECF5)
    ) {
        Column {

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row {

                    Box(
                        modifier = Modifier
                            .size(width = 36.dp, height = 24.dp)
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(5.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_visa_business),
                            contentDescription = "",
                            modifier = Modifier.size(width = 36.dp, height = 24.dp),
                        )
                    }

                    Spacer(
                        Modifier.size(width = 5.dp, height = 1.dp)
                    )

                    Text(
                        text = "${additionCard.Name}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium))
                        )
                    )

                }


                Row() {

                    Image(
                        painter = painterResource(id = R.drawable.ic_options),
                        contentDescription = "",
                        Modifier
                            .size(height = 26.dp, width = 26.dp)
                            .padding(3.dp)
                    )

                    Image(
                        painter = if (isExpanded.value) painterResource(id = R.drawable.ic_options_collapse) else painterResource(
                            id = R.drawable.ic_option_expand
                        ),
                        contentDescription = "",
                        Modifier
                            .size(height = 26.dp, width = 26.dp)
                            .padding(3.dp)
                            .clickable {
                                coroutine.launch {
                                    isExpanded.value = !isExpanded.value
                                }
                            }
                    )

                }

            }

            if (isExpanded.value) {

                Box(
                    Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(0.4.dp)
                        .background(color = Color(0xFFCAD5ED))
                ) {

                }


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.sdp, vertical = 8.sdp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Row {

                        Image(
                            painter = painterResource(id = R.drawable.ic_master_card_icon),
                            contentDescription = "",
                            modifier = Modifier.size(width = 36.dp, height = 24.dp)
                        )

                        Spacer(
                            Modifier.size(width = 5.dp, height = 1.dp)
                        )

                        Text(
                            text = "${additionCard.Pan}", style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_medium))
                            )
                        )

                    }


                }


                Box(
                    Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(0.4.dp)
                        .background(color = Color(0xFFCAD5ED))
                ) {

                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.sdp, vertical = 8.sdp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = stringResource(id = R.string.user), style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.grey_text),
                            )
                        )

                        Text(
                            text = "${additionCard.CardHolder}", style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.background_card_blue),
                            )
                        )
                    }


                }


                Box(
                    Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(0.4.dp)
                        .background(color = Color(0xFFCAD5ED))
                ) {

                }


                Row(
                    Modifier
                        .fillMaxWidth()
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

                        Text(
                            text = "${additionCard.CardStat}", style = TextStyle(
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
                            text = "${additionCard.Currency}", style = TextStyle(
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
                            text = stringResource(id = R.string.end_date), style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.grey_text),
                            )
                        )

                        Text(
                            text = "${additionCard.ExpDate}", style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.background_card_blue),


                                )
                        )
                    }
                }

            }

        }
    }

}

@Preview
@Composable
private fun PreviewCardDetails() {
    CardDetailsOld(navController = rememberNavController())
}