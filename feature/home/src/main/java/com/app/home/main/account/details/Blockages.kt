package com.app.home.main.account.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.home.R
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetAccountBlocks
import com.app.network.models.responseModels.GetAccountBlocksItem
import com.app.network.models.responseModels.GetAccountsItem
import com.app.network.viewmodel.HomeViewModel
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@Composable
fun Blockages(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val accountBlockByIban by viewModel.accountsBlockByIban.collectAsState()
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()

    val str = viewModel.session[Keys.KEY_MAIN_INFO]
    val data = Converter.fromJson(str!!, GetAccountsItem::class.java)


    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(true) }

    val accountBlock = remember { mutableListOf<GetAccountBlocksItem>() }

    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getAccountBlockByIBAN(
                data.CUSTOMER_NO,
                data.IBAN
            )
        }
    }

    if (isEmpty.value) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No blocked account found!")
        }


    } else {

        LazyColumn {

            accountBlock.forEachIndexed { index, getAccountBlocksItem ->
                item {
                    BlockItemView(getAccountBlocksItem)
                }
            }

            item {
                Spacer(modifier = Modifier.size(height = 50.sdp, width = 1.sdp))
            }

        }

    }


    accountBlockByIban?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false


                val data = it.data as GetAccountBlocks

                if (data.isEmpty()) {
                    isEmpty.value = true
                } else {
                    isEmpty.value = false

                    if (accountBlock.isNotEmpty()) {
                        accountBlock.clear()
                    }
                    data.forEach { block ->
                        accountBlock.add(block)
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
private fun BlockItemView(blockItem: GetAccountBlocksItem) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {
        Column {

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.block_number), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = blockItem.blockId.toString(), style = TextStyle(
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
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.blocked_amount), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(blockItem.blockAmount),
                        style = TextStyle(
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
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.block_date), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = blockItem.blockDate, style = TextStyle(
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
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.reason_for_blocking),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = blockItem.description, style = TextStyle(
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


@Preview
@Composable
private fun PreviewBloackages() {
    Blockages(navController = rememberNavController())
}
