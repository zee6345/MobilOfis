package com.app.home.main.account.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.app.home.R
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetAccountsItem
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.viewmodel.HomeViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun Blockages(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val homeData by rememberUpdatedState(viewModel.accountsData.collectAsState())
    val context = LocalContext.current

    val str = MainApp.session[Keys.KEY_MAIN_INFO]
    val data = Converter.fromJson(str!!, GetAccountsItem::class.java)

    val blockNumber = remember { mutableStateOf("") }
    val blockedAmount = remember { mutableStateOf("") }
    val blockDate = remember { mutableStateOf("") }
    val blockReason = remember { mutableStateOf("") }

    val isLoading = remember { mutableStateOf(false) }
    val isEmpty = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.getAccountBlockByIBAN(
            "",
            data.CUSTOMER_NO,
            data.IBAN
        )
    }

    if (isEmpty.value){

    } else{

    }

//    Column(
//        modifier = Modifier.fillMaxSize()
//            .background(color = Color.Transparent),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        if (isLoading.value){
//
//            Box(
//                contentAlignment= Alignment.CenterStart,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//                    .padding(horizontal = 30.dp, vertical = 10.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//
//                    ){
//
//                    CircularProgressIndicator()
//
//                    androidx.compose.material3.Text("Please wait...",
//                        Modifier.padding(start = 20.sdp))
//                }
//
//            }
//
//        } else{
//            Text(text = "Nothing found!")
//        }
//
//    }

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
//                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.block_number), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF859DB5),

                            )
                    )

                    Text(
                        text = blockNumber.value, style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),

                            )
                    )
                }
            }


            Row(
                Modifier
                    .fillMaxWidth()
//                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.blocked_amount), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF859DB5),

                            )
                    )

                    Text(
                        text = blockedAmount.value, style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),

                            )
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
//                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.block_date), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF859DB5),

                            )
                    )

                    Text(
                        text = blockDate.value, style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),

                            )
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
//                    .dashedBorder(3.dp, Color(0xFFE7EEFC))
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.reason_for_blocking), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF859DB5),

                            )
                    )

                    Text(
                        text = blockReason.value, style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = Color(0xFF223142),

                            )
                    )
                }
            }


        }
    }


    homeData.value?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

            }
        }
    }

}

@Preview
@Composable
private fun PreviewBloackages(){
    Blockages(navController = rememberNavController())
}
