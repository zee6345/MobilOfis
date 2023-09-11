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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.app.home.main.account.accountDetailsRoute
import com.app.home.main.isShowBalance
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetAccountsItem
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.viewmodel.HomeViewModel
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.Utils
import com.app.uikit.views.AutoResizedText
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch


@Composable
fun AccountList(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)
    val homeData by rememberUpdatedState(viewModel.accountsData.collectAsState())
    val cardsList = remember { mutableListOf<GetAccountsItem>() }
    val isLoading = remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        //fetch accounts list
        coroutine.launch {
            viewModel.getAccounts(
                userDetails.customerNo
            )
        }

    }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 1.dp, horizontal = 10.dp)
    ) {

        item {
            Spacer(modifier = Modifier.size(width = 1.dp, height = 10.dp))
        }


        cardsList.forEachIndexed { index, getAccountsItem ->
            item {
                AccountListItem(obj = getAccountsItem, navController, viewModel)
            }
        }

        item {
            Spacer(modifier = Modifier.size(width = 1.dp, height = 50.sdp))
        }

    }


    /**
     * handle home response data
     */
    homeData.value?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                val userAccounts = it.data as GetAccounts

                isLoading.value = false

                if (userAccounts.isNotEmpty()) {
                    cardsList.apply {
                        clear()
                        cardsList.addAll(userAccounts)
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
fun AccountListItem(obj: GetAccountsItem, navController: NavController, viewModel: HomeViewModel) {

    var isCardAccount by remember { mutableStateOf(false) }
    val context: Context = LocalContext.current

    val symbol = Utils.formatCurrency(obj.CCY_NAME)

    var title = ""
    if (obj.ACCOUNT_TYPE != null) {
        when (obj.ACCOUNT_TYPE) {
            "CARD-ACCOUNT" -> {
                title = if (obj.NICKNAME != null) {
                    obj.NICKNAME.ifEmpty {
                        "Settlement-card account"
                    }
                } else {
                    "Settlement-card account"
                }

                isCardAccount = true

            }

            "ACCOUNT" -> {
                title = if (obj.NICKNAME != null) {
                    obj.NICKNAME.ifEmpty {
                        "Settlement account"
                    }
                } else {
                    "Settlement account"
                }

                isCardAccount = false
            }
        }
    }

    Card(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.sdp)
            .fillMaxWidth()
            .clickable {
                viewModel.session.put(Keys.KEY_MAIN_INFO, Converter.toJson(obj))
                navController.navigate(accountDetailsRoute)
            },
        elevation = 1.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 14.sp),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )

                Row {


                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(15.dp),
                            )
                            .background(
                                color = Color(0xFFF3F7FA),
                            )
                    ) {
                        Text(
                            text = obj.IBAN,
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                            style = TextStyle(fontSize = 11.sp),
                            color = Color(R.color.grey_text)
                        )
                    }

                    if (isCardAccount) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_card_acc),
                            contentDescription = "",

                            )
                    }

                }

            }

            AutoResizedText(
                text = if (isShowBalance.value) "****" else "${Utils.formatAmountWithSpaces(obj.BALANCE.toDouble())} $symbol",
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF223142),
                    textAlign = TextAlign.Right,
                ),
            )
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun TabPreview() {
    AccountList(rememberNavController())
}