package com.app.uikit.bottomSheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import com.app.uikit.data.DataProvider
import com.app.uikit.models.DateType
import com.app.uikit.models.DurationDateModel
import com.app.uikit.views.transfersDate
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


lateinit var datePickerEndBottomSheet: MutableState<Boolean>
lateinit var datePickerStartBottomSheet: MutableState<Boolean>

private val currentDate = System.currentTimeMillis()
private val formattedCurrentDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
private val startDate = mutableStateOf(formattedCurrentDate)
private val endDate = mutableStateOf(formattedCurrentDate)

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(
    showDateBottomSheet: MutableState<Boolean>,
//    onSelectedType: (DurationDateModel) -> Unit,
    onSelectedDate: (DateModel) -> Unit,
) {

    datePickerStartBottomSheet = rememberSaveable { mutableStateOf(false) }
    datePickerEndBottomSheet = rememberSaveable { mutableStateOf(false) }

    val showStartDatePicker = remember { mutableStateOf(false) }
    val showEndDatePicker = remember { mutableStateOf(false) }
    var selectedDateType by remember { mutableStateOf(DurationDateModel("Today", DateType.TODAY)) }


    if (showDateBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showDateBottomSheet.value = false },
        shape = RoundedCornerShape(10.dp),
        containerColor = Color.White

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Text(
                text = stringResource(R.string.duration),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF223142),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            DateTypeMenu {
                selectedDateType = it
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Text(
                text = stringResource(id = R.string.duration),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.1.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF859DB5),
                )
            )

            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                Box(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color(0xFFE7EEFC),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                        .clickable {
                            showStartDatePicker.value = true
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 5.dp)
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
                            color = colorResource(R.color.border_grey),
                            shape = RoundedCornerShape(size = 10.sdp)
                        )
                        .align(Alignment.CenterVertically)
                )

                Box(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color(0xFFE7EEFC),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                        .clickable {
                            showEndDatePicker.value = true
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 5.dp)
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
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
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

                        startDate.value =
                            handleDateWithFilter(selectedDateType!!.dateType).startDate
                        endDate.value = handleDateWithFilter(selectedDateType!!.dateType).endDate

                        onSelectedDate(
                            handleDateWithFilter(selectedDateType!!.dateType)
                        )

                        //for filter title
                        transfersDate.value = handleDateWithFilter(selectedDateType!!.dateType)

                    },
                )
            }

            Spacer(modifier = Modifier.size(height = 15.dp, width = 1.dp))
        }
    }


    StartDatePicker(showStartDatePicker) {
        startDate.value = it
        showStartDatePicker.value = false

        selectedDateType = DurationDateModel("", DateType.CUSTOM)
    }

    EndDatePicker(showEndDatePicker) {
        endDate.value = it
        showEndDatePicker.value = false

        selectedDateType = DurationDateModel("", DateType.CUSTOM)
    }
}


data class DateModel(
    val startDate: String,
    val endDate: String,
    val type: DateType?
)

@Composable
private fun DateTypeMenu(selectedDateType: (DurationDateModel) -> Unit) {

    var isSelected by remember { mutableStateOf(DurationDateModel("Today", DateType.TODAY)) }
    val dateList = remember { DataProvider.filterDateList }

    LazyRow {
        dateList.forEachIndexed { index, durationDateModel ->

            item {
                Box(
                    modifier = Modifier
                        .border(
                            width = if (isSelected == durationDateModel) 0.dp else 2.dp,
                            color = Color(0xFFE7EEFC),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .background(
                            color = if (isSelected == durationDateModel) Color(0xFF0FBF1B) else Color(
                                0xFFFFFFFF
                            ),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                        .clickable {
                            selectedDateType(durationDateModel)

                            isSelected = durationDateModel
                        }
                ) {
                    Text(
                        text = durationDateModel.title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight(400),
                            color = if (isSelected == durationDateModel) Color(0xFFFFFFFF) else Color(
                                0xFF223142
                            ),
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
            }

        }
    }
}

fun handleDateWithFilter(selectedDateType: DateType?): DateModel {
    val currentDate = System.currentTimeMillis()
    var filterStartDate = ""
    var filterEndDate = ""

    when (selectedDateType) {
        DateType.TODAY -> {
            filterStartDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
            filterEndDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
        }

        DateType.YESTERDAY -> {
            val yesterdayDate = Date(currentDate - 24 * 60 * 60 * 1000)
            filterStartDate = SimpleDateFormat("dd.MM.yyyy").format(yesterdayDate)
            filterEndDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
        }

        DateType.THIS_WEEK -> {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = currentDate

            val currentWeekEndDate = calendar.time

            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            val currentWeekStartDate = calendar.time

            filterStartDate = SimpleDateFormat("dd.MM.yyyy").format(currentWeekStartDate)
            filterEndDate = SimpleDateFormat("dd.MM.yyyy").format(currentWeekEndDate)
        }

        DateType.LAST_WEEK -> {
            val calendar = Calendar.getInstance()

            // Set the calendar to the current date
            val currentDate = System.currentTimeMillis()
            calendar.timeInMillis = currentDate

            // Find the most recent Sunday before the current date
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            calendar.add(Calendar.DAY_OF_MONTH, -7)
            val lastSunday = calendar.time

            // Calculate the start of the last week (last Monday)
            calendar.add(Calendar.DAY_OF_WEEK, -6)
            val lastMonday = calendar.time

            filterStartDate = SimpleDateFormat("dd.MM.yyyy").format(lastMonday)
            filterEndDate = SimpleDateFormat("dd.MM.yyyy").format(lastSunday)
        }

        DateType.THIS_MONTH -> {
            val calendar = Calendar.getInstance()

            calendar.set(Calendar.DAY_OF_MONTH, 1)
            val currentMonthStartDate = calendar.time

            filterStartDate = SimpleDateFormat("dd.MM.yyyy").format(currentMonthStartDate)
            filterEndDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
        }

        DateType.LAST_MONTH -> {

            val calendar = Calendar.getInstance()

            // Set the calendar to the first day of the current month
            calendar.set(Calendar.DAY_OF_MONTH, 1)

            // Move the calendar back one month to get the first day of last month
            calendar.add(Calendar.MONTH, -1)
            val lastMonthStartDate = calendar.time

            // Move the calendar to the last day of last month
            calendar.add(Calendar.MONTH, 1)
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            val lastMonthEndDate = calendar.time

            filterStartDate = SimpleDateFormat("dd.MM.yyyy").format(lastMonthStartDate)
            filterEndDate = SimpleDateFormat("dd.MM.yyyy").format(lastMonthEndDate)
        }

        DateType.CUSTOM -> {
            filterStartDate = startDate.value
            filterEndDate = endDate.value
        }

        else -> {
            filterStartDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
            filterEndDate = SimpleDateFormat("dd.MM.yyyy").format(currentDate)
        }
    }

    return DateModel(filterStartDate, filterEndDate, selectedDateType!!)
}
