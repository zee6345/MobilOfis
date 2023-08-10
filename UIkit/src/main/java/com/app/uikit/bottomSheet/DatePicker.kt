package com.app.uikit.bottomSheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun DatePicker(
    onDateSelected: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    // Second, you simply have to add the DatePicker component to your layout.
    androidx.compose.material3.DatePicker(state = datePickerState)
    val selectedDate = datePickerState.selectedDateMillis?.let {
        Instant.ofEpochMilli(it).atOffset(ZoneOffset.UTC)
    }
    val simpleDateTimeFormatter = SimpleDateFormat("dd-MM-yyyy")
    val date = simpleDateTimeFormatter.format(selectedDate)
    onDateSelected(date)

}