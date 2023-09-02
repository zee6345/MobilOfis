package com.app.uikit.bottomSheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar

@ExperimentalMaterial3Api
@Composable
fun StartDatePicker(
    showDatePicker: MutableState<Boolean>,
    onDateSelected: (String) -> Unit
) {

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


@ExperimentalMaterial3Api
@Composable
fun EndDatePicker(
    showDatePicker: MutableState<Boolean>,
    onDateSelected: (String) -> Unit
) {

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

