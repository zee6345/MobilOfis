package com.app.home.main.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.home.main.account.navigation.accountDetailsRoute
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetAccounts
import com.app.network.data.responseModels.GetAccountsItem
import com.app.network.data.responseModels.LoginVerifyResponse

import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.utils.Message
import com.app.network.viewmodel.HomeViewModel

data class AccountListData(
    val title: String,
    val description: String,
)

@Composable
fun AccountList(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val context = LocalContext.current
    val userDetails = MainApp.session.fetchUserDetails()
    val homeData by rememberUpdatedState(viewModel.accountsData.collectAsState())
    val cardsList = remember { mutableListOf<GetAccountsItem>() }
    val isLoading = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        //fetch accounts list
        viewModel.getAccounts(
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
            contentPadding = PaddingValues(vertical = 1.dp, horizontal = 5.dp)
        ) {

            item {
                Spacer(modifier = Modifier.size(width = 1.dp, height = 10.dp))
            }

            items(items = cardsList, itemContent = {

                AccountListItem(obj = it, navController)

            })
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
                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                val userAccounts = it.data as GetAccounts

                isLoading.value = false

                cardsList.apply {
                    clear()
                    cardsList.addAll(userAccounts)
                }

            }
        }
    }

}

@Composable
fun AccountListItem(obj: GetAccountsItem, navController: NavController) {

    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                MainApp.session.put(Keys.KEY_MAIN_INFO, Converter.toJson(obj))
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
                    text = obj.BRANCH_NAME,
                    style = TextStyle(fontSize = 14.sp),
                    color = Color(R.color.background_card_blue),
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )
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
            }

            Text(
                text = "${obj.BALANCE} ₼",
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 10.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    color = Color(R.color.background_card_blue)
                )
            )
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun TabPreview() {
    AccountList(rememberNavController())
}