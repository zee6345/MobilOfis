package com.app.home.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.home.main.recents.recentToDetails
import com.app.home.main.recents.recentTransactions
import com.app.home.main.subviews.TabLayoutMenu
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.errorResponse.ErrorState
import com.app.network.models.requestModels.ChangeCompanyName
import com.app.network.models.responseModels.GetCustomerBalance
import com.app.network.models.responseModels.GetCustomerBalanceItem
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetRecentOpsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponseItem
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.transfers.headerFilters
import com.app.uikit.bottomSheet.SelectCompanyBottomSheet
import com.app.uikit.dialogs.ShowProgressDialog
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


private const val TAG = "MenuScreen"


val recentDetail = mutableStateOf<GetRecentOpsItem?>(null)


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val selectCompanyState = remember { mutableStateOf(false) }
    val showBalance = remember { mutableStateOf(false) }
    val balancePopup = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(false) }
    val customerBalanceType = remember { mutableStateOf("Balance") }
    val balance = remember { mutableStateOf("") }
    val customerName = remember { mutableStateOf("") }


    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val customerBalance = remember { mutableStateListOf<GetCustomerBalanceItem>() }
    val transferHeaderList = remember { mutableListOf<TransferCountSummaryResponseItem>() }
    val recentData = remember { mutableListOf<GetRecentOpsItem>() }

    val accountBalance by viewModel.accountBalance.collectAsState()
    val recentOps by viewModel.recentOps.collectAsState()
    val setCustomerName by viewModel.setCustomerName.collectAsState()
    val transferCountSummery by viewModel.getTransferCountSummary.collectAsState()


    //set initial dates
    val currentDate = System.currentTimeMillis()
    val formattedDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)

    val startDateSelected = remember { mutableStateOf(formattedDate) }
    val endDateSelected = remember { mutableStateOf(formattedDate) }

    val coroutine = rememberCoroutineScope()


    val context = LocalContext.current
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    //default name
    customerName.value = userDetails.customerName


    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getTransferCountSummary(startDateSelected.value, endDateSelected.value)
            viewModel.getBalance(userDetails.customerNo)
            viewModel.getRecentOps(userDetails.customerNo)
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = if (isEmpty.value) 0.sdp else 45.sdp,
        modifier = Modifier.padding(bottom = 40.sdp),
        sheetShape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
        sheetContent = {

            Column(Modifier.fillMaxWidth()) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        if (scaffoldState.bottomSheetState.isExpanded) painterResource(id = R.drawable.ic_option_down) else painterResource(
                            id = R.drawable.ic_options_up
                        ),
                        modifier = Modifier
                            .size(width = 24.sdp, height = 24.sdp)
                            .clickable {
                                coroutine.launch {
                                    if (scaffoldState.bottomSheetState.isExpanded) {
                                        scaffoldState.bottomSheetState.collapse()
                                    } else {
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            },
                        tint = colorResource(R.color.background_card_blue),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.sdp, bottom = 10.sdp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val sumDR = recentData?.filter { it.debit_credit_flag == "DR" }
                        ?.sumByDouble { it.amount.toDoubleOrNull() ?: 0.0 }

                    val sumCR = recentData?.filter { it.debit_credit_flag == "CR" }
                        ?.sumByDouble { it.amount.toDoubleOrNull() ?: 0.0 }

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
                            text = "${sumDR}",
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
                            text = "-${sumCR}",
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
                    val selectedBoxIndex = remember { mutableStateOf(0) }

                    Row() {

                        Card(
                            modifier = Modifier
                                .padding(6.dp),
                            elevation = 8.sdp,
                        ) {
                            Box(modifier = Modifier
                                .background(
                                    if (selectedBoxIndex.value == 0) Color(0xFFE7EEFC) else Color.White,

                                    )
                                .clickable { selectedBoxIndex.value = 0 }) {
                                Text(
                                    text = "All",
                                    modifier = Modifier.padding(6.dp),
                                    style = TextStyle(
                                        if (selectedBoxIndex.value == 0) Color(0xFF015CD1) else Color(
                                            0xFF223142
                                        ),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .padding(6.dp),
                            elevation = 8.sdp,
                        ) {
                            Box(modifier = Modifier
                                .background(
                                    if (selectedBoxIndex.value == 1) Color(0xFFE7EEFC) else Color.White,

                                    )
                                .clickable { selectedBoxIndex.value = 1 }) {
                                Text(
                                    text = "Income",
                                    modifier = Modifier.padding(6.dp),
                                    style = TextStyle(
                                        if (selectedBoxIndex.value == 1) Color(0xFF015CD1) else Color(
                                            0xFF223142
                                        ),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }


                        Card(
                            modifier = Modifier
                                .padding(6.dp),
                            elevation = 8.sdp,
                        ) {
                            Box(modifier = Modifier
                                .background(
                                    if (selectedBoxIndex.value == 2) Color(0xFFE7EEFC) else Color.White
                                )
                                .clickable { selectedBoxIndex.value = 2 }) {
                                Text(
                                    text = "Expenditure",
                                    modifier = Modifier.padding(6.dp),
                                    style = TextStyle(
                                        if (selectedBoxIndex.value == 2) Color(0xFF015CD1) else Color(
                                            0xFF223142
                                        ),
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }

                    }

                    Box(
                        Modifier.clickable {
                            navController.navigate(recentTransactions)
                        }
                    ) {
                        Text(
                            text = "More"
                        )
                    }

                    Box(
                        Modifier.clickable {
                            coroutine.launch {
                                viewModel.getRecentOps(userDetails.customerNo)
                            }
                        }
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_transfers),
                            tint = colorResource(R.color.background_card_blue),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 10.sdp)
                        )
                    }

                }


                LazyColumn {
                    val groupedItems = recentData?.groupBy { it.trn_date }

                    groupedItems?.forEach { (dateStr, itemList) ->

                        item {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(4.sdp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = dateStr)
                            }

                            itemList.forEach { ops ->
                                CardsItem(ops, navController)
                            }
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
                                .padding(start = 22.dp, end = 22.dp)

                        ) {


                            Row(
                                modifier = Modifier
                                    .weight(0.6f)
                                    .clickable {
                                        coroutine.launch {
                                            selectCompanyState.value = true
                                        }
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_business_icon),
                                    modifier = Modifier
                                        .size(28.dp)
                                        .align(Top)
                                        .weight(0.5f),
                                    contentDescription = "",
                                    tint = Color.White
                                )

                                Text(
                                    text = customerName.value,
                                    style = TextStyle(color = Color.White, fontSize = 14.sp),
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                        .weight(1f),
                                    maxLines = 2,
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.ic_business_expand),
                                    modifier = Modifier
                                        .size(16.dp)
                                        .align(Top)
                                        .weight(0.5f),
                                    contentDescription = ""
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.4f)
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

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 22.sdp),
                        ) {
                            Text(
                                text = "${userDetails.userName}",
                                style = TextStyle(
                                    color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp
                                ),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
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
                            Modifier.padding(horizontal = 22.dp),
                            verticalArrangement = Arrangement.Center

                        ) {
                            val annotatedString = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        Color.White.copy(0.5f),
                                        fontSize = 14.sp
                                    )
                                ) {
                                    append(stringResource(R.string.total_accounts))
                                }
                                withStyle(style = SpanStyle(Color.White, fontSize = 14.sp)) {
                                    append(customerBalanceType.value)
                                }
                            }

                            Row() {
                                Text(
                                    text = annotatedString,
                                    modifier = Modifier
                                        .align(Bottom)
                                        .padding(end = 8.dp, top = 3.dp)
                                )
                                Box(
                                    Modifier.align(Bottom)
                                ) {

                                    Image(
                                        painter = painterResource(id = R.drawable.ic_business_expand),
                                        modifier = Modifier
                                            .size(16.dp)
                                            .clickable {
                                                balancePopup.value = !balancePopup.value
                                            },
                                        contentDescription = ""
                                    )

                                    if (customerBalance.isNotEmpty()) {
                                        DropDownPopup(expanded = balancePopup) {
                                            when (it) {
                                                Balance.BLOCKED -> {
                                                    balance.value = customerBalance[0].BLOCK_BALANCE
                                                    customerBalanceType.value = "Blocked"
                                                }

                                                Balance.FREE_BALANCE -> {
                                                    balance.value = customerBalance[0].AVAIL_BALANCE
                                                    customerBalanceType.value = "Free Balance"
                                                }

                                                Balance.BANK_EXECUTION -> {
                                                    balance.value =
                                                        customerBalance[0].WAITING_BALANCE
                                                    customerBalanceType.value = "Bank Execution"
                                                }

                                                Balance.AFTER_EXECUTION -> {
                                                    balance.value =
                                                        customerBalance[0].AFTERAPPROVE_BALANCE
                                                    customerBalanceType.value = "After Execution"
                                                }

                                                Balance.BALANCE -> {
                                                    balance.value = customerBalance[0].BALANCE
                                                    customerBalanceType.value = "Balance"
                                                }

                                            }
                                        }
                                    }


                                }


                            }


                            Row() {
                                Text(
                                    text = if (showBalance.value) "****" else balance.value,
                                    modifier = Modifier.align(Top),
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Color.White
                                )

                                if (!showBalance.value) {
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

            }

            Column(
                modifier = Modifier
                    .weight(0.75f)
                    .padding(horizontal = 10.dp)
            ) {

                Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

                headerFilters(transferHeaderList) {

                }

                TabLayoutMenu(navController)

            }


        }

    }

    SelectCompanyBottomSheet(selectCompanyState, userDetails.customers) {
        coroutine.launch {
            viewModel.setCustomerName(ChangeCompanyName(it))
            viewModel.getRecentOps(userDetails.customerNo)
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
//                ErrorState(context = context, it.errorMessage).handleError()
            }

            is DataState.Success -> {
                try {

                    val data = it.data as GetCustomerBalance

                    if (data.isNotEmpty()) {
                        customerBalance?.apply {
                            clear()
                            addAll(data)
                        }
                    } else {

                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

    setCustomerName?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
//                ErrorState(context = context, it.errorMessage).handleError()
            }

            is DataState.Success -> {
                isLoading.value = false


                try {

                    val data = it.data as LoginVerifyResponse
                    data?.apply {
                        val str = Converter.toJson(this)
                        viewModel.session.put(Keys.KEY_USER_DETAILS, str)

                        customerName.value = this.customerName

                        viewModel.session.put("customer", this.customerName)

                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

    transferCountSummery?.let {
        when (it) {
            is DataState.Loading -> {}

            is DataState.Error -> {}

            is DataState.Success -> {
                val data = it.data as TransferCountSummaryResponse

                if (data.isNotEmpty()) {
                    transferHeaderList.apply {
                        clear()
                        addAll(data)
                    }
                }

            }
        }
    }

    //handle API Responce
    recentOps?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

                //on error hide sheet
                isEmpty.value = true

//                ErrorState(context = context, it.errorMessage).handleError()
            }

            is DataState.Success -> {
                isLoading.value = false

                try {
                    val data = it.data as GetRecentOps

                    if (data.isNotEmpty()) {
                        recentData?.apply {
                            clear()
                            addAll(data)
                        }
                        isEmpty.value = false
                    } else {
                        isEmpty.value = true
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

    }



    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }

}


@Composable
private fun CardsItem(data: GetRecentOpsItem, navController: NavController) {

    var isCredit by remember { mutableStateOf(false) }

    isCredit = data.debit_credit_flag != "DR"

    Card(
        modifier = Modifier
            .padding(vertical = 4.sdp, horizontal = 8.sdp)
            .fillMaxWidth()
            .clickable {
                recentDetail.value = data
                navController.navigate(recentToDetails)
            },
        elevation = 1.dp,
        backgroundColor = Color(0xFFF3F7FA),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 5.sdp),
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
                    if (isCredit) "-${data.amount} ₼" else "${data.amount} ₼",
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
}

fun setDefaultBalanceValue(
    customerBalanceType: MutableState<String>,
    balance: MutableState<String>,
    customerBalance: SnapshotStateList<GetCustomerBalanceItem>
) {
    customerBalance?.let {

        when (customerBalanceType.value) {
            "Blocked" -> {
                balance.value = customerBalance[0].BLOCK_BALANCE
            }

            "Free Balance" -> {
                balance.value = customerBalance[0].AVAIL_BALANCE
            }

            "Bank Execution" -> {
                balance.value = customerBalance[0].WAITING_BALANCE
            }

            "After Execution" -> {
                balance.value = customerBalance[0].AFTERAPPROVE_BALANCE
            }

            "Balance" -> {
                balance.value = customerBalance[0].BALANCE
            }

        }
    }
}

@Composable
fun DropDownPopup(
    expanded: MutableState<Boolean>,
    selectedString: (type: Balance) -> Unit
) {

    DropdownMenu(
        expanded = expanded.value,
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
            onClick = {
                selectedString(Balance.BANK_EXECUTION)
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

enum class Balance {
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