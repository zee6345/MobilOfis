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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import com.app.home.main.cards.homeToNewCardDetails
import com.app.home.main.cards.homeToOldCardDetails
import com.app.home.main.enableSearch
import com.app.home.main.isShowBalance
import com.app.home.main.model.Search
import com.app.home.main.model.SearchBy
import com.app.home.main.searchBy
import com.app.home.main.searchFrom
import com.app.home.main.searchIban
import com.app.home.main.searchUser
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetNewCards
import com.app.network.models.responseModels.GetOldCards
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.MainCard
import com.app.network.models.responseModels.MainCardX
import com.app.network.viewmodel.HomeViewModel
import com.app.uikit.bottomSheet.BottomSheetCardStatus
import com.app.uikit.data.DataProvider
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.models.CardFilters
import com.app.uikit.utils.Utils
import com.app.uikit.views.AutoResizedText
import com.app.uikit.views.FilterView
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


val selectedOldCard = mutableStateOf<MainCard?>(null)
val selectedNewCard = mutableStateOf<MainCardX?>(null)
private val filterByStatus = mutableStateOf("")

@Composable
fun CardsList(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    val oldBusinessCards by viewModel.oldBusinessCards.collectAsState()
    val newBusinessCards by viewModel.newBusinessCards.collectAsState()

    val selectedBoxIndex = remember { mutableStateOf(0) }
    val cardFilters = remember { DataProvider.filtersList }
    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(false) }
    var sortByEndDate by remember { mutableStateOf(false) }
    var sortByBalance by remember { mutableStateOf(false) }

    val showStatusBottomSheet = remember { mutableStateOf(false) }

    val oldCards = remember { mutableListOf<MainCard>() }
    val newCards = remember { mutableListOf<MainCardX>() }
    val cardStatusList = remember { mutableListOf<String>() }
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getOldBusinessCards(userDetails.customerNo)
        }
    }


    //filter by card status
    val filterList = oldCards.filter {
        it.CardStat.contains(filterByStatus.value, true)
    }.filter {
        when (searchBy.value) {
            SearchBy.ByIBAN -> {
                it.Iban.contains(searchIban.value, true)
            }

            SearchBy.ByUser -> {
                it.Name.contains(searchUser.value, true)
            }

            else -> {
                true
            }
        }
    }.toList()


    //sort list by end date
    val sortedList = if (sortByEndDate) {
        filterList.sortedBy { it.ExpDate }
    } else if (sortByBalance) {
        filterList.sortedBy { it.Balance }
    } else {
        filterList
    }



    //filter by card status
    val filterNewList = newCards.filter {
        it.IBANStat.contains(filterByStatus.value, true)
    }.filter {
        when (searchBy.value) {
            SearchBy.ByIBAN -> {
                it.Iban.contains(searchIban.value, true)
            }

            SearchBy.ByUser -> {
                it.Name.contains(searchUser.value, true)
            }

            else -> {
                true
            }
        }
    }.toList()

    //sort list by end date
    val sortedNewList = if (sortByEndDate) {
        filterNewList.sortedBy { it.OpenDate }
    } else {
        filterNewList
    }

    Column {


        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(top = 10.sdp, start = 10.sdp, end = 10.sdp)
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
                    viewModel.getOldBusinessCards(userDetails.customerNo)
                }) {
                Text(
                    stringResource(R.string.in_the_name_of_a_physical_person),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = if (selectedBoxIndex.value == 0) Color.White else colorResource(
                            R.color.background_card_blue
                        )
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

        Spacer(modifier = Modifier.size(width = 1.dp, height = 10.sdp))


        if (!isEmpty.value) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 10.sdp)
            ) {

                cardFilters.forEachIndexed { index, cardFilters ->

                    item {
                        Row {
                            FilterView(filter = cardFilters) {

                                when (index) {
                                    0 -> {
                                        enableSearch.value = true
                                        searchFrom.value = Search.FromCards
                                        searchBy.value = SearchBy.ByIBAN
                                    }

                                    1 -> {
                                        enableSearch.value = true
                                        searchFrom.value = Search.FromCards
                                        searchBy.value = SearchBy.ByUser
                                    }

                                    2 -> {
                                        showStatusBottomSheet.value = true
                                    }

                                    3 -> {
                                        sortByEndDate = true
                                    }

                                    4 -> {
                                        sortByBalance = true
                                    }

                                }

                            }
                            Box(modifier = Modifier.padding(end = 5.sdp))
                        }
                    }

                }


            }
        }

        Spacer(modifier = Modifier.padding(bottom = 5.dp))


        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 5.sdp, vertical = 5.sdp)
        ) {

            if (selectedBoxIndex.value == 0) {
                //old cards

                if (oldCards.isNotEmpty()) {


                    sortedList.forEachIndexed { index, mainCard ->
                        item {
                            OldCardsListItem(obj = mainCard) {
                                selectedOldCard.value = it
                                navController.navigate(homeToOldCardDetails)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.size(width = 1.dp, height = 50.sdp))
                    }

                } else {
                    item {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(20.sdp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No old card found!")
                        }
                    }
                }


            } else if (selectedBoxIndex.value == 1) {

                //new cards
                if (newCards.isNotEmpty()) {

                    sortedNewList.forEachIndexed { index, mainCardX ->
                        item {
                            NewCardsListItem(obj = mainCardX) {
                                selectedNewCard.value = it
                                navController.navigate(homeToNewCardDetails)
                            }
                        }
                    }

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
                            Text("No new card found!")
                        }
                    }
                    isEmpty.value = true
                }
            }

        }

    }

    BottomSheetCardStatus(
        showStatusBottomSheet = showStatusBottomSheet,
        statusList = cardStatusList,
        onStatusClick = {
            showStatusBottomSheet.value = false
            filterByStatus.value = it
        })

    oldBusinessCards?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                val cards = it.data as GetOldCards

                isLoading.value = false

                if (cards.oldBusinessCards.MainCards != null) {

                    if (oldCards.isNotEmpty()) {
                        oldCards.clear()
                    }

                    oldCards.addAll(cards.oldBusinessCards.MainCards)



                    //card status
                    if (cardStatusList.isNotEmpty()) {
                        cardStatusList.clear()
                    }

                    cards.oldBusinessCards.MainCards.forEach {
                        cardStatusList.add(it.CardStat)
                    }


                }

            }
        }
    }

    newBusinessCards?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                val cards = it.data as GetNewCards

                if (cards.newBusinessCards.MainCards != null) {
                    if (newCards.isNotEmpty()) {
                        newCards.clear()
                    }
                    newCards.addAll(cards.newBusinessCards.MainCards)



                    //card status
                    if (cardStatusList.isNotEmpty()) {
                        cardStatusList.clear()
                    }

                    cards.newBusinessCards.MainCards.forEach {
                        cardStatusList.add(it.IBANStat)
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
private fun OldCardsListItem(obj: MainCard, onCardClick: (MainCard) -> Unit) {


    val symbol = Utils.formatCurrency(obj.Currency)

    val amount: String = if (obj.Balance != null) {
        "${obj.Balance}"
    } else {
        "0.00"
    }

    val icon = if (obj.PaymentSys.equals("Master", true)) {
        painterResource(id = R.drawable.ic_master_card)
    } else if (obj.PaymentSys.equals("VISA", true)) {
        painterResource(id = R.drawable.ic_visa_business)
    } else {
        painterResource(id = R.drawable.ic_master_card)
    }

    val subIcon = if (obj.PaymentSys.equals("Master", true)) {
        painterResource(id = R.drawable.ic_master_card_icon)
    } else if (obj.PaymentSys.equals("VISA", true)) {
        painterResource(id = R.drawable.ic_visa_icon)
    } else {
        painterResource(id = R.drawable.ic_master_card_icon)
    }


    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.sdp)
            .fillMaxWidth()
            .clickable {
                onCardClick(obj)
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

            Box(
                modifier = Modifier
                    .size(width = 36.dp, height = 24.dp)
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Image(
                    painter = icon,
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(width = 36.dp, height = 26.dp)
                )
            }

            Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp, top = 5.dp)
            ) {
                Text(
                    text = if (obj.nickName == null) obj.Name else obj.nickName,
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
                        painter = subIcon,
                        contentDescription = "",
                        modifier = Modifier.size(width = 30.dp, height = 20.dp)
                    )

                    Text(
                        text = if (isShowBalance.value) "${obj.EncryptedPan}" else "${obj.Pan}",
                        style = TextStyle(fontSize = 14.sp),
                        color = colorResource(R.color.background_card_blue),
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Box(
                        Modifier.padding(start = 4.sdp)
                    ) {

                    }

                    if (obj.AdditionNumb > 0) {
                        Box(
                            Modifier
                                .width(26.dp)
                                .height(22.dp)
                                .background(
                                    color = Color(0xFFF3F7FA),
                                    shape = RoundedCornerShape(size = 30.dp)
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
            }

            AutoResizedText(
                text = if (isShowBalance.value) "****" else "${Utils.formatAmountWithSpaces(amount.toDouble())} $symbol",
                color = colorResource(R.color.background_card_blue),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF223142),
                    textAlign = TextAlign.Right,
                )
            )
        }


    }
}

@Composable
private fun NewCardsListItem(obj: MainCardX, onCardClick: (MainCardX) -> Unit) {

    val symbol = Utils.formatCurrency(obj.Currency)

    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.sdp)
            .fillMaxWidth()
            .clickable {
                onCardClick(obj)
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

            Box(
                modifier = Modifier
                    .size(width = 36.dp, height = 24.dp)
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_master_card),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(width = 36.dp, height = 26.dp)
                )
            }

//            Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp, top = 5.dp)
            ) {
                Text(
                    text = if (obj.nickName == null) obj.Name else obj.nickName,
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

                    AutoResizedText(
                        text = "${obj.Iban}",
                        style = TextStyle(fontSize = 14.sp),
                        color = colorResource(R.color.background_card_blue),
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Box(
                        Modifier.padding(start = 4.sdp)
                    ) {

                    }

                    if (obj.AdditionNumb > 0) {
                        Box(
                            Modifier
                                .width(26.dp)
                                .height(22.dp)
                                .padding(2.dp)
                                .background(
                                    color = Color(0xFFF3F7FA),
                                    shape = RoundedCornerShape(size = 30.dp)
                                ), contentAlignment = Alignment.Center
                        ) {
                            AutoResizedText(
                                text = "+ ${obj.AdditionNumb}", style = TextStyle(
                                    fontSize = 14.sp, color = colorResource(R.color.grey_text)
                                )

                            )
                        }
                    }
                }
            }

            AutoResizedText(
                text = if (isShowBalance.value) "****" else "",
                color = colorResource(R.color.background_card_blue),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF223142),
                    textAlign = TextAlign.Right,
                )
            )
        }


    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun CardsPreview() {
    CardsList(rememberNavController())
}