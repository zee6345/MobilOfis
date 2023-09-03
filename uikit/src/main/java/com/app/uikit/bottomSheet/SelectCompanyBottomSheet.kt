@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.uikit.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
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
import com.app.network.helper.Session
import com.app.network.models.responseModels.AsanCustomer
import com.app.network.models.responseModels.Customers
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder

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
            text = AnnotatedString(text = stringResource(R.string.show_status_bottomsheet)),
            onClick = {
                selectCompanyState.value = !selectCompanyState.value
            }

        )
    }

}

@Composable
fun SelectCompanyBottomSheet(
    selectCompanyState: MutableState<Boolean>,
    customers: Customers,
    onItemClick: (companyName: Int) -> Unit
) {
    val companyList = customers.asanCustomerList

    if (selectCompanyState.value)
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = { selectCompanyState.value = false },
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        ) {

            Column(
                modifier = Modifier.padding( vertical = 10.dp)
            ) {

                Text(
                    text = stringResource(R.string.select_company),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF223142),
                        textAlign = TextAlign.Center,
                    )
                )

                if (companyList != null) {
                    LazyColumn() {

                        item {
                            customers.asanCustomerList.forEachIndexed { index, asanCustomer ->
                                rowItem(asanCustomer) { customerId ->
                                    onItemClick(customerId)
                                    selectCompanyState.value = false
                                }
                            }
                        }


//                        items(items = customers.asanCustomerList, itemContent = {
//                            rowItem(it) { customerId ->
//                                onItemClick(customerId)
//                                selectCompanyState.value = false
//                            }
//                        })

                    }
                }
            }
        }
}

@Composable
private fun rowItem(asanCustomer: AsanCustomer, onItemClick: (companyName: Int) -> Unit) {

    val context = LocalContext.current
    val selectedCustomer = Session(context)["customer"]

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .dashedBorder(2.dp, Color(0x99C9CACC))
            .clickable {
                onItemClick(asanCustomer.customerNo.toInt())
            }
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${asanCustomer.name}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                    fontWeight = if (asanCustomer.customerNo == selectedCustomer) FontWeight(600) else FontWeight(300),
                    color = if (asanCustomer.name == selectedCustomer) colorResource(R.color.background_card_blue) else colorResource(
                        R.color.background_card_blue
                    ).copy(0.5f),
                )
            )
        }

    }

}

@Preview
@Composable
private fun display() {
    SelectCompanyBottomSheet()
}