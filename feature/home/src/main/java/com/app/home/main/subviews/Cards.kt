package com.app.home.main.subviews

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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R

import com.app.uikit.data.DataProvider
import com.app.home.main.cards.navigation.homeToCardDetails
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetNewCards
import com.app.network.models.responseModels.GetOldCards
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.MainCard
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel
import com.app.uikit.models.CardFilters
import ir.kaaveh.sdpcompose.sdp


val cardsList = mutableListOf<MainCard>()

@Composable
fun CardsList(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)
//    val userDetails = MainApp.session.fetchUserDetails()

    val oldBusinessCards by viewModel.oldBusinessCards.collectAsState()
    val newBusinessCards by viewModel.newBusinessCards.collectAsState()

    val selectedBoxIndex = remember { mutableStateOf(0) }
//    val cardsList = remember { mutableListOf<MainCard>() }
    val cardFilters = remember { DataProvider.filtersList }
    val isLoading = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getOldBusinessCards(userDetails.customerNo)
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
            modifier = Modifier.padding(horizontal = 5.sdp, vertical = 5.sdp)
        ) {


            Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                Box(modifier = Modifier
                    .background(
                        if (selectedBoxIndex.value == 0) colorResource(R.color.background_card_blue) else Color(
                            R.color.border_grey
                        ),
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .padding(vertical = 5.sdp, horizontal = 10.sdp)
                    .clickable {
                        selectedBoxIndex.value = 0


                        viewModel.getOldBusinessCards(userDetails.customerNo)

                    }) {
                    Text(
                        stringResource(R.string.in_the_name_of_a_physical_person),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = if (selectedBoxIndex.value == 0) Color.White else Color(
                                R.color.background_card_blue
                            )
                        )
                    )
                }



                Box(modifier = Modifier
                    .background(
                        if (selectedBoxIndex.value == 1) colorResource(R.color.background_card_blue) else Color(
                            R.color.border_grey
                        ),
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .padding(vertical = 5.sdp, horizontal = 10.sdp)
                    .clickable {
                        selectedBoxIndex.value = 1

                        viewModel.getNewBusinessCards(userDetails.customerNo)

                    }) {
                    Text(
                        stringResource(R.string.tin_based), style = TextStyle(
                            fontSize = 12.sp,
                            color = if (selectedBoxIndex.value == 1) Color.White else colorResource(
                                R.color.background_card_blue
                            )
                        )
                    )
                }

            }


            LazyRow(
                contentPadding = PaddingValues(vertical = 5.dp)
            ) {
                items(items = cardFilters, itemContent = {
                    Row {
                        FilterView(filter = it)
                        Box(modifier = Modifier.padding(end = 5.sdp))
                    }

                })
            }

            val lazyListState = rememberLazyListState()
            LaunchedEffect(lazyListState) {
                lazyListState.scrollToItem(0) // Optional: Scroll to an initial position when the list is first displayed
            }


            LazyColumn(
                contentPadding = PaddingValues(vertical = 1.dp),
                state = lazyListState,
            ) {
                items(items = cardsList, itemContent = {
                    CardsListItem(obj = it) {
                        navController.navigate(homeToCardDetails)
                    }
                })
            }

        }

    }



    oldBusinessCards?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                val cards = it.data as GetOldCards

                isLoading.value = false

                if (cards.oldBusinessCards.MainCards != null) {

                    cardsList.apply {
                        clear()
                        addAll(cards.oldBusinessCards.MainCards)
                    }

                }

            }
        }
    }

    newBusinessCards?.let {
        when (it) {
            is DataState.Loading -> {

            }

            is DataState.Error -> {
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                try {
                    val cards = it.data as GetNewCards
                    cardsList.apply {
                        clear()
//                        addAll(cards.newBusinessCards.MainCards)
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

}


@Composable
fun CardsListItem(obj: MainCard, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                onCardClick()
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
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_master_card),
                contentDescription = "",
                modifier = Modifier.size(width = 36.dp, height = 24.dp)
            )

            Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp, top = 5.dp)
            ) {
                Text(
                    text = obj.nickName,
                    style = TextStyle(fontSize = 14.sp),
                    color = colorResource(R.color.background_card_blue),
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_master_card_icon),
                        contentDescription = "",
                        modifier = Modifier.size(width = 30.dp, height = 20.dp)
                    )

                    Text(
                        text = obj.EncryptedPan,
                        style = TextStyle(fontSize = 14.sp),
                        color = colorResource(R.color.background_card_blue),
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Box(
                        Modifier.padding(start = 4.sdp)
                    ) {

                    }

                    Box(
                        Modifier
                            .width(26.dp)
                            .height(22.dp)
                            .background(
                                color = Color(0xFFF3F7FA), shape = RoundedCornerShape(size = 30.dp)
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "${obj.AdditionNumb}", style = TextStyle(
                                fontSize = 14.sp, color = colorResource(R.color.grey_text)
                            )

                        )
                    }
                }
            }

            Text(
                text = if (obj.Balance == null) "0.00" else "${obj.Balance}",
                style = TextStyle(fontSize = 14.sp),
                color = colorResource(R.color.background_card_blue)
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


//@Composable
//private fun Filters() {
//
////    val selectedBoxIndex = remember { mutableStateOf(-1) }
////    val selectedBoxIndex = remember { mutableStateOf(0) }
//
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
//        verticalAlignment = Alignment.Top,
//    ) {
//        Box(modifier = Modifier
//            .background(
//                if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(R.color.border_grey),
//                shape = RoundedCornerShape(size = 6.dp)
//            )
//            .padding(vertical = 5.sdp, horizontal = 10.sdp)
//            .clickable { selectedBoxIndex.value = 0 }) {
//            Text(
//                stringResource(R.string.in_the_name_of_a_physical_person), style = TextStyle(
//                    fontSize = 12.sp,
//                    color = if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142)
//                )
//            )
//        }
//
//
//
//        Box(modifier = Modifier
//            .background(
//                if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(R.color.border_grey),
//                shape = RoundedCornerShape(size = 6.dp)
//            )
//            .padding(vertical = 5.sdp, horizontal = 10.sdp)
//            .clickable { selectedBoxIndex.value = 1 }) {
//            Text(
//                stringResource(R.string.tin_based), style = TextStyle(
//                    fontSize = 12.sp,
//                    color = if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142)
//                )
//            )
//        }
//
//    }
//}


@Preview(device = Devices.PIXEL_4)
@Composable
fun CardsPreview() {
    CardsList(rememberNavController())
}