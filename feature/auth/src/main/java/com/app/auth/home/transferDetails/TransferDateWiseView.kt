package com.app.auth.home.transferDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

data class Task(val name: String,
                val id:Int,
    val amount:Double,
    val status:String,
    val paymentBy:String,
    val timeSmall:String
    )
data class TaskDate(val date: String, val tasks: List<Task>)

@Composable
fun callTaskViews() {
    val taskDates = remember { DataClassTransfer.taskDates}
    LazyColumn {
        items(taskDates) { taskDate ->
            this@LazyColumn.item{
                Row(
                    modifier = Modifier
                        .padding(5.sdp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = taskDate.date,
                        style = TextStyle(fontSize = 20.ssp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(16.sdp)
                    )
                }
            }
            this@LazyColumn.items(taskDate.tasks) { task ->
                Card(modifier = Modifier
                    .padding(10.sdp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(10.sdp)
                ) {

                    Column {
                        Row( horizontalArrangement = Arrangement.SpaceBetween) {

                        }
                        Row( horizontalArrangement = Arrangement.SpaceBetween) {

                        }
                    }


                }
                Text(
                    text = task.name,
                    modifier = Modifier.padding(start = 32.dp, top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}