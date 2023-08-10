package com.app.uikit.bottomSheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.data.DataProvider
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


lateinit var datePickerEndBottomSheet: MutableState<Boolean>
lateinit var datePickerStartBottomSheet: MutableState<Boolean>

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet() {
    val showDateBottomSheetSheet = rememberSaveable { mutableStateOf(false) }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(id = R.string.show_account_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                showDateBottomSheetSheet.value = !showDateBottomSheetSheet.value
            }
        )
    }

    DateBottomSheet(showDateBottomSheetSheet, onDateEndSelected = {}, onDateStartSelected = {})

}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(showDateBottomSheet: MutableState<Boolean>,
                    onDateStartSelected: (String) -> Unit,
                    onDateEndSelected:(String) -> Unit) {

    datePickerStartBottomSheet = rememberSaveable { mutableStateOf(false) }
    datePickerEndBottomSheet = rememberSaveable { mutableStateOf(false) }

    if (showDateBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showDateBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 12.sdp, topEnd = 12.sdp),
        containerColor = Color.White

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Text(
                text = stringResource(R.string.duration),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142),


                )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            DateTypeMenu()
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            Text(
                text = stringResource(id = R.string.duration),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.sdp),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                color = Color(R.color.grey_text),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemsCardRow(stringResource(R.string._18_11_2021), 1)
                Box(
                    modifier = Modifier
                        .width(10.sdp)
                        .height(1.sdp)
                        .background(
                            color = Color(R.color.border_grey),
                            shape = RoundedCornerShape(size = 10.sdp)
                        )
                        .align(Alignment.CenterVertically)
                )
                ItemsCardRow(stringResource(R.string._14_11_2021), 2)
            }

            Spacer(modifier = Modifier.size(height = 20.dp, width = 1.dp))

            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.cancel)),
                    style = TextStyle(
                        color = Color(0xFF203657),
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 14.ssp
                    ),
                    onClick = {
                        showDateBottomSheet.value = false
                    },
                )

                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.apply)),
                    style = TextStyle(
                        color = Color(0xFF203657),
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 14.ssp
                    ),
                    onClick = {
                        showDateBottomSheet.value = false
                    },

                    )
            }


            Spacer(modifier = Modifier.size(height = 15.dp, width = 1.dp))
        }
    }

    if (datePickerStartBottomSheet.value) {
        DatePicker() {
            onDateStartSelected(it)
        }
    }
    if (datePickerEndBottomSheet.value) {
        DatePicker() {
            onDateEndSelected(it)
        }
    }
}


data class DateMenuModel(
    val title: String,
    val textColor: Color,
    val color: Color
)

@Composable
fun DateTypeMenu() {
    val menu = remember { DataProvider.DateMenu }
    LazyRow(
    ) {
        items(items = menu, itemContent = {
            DateMenuItem(menuItem = it)
        })
    }
}

@Composable
fun DateMenuItem(menuItem: DateMenuModel) {
    Card(
        modifier = Modifier.padding(horizontal = 4.dp),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = menuItem.color
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            androidx.compose.material.Text(
                text = menuItem.title,
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 8.dp
                    ),
                fontSize = 12.ssp,
                color = menuItem.textColor
            )
        }
    }
}

@Composable
fun ItemsCardRow(text: String, condition: Int) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .clickable {
                if (condition == 1) {
                    datePickerStartBottomSheet.value = !datePickerStartBottomSheet.value
                } else {
                    datePickerEndBottomSheet.value = !datePickerStartBottomSheet.value
                }
            },
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "calender",
                tint = colorResource(R.color.blue),
                modifier = Modifier
                    .padding(horizontal = 5.sdp)
                    .align(Alignment.CenterVertically)
                    .size(width = 18.dp, height = 18.dp)
            )
            androidx.compose.material.Text(
                text = text,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 12.sp,
                color = Color(0xFF223142)
            )
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = "calender",
                modifier = Modifier
                    .padding(horizontal = 5.sdp)
                    .align(Alignment.CenterVertically),
                tint = Color.Red,

                )

        }
    }
}
