package com.app.uikit.bottomSheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.kaaveh.sdpcompose.sdp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun DatePicker(
    showDatePicker:MutableState<Boolean>,
    onDateSelected: (String)->Unit
) {
//    val datePickerState = rememberDatePickerState(
//        initialSelectedDateMillis = Instant.now().toEpochMilli()
//    )
//
//    // Wrap the DatePicker in a Surface with a white background
//    Box(
//        modifier = Modifier
//            .background(Color.White, shape = RoundedCornerShape(8.sdp))
//            .padding(8.sdp),
//        contentAlignment = Alignment.Center
//    ) {
//        androidx.compose.material3.DatePicker(state = datePickerState)
//    }
//
//    val selectedDate = datePickerState.selectedDateMillis?.let {
//        Instant.ofEpochMilli(it).atOffset(ZoneOffset.UTC)
//    }
//    onDateSelected(selectedDate?.format(DateTimeFormatter.ISO_LOCAL_DATE)!!)

    val calendar = Calendar.getInstance()
    calendar.set(1990, 0, 22) // add year, month (Jan), date
    // set the initial date
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    var selectedDate by remember { mutableStateOf(calendar.timeInMillis) }

    if (showDatePicker.value) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker.value = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker.value = false
                    selectedDate = datePickerState.selectedDateMillis!!

                    val formattedDate = SimpleDateFormat("dd.MM.yyyy").format(selectedDate)
                    onDateSelected(formattedDate)

                    showDatePicker.value = false

                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker.value = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            androidx.compose.material3.DatePicker(
                state = datePickerState
            )
        }
    }

}

