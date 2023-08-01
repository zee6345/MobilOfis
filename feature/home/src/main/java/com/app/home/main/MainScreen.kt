package com.app.home.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.home.main.component.CardMenuContent

import com.app.home.main.component.SelectCompanyBottomSheet
import com.app.home.main.component.TabLayoutMenu
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetCustomerBalance
import com.app.network.data.responseModels.GetCustomerBalanceItem
import com.app.network.data.responseModels.GetRecentOps
import com.app.network.data.responseModels.GetRecentOpsItem

import com.app.network.data.responseModels.LoginVerifyResponse
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


private const val TAG = "MenuScreen"


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {

    val selectCompanyState = rememberSaveable { mutableStateOf(false) }
    val showBalance = rememberSaveable { mutableStateOf(false) }
    val balancePopup = rememberSaveable { mutableStateOf(false) }
    var touchPoint: Offset by remember { mutableStateOf(Offset.Zero) }
    val customerBalanceType = remember { mutableStateOf(Balance.BALANCE) }
    val balance = remember { mutableStateOf("") }
    val customerBalance = remember { mutableStateListOf<GetCustomerBalanceItem>() }
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    var isBottomSheetExpanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val accountBalance by viewModel.accountBalance.collectAsState()
    val recentOps by viewModel.recentOps.collectAsState()
//    val dates = mutableListOf<String>()
    val recentData = mutableListOf<GetRecentOpsItem>()

    val context = LocalContext.current
    val userDetails = MainApp.session.fetchUserDetails()

    LaunchedEffect(key1 = true) {
        viewModel.getBalance(userDetails.customerNo)
        viewModel.getRecentOps(userDetails.customerNo)
    }




    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 40.sdp,
        modifier = Modifier.padding(bottom = 40.sdp),
        sheetShape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
        sheetContent = {

            Column(Modifier.fillMaxWidth()) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isBottomSheetExpanded = !isBottomSheetExpanded

                            coroutineScope.launch {
                                if (isBottomSheetExpanded) {
                                    scaffoldState.bottomSheetState.expand()
                                } else {
                                    scaffoldState.bottomSheetState.collapse()
                                }
                            }

                        }
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_options_up),
                        modifier = Modifier.size(width = 24.sdp, height = 24.sdp),
                        tint = Color(0xFF223142),
                        contentDescription = ""
                    )
                }


                Text(
                    text = "Recent operations on accounts",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.sdp),
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row {

                        Icon(
                            painterResource(id = R.drawable.ic_options_arrow_in),
                            modifier = Modifier.size(width = 18.sdp, height = 12.sdp),
                            tint = Color(0xFF223142),
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                        )

                        Text(
                            text = "5600.00",
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
                            modifier = Modifier.size(width = 18.sdp, height = 12.sdp),
                            tint = Color(0xFFFF4E57),
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                        )


                        Text(
                            text = "-5600.00",
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.sdp, vertical = 5.sdp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val selectedBoxIndex = remember { mutableStateOf(-1) }

                    Row() {
                        Box(modifier = Modifier
                            .padding(6.dp)
                            .background(
                                if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(
                                    0xFFE7EEFC
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedBoxIndex.value = 0 }) {
                            Text(
                                text = "All", modifier = Modifier.padding(6.dp), style = TextStyle(
                                    if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142),
                                    fontSize = 12.sp
                                )
                            )
                        }
                        Box(modifier = Modifier
                            .padding(6.dp)
                            .background(
                                if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(
                                    0xFFE7EEFC
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedBoxIndex.value = 1 }) {
                            Text(
                                text = "Income", modifier = Modifier.padding(6.dp), style = TextStyle(
                                    if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142),
                                    fontSize = 12.sp
                                )
                            )
                        }
                        Box(modifier = Modifier
                            .padding(6.dp)
                            .background(
                                if (selectedBoxIndex.value == 2) Color(0xFF223142) else Color(
                                    0xFFE7EEFC
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedBoxIndex.value = 2 }) {
                            Text(
                                text = "Expenditure",
                                modifier = Modifier.padding(6.dp),
                                style = TextStyle(
                                    if (selectedBoxIndex.value == 2) Color.White else Color(0xFF223142),
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }

                    Box() {
                        Text(
                            text = "More"
                        )
                    }

                    Box() {
                        Icon(
                            painterResource(R.drawable.ic_transfers),
                            tint = Color(0xFF223142),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 10.sdp)
                        )
                    }

                }

                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    recentData?.groupBy { it.trn_date }?.forEach { (date, itemList) ->
                        item {
                            DateHeader(date)
                        }
                        items(itemList.size) { item ->
                            CardsItem(itemList[item])
                        }
                    }
                }


            }


        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF3F7FA))
        ) {
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                    .fillMaxWidth()
                    .weight(0.25f),
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(color = Color(0xFF203657).copy(alpha = 0.9f)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 22.dp, end = 12.dp)

                        ) {

                            Row(
                                modifier = Modifier.weight(0.7f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_business_icon),
                                    modifier = Modifier
                                        .size(28.dp)
                                        .align(Top)
                                        .clickable {
                                            selectCompanyState.value = !selectCompanyState.value
                                        },
                                    contentDescription = "",
                                    tint = Color.White
                                )
                                Column(
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .padding(start = 5.dp)
                                ) {
                                    Text(
                                        text = userDetails.customerName,
                                        style = TextStyle(color = Color.White, fontSize = 14.sp),
                                        modifier = Modifier
                                            .padding(start = 3.dp)
                                            .widthIn(max = 140.dp)
                                            .wrapContentWidth(align = Alignment.Start),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = stringResource(R.string.semire), style = TextStyle(
                                            color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp
                                        )
                                    )
                                }

                                Image(
                                    painter = painterResource(id = R.drawable.ic_business_expand),
                                    modifier = Modifier
                                        .size(16.dp)
                                        .align(Top)
                                        .clickable {
                                            selectCompanyState.value = !selectCompanyState.value
                                        },
                                    contentDescription = ""
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.3f)
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Top
                            ) {
                                Image(
                                    painter =
                                    if (showBalance.value)
                                        painterResource(id = R.drawable.ic_password_visible_off)
                                    else painterResource(id = R.drawable.ic_password_visible),
                                    modifier = Modifier
                                        .size(22.dp)
                                        .align(Top)
                                        .clickable {
                                            showBalance.value = !showBalance.value
                                        },
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.ic_option_icon),
                                    modifier = Modifier
                                        .size(22.dp)
                                        .align(Top),
                                    contentDescription = ""
                                )
                            }

                        }

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(color = Color(0xFF203657)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            Modifier
                                .padding(horizontal = 22.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures {

                                        touchPoint = it
                                        balancePopup.value = !balancePopup.value
                                    }

                                },
                            verticalArrangement = Arrangement.Center

                        ) {
                            val annotatedString = buildAnnotatedString {
                                withStyle(style = SpanStyle(Color.White.copy(0.5f), fontSize = 14.sp)) {
                                    append(stringResource(R.string.total_accounts))
                                }
                                withStyle(style = SpanStyle(Color.White, fontSize = 14.sp)) {
                                    append(customerBalanceType.value.name)
                                }
                            }

                            Row() {
                                Text(
                                    text = annotatedString,
                                    modifier = Modifier
                                        .align(Bottom)
                                        .padding(end = 8.dp, top = 3.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_business_expand),
                                    modifier = Modifier
                                        .size(16.dp)
                                        .align(Bottom),
                                    contentDescription = ""
                                )

                            }
                            Row() {
                                Text(
                                    text = balance.value,
                                    modifier = Modifier.align(Top),
                                    style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )

                                Text(
                                    text = stringResource(R.string.text_currency_type),
                                    modifier = Modifier
                                        .padding(end = 22.dp)
                                        .padding(3.dp)
                                        .align(Bottom),
                                    style = TextStyle(fontSize = 24.sp),
                                    color = Color.White
                                )
                            }

                        }
                    }

                }

            }

            Column(
                modifier = Modifier
                    .weight(0.75f)
                    .padding(horizontal = 5.dp)
            ) {
                CardMenuContent()

                TabLayoutMenu(navController)

            }


        }



    }



    SelectCompanyBottomSheet(selectCompanyState)

    DropDownPopup(balancePopup, density, touchPoint, screenHeightDp) {
        customerBalanceType.value = it
        when(it){
            Balance.BLOCKED -> {
                balance.value = customerBalance[0].BLOCK_BALANCE
            }
            Balance.FREE_BALANCE -> {
                balance.value = customerBalance[0].AVAIL_BALANCE
            }
            Balance.BANK_EXECUTION -> {
                balance.value = customerBalance[0].WAITING_BALANCE
            }
            Balance.AFTER_EXECUTION -> {
                balance.value = customerBalance[0].AFTERAPPROVE_BALANCE
            }
            Balance.BALANCE -> {
                balance.value = customerBalance[0].BALANCE
            }

        }
    }



    if (customerBalance.isNotEmpty() && customerBalance != null) {
        setDefaultBalanceValue(customerBalanceType, balance, customerBalance)
    }



    //handle API Responce
    accountBalance?.let {
        when (it) {
            is DataState.Loading -> {

            }

            is DataState.Error -> {
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                try {

                    val data = it.data as GetCustomerBalance

                    data.forEach { balance ->
                        customerBalance.add(balance)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }


    //handle API Responce
    recentOps?.let {
        when (it) {
            is DataState.Loading -> {

            }

            is DataState.Error -> {
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                try {
                    val data = it.data as GetRecentOps

                    data.forEach{
//                        dates.apply {
//                            clear()
//                            add(it.trn_date)
//                        }

                        recentData.apply {
                            clear()
                            add(it)
                        }
                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }


}
@Composable
fun DateHeader(date: String) {
    Text(
        text = date,
        style = Typography().body2.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    )
}

@Composable
private fun CardsItem(data: GetRecentOpsItem) {

    Card(
        modifier = Modifier
            .padding(vertical = 5.sdp, horizontal = 5.sdp)
            .fillMaxWidth()
            .clickable {

            },
        elevation = 1.dp,
        backgroundColor = Color(0xFFE7EEFC),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 5.sdp),

            ) {


            Column(
                Modifier.fillMaxWidth()
            ) {

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        "PHARMASCOPE LIMITED",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF223142),
                        ),
                        modifier = Modifier.padding(vertical = 5.sdp)
                    )


                    Text(
                        "${data.trn_date}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF203657),
                            textAlign = TextAlign.Right,
                        ),
                        modifier = Modifier.padding(vertical = 5.sdp)
                    )
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        "LIABILITY COMPANY",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF223142),
                        ),

                        )

                    Text(
                        "${data.trn_time}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.1.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF859DB5),
                        ),

                        )
                }


            }

        }


    }

}

fun setDefaultBalanceValue(
    customerBalanceType: MutableState<Balance>,
    balance: MutableState<String>,
    customerBalance: SnapshotStateList<GetCustomerBalanceItem>
) {
    customerBalance?.let {

        when (customerBalanceType.value) {
            Balance.BLOCKED -> {
                balance.value = customerBalance[0].BLOCK_BALANCE
            }

            Balance.FREE_BALANCE -> {
                balance.value = customerBalance[0].AVAIL_BALANCE
            }

            Balance.BANK_EXECUTION -> {
                balance.value = customerBalance[0].WAITING_BALANCE
            }

            Balance.AFTER_EXECUTION -> {
                balance.value = customerBalance[0].AFTERAPPROVE_BALANCE
            }

            Balance.BALANCE -> {
                balance.value = customerBalance[0].BALANCE
            }

        }
    }
}

@Composable
fun DropDownPopup(
    expanded: MutableState<Boolean>,
    density: Density,
    touchPoint: Offset,
    screenHeightDp: Dp,
    selectedString: (type: Balance) -> Unit
) {
    val (xDp, yDp) = with(density) {
        (touchPoint.x.toDp()) to (touchPoint.y.toDp())
    }
    DropdownMenu(
        expanded = expanded.value,
        offset = DpOffset(xDp, -screenHeightDp + yDp + 150.dp),
        onDismissRequest = { expanded.value = false }
    ) {

        DropdownMenuItem(
            content = { Text(stringResource(R.string.balance)) },
            onClick = {
                selectedString(Balance.BALANCE)
                expanded.value = false
            }
        )
        Divider()

        DropdownMenuItem(
            content = { Text(stringResource(R.string.blocked)) },
            onClick = {
                selectedString(Balance.BLOCKED)
                expanded.value = false
            }
        )
        Divider()
        DropdownMenuItem(
            content = { Text(stringResource(R.string.free_balance)) },
            onClick = {
                selectedString(Balance.FREE_BALANCE)
                expanded.value = false
            }
        )
        Divider()
        DropdownMenuItem(
            content = { Text(stringResource(R.string.bank_execution)) },
            onClick = { selectedString(Balance.BANK_EXECUTION)
                expanded.value = false
            }
        )
        Divider()
        DropdownMenuItem(
            content = { Text(stringResource(R.string.after_execution)) },
            onClick = {
                selectedString(Balance.AFTER_EXECUTION)
                expanded.value = false
            }
        )
    }
}

enum class Balance{
    BALANCE,
    BLOCKED,
    FREE_BALANCE,
    BANK_EXECUTION,
    AFTER_EXECUTION
}



@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MenuScreen(rememberNavController())
}