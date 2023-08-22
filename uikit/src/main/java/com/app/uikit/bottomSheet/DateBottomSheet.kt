package com.app.uikit.bottomSheet

import android.os.Build
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.models.DateType
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import java.text.SimpleDateFormat
import java.util.Date


lateinit var datePickerEndBottomSheet: MutableState<Boolean>
lateinit var datePickerStartBottomSheet: MutableState<Boolean>

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
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

    DateBottomSheet(showDateBottomSheetSheet) {

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(
    showDateBottomSheet: MutableState<Boolean>,
    onSelectedDate: (DateModel) -> Unit,
) {

    datePickerStartBottomSheet = rememberSaveable { mutableStateOf(false) }
    datePickerEndBottomSheet = rememberSaveable { mutableStateOf(false) }

    val showDatePicker = remember { mutableStateOf(false) }

    val selectedDate = remember { mutableStateOf("") }
    var selectedDateType by remember { mutableStateOf(DateType.TODAY) }
    val currentDate = System.currentTimeMillis()
    val formattedCurrentDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)

    val startDate = remember { mutableStateOf(formattedCurrentDate) }
    val endDate = remember { mutableStateOf(formattedCurrentDate) }


    if (showDateBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showDateBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 12.sdp, topEnd = 12.sdp),
        containerColor = Color(0xFFF3F7FA)

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

            DateTypeMenu({
                selectedDate.value = it
            }, {
                selectedDateType = it
            })


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


                Card(
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable {
                            showDatePicker.value = true
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
                            text = startDate.value,
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
                            tint = Color.Red
                        )

                    }
                }


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

                Card(
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable {
                            showDatePicker.value = true
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
                            text = endDate.value,
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
                            tint = Color.Red
                        )

                    }
                }
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

                        onSelectedDate(
                            DateModel(
                                selectedDate.value,
                                formattedCurrentDate,
                                selectedDateType
                            )
                        )

                    },
                )
            }

            Spacer(modifier = Modifier.size(height = 15.dp, width = 1.dp))
        }
    }


    DatePicker(showDatePicker) {
        startDate.value = it
        showDatePicker.value = false
    }

    DatePicker(showDatePicker) {
        endDate.value = it
        showDatePicker.value = false
    }

}


data class DateModel(
    val startDate: String,
    val endDate: String,
    val dateType: DateType
)

@Composable
fun DateTypeMenu(selectedDate: (String) -> Unit, selectedDateType: (DateType) -> Unit) {

    val selectedBoxIndex = remember { mutableStateOf(0) }
    var dateType by remember { mutableStateOf(DateType.TODAY) }


    Row(
        Modifier.fillMaxWidth()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
                .background(
                    if (selectedBoxIndex.value == 0) Color(0xFF0FBF1B) else Color(
                        0xFFFFFFFF
                    ), shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    selectedBoxIndex.value = 0
                    dateType = DateType.TODAY
                },
        ) {
            androidx.compose.material.Text(
                text = "Today",
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                fontSize = 12.ssp,
                color = if (selectedBoxIndex.value == 0) Color(0xFFFFFFFF) else Color(0xFF223142)
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
                .background(
                    if (selectedBoxIndex.value == 1) Color(0xFF0FBF1B) else Color(
                        0xFFFFFFFF
                    ), shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    selectedBoxIndex.value = 1
                    dateType = DateType.YESTERDAY
                },

            ) {
            androidx.compose.material.Text(
                text = "Yesterday",
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 8.dp
                    ),
                fontSize = 12.ssp,
                color = if (selectedBoxIndex.value == 1) Color(0xFFFFFFFF) else Color(0xFF223142)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
                .background(
                    if (selectedBoxIndex.value == 2) Color(0xFF0FBF1B) else Color(
                        0xFFFFFFFF
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    selectedBoxIndex.value = 2
                    dateType = DateType.WEEK
                },

            ) {
            androidx.compose.material.Text(
                text = "This Week",
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 8.dp
                    ),
                fontSize = 12.ssp,
                color = if (selectedBoxIndex.value == 2) Color(0xFFFFFFFF) else Color(0xFF223142)
            )
        }

    }

    val currentDate = System.currentTimeMillis()
    val finalDate = when (selectedBoxIndex.value) {
        0 -> {
            SimpleDateFormat("dd.MM.yyyy").format(currentDate)
        }

        1 -> {
            val yesterdayDate = Date(currentDate - 24 * 60 * 60 * 1000)
            SimpleDateFormat("dd.MM.yyyy").format(yesterdayDate)
        }

        2 -> {
            val oneWeekLaterDate = Date(currentDate - 7 * 24 * 60 * 60 * 1000)
            SimpleDateFormat("dd.MM.yyyy").format(oneWeekLaterDate)
        }

        else -> {
            ""
        }
    }

    selectedDate(finalDate)
    selectedDateType(dateType)


}
