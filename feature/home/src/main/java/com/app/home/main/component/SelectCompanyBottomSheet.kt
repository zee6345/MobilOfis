@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.home.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.network.models.responseModels.AsanCustomer
import com.app.network.models.responseModels.Customers
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SelectCompanyBottomSheet() {
    val selectCompanyState = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(com.app.home.R.string.show_status_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                selectCompanyState.value = !selectCompanyState.value
            }

        )
    }

//    SelectCompanyBottomSheet(selectCompanyState, null)
}

@Composable
fun SelectCompanyBottomSheet(selectCompanyState: MutableState<Boolean>, customers: Customers, onItemClick:(companyName:Int)->Unit) {

    val companyList = customers.asanCustomerList

    if (selectCompanyState.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { selectCompanyState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {

            Text(
                text = stringResource(com.app.home.R.string.select_company),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = colorResource(R.color.background_card_blue)
            )



            if (companyList != null) {
                LazyColumn() {
                    items(items = customers.asanCustomerList, itemContent = {
                        rowItem(it){ customerId->
                            onItemClick(customerId)
                        }
                    })

                }
            }

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(1.dp, Color(0x99C9CACC))
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 10.dp, vertical = 10.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(
//                        text = stringResource(com.app.home.R.string.text_agency),
//                        textAlign = TextAlign.Start,
//                        fontWeight = FontWeight.Medium,
//                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
//                        color = Color(com.app.home.R.color.background_card_blue),
//                        fontSize = 15.sp
//                    )
//                }
//
//            }
//
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(1.dp, Color(0x99C9CACC))
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 10.dp, vertical = 10.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(
//                        text = stringResource(com.app.home.R.string.value_services_mmc),
//                        textAlign = TextAlign.Start,
//                        fontWeight = FontWeight.Medium,
//                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
//                        color = Color(com.app.home.R.color.background_card_blue),
//                        fontSize = 15.sp
//                    )
//                }
//
//            }
//
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(1.dp, Color(0x99C9CACC))
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 10.dp, vertical = 10.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(
//                        text = stringResource(com.app.home.R.string.saba_mmc),
//                        textAlign = TextAlign.Start,
//                        fontWeight = FontWeight.Medium,
//                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
//                        color = Color(com.app.home.R.color.background_card_blue),
//                        fontSize = 15.sp
//                    )
//                }
//
//            }


        }
    }
}

@Composable
private fun rowItem(asanCustomer: AsanCustomer, onItemClick:(companyName:Int)->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .dashedBorder(1.dp, Color(0x99C9CACC))
            .clickable { 
                onItemClick(asanCustomer.customerNo.toInt())
            }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${asanCustomer.name}",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = colorResource(R.color.background_card_blue),
                maxLines = 1,
                fontSize = 15.sp
            )
        }

    }

}

@Preview
@Composable
private fun display() {
    SelectCompanyBottomSheet()
}