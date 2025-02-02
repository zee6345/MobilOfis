package com.app.uikit.dialogs

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.kaaveh.sdpcompose.sdp

@Composable
fun RoundedCornerToast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT,
    context: Context
) {
    val coroutineScope = rememberCoroutineScope()


//    LaunchedEffect(Unit) {
//        coroutineScope.launch {
//            Toast.makeText(context, text, duration).show()
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.sdp, horizontal = 20.sdp)
            .background(Color.Transparent, RoundedCornerShape(16.dp))
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFDCDE), RoundedCornerShape(16.dp))
                .border(2.dp, Color(0xFFF3646C), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                style = TextStyle(fontSize = 16.sp, color = Color(0xFFF3646C)),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}