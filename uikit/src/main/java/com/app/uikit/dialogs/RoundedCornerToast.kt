package com.app.uikit.dialogs

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun RoundedCornerToast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT,
    context: Context,
    visible: Boolean = true
) {

    AnimatedVisibility(
        visible = visible,
        modifier = Modifier.fillMaxWidth(),
            enter = slideInVertically(initialOffsetY = { -40 }) + expandVertically(expandFrom = Alignment.Top) + scaleIn(transformOrigin = TransformOrigin(0.5f, 0f)) + fadeIn(initialAlpha = 0.3f),
//        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 50.dp, horizontal = 15.dp)
                .background(Color.Transparent, RoundedCornerShape(10.dp))
                .wrapContentSize(Alignment.TopCenter)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFDCDE), RoundedCornerShape(10.dp))
                    .border(2.dp, Color(0xFFF3646C), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = text,
                    style = TextStyle(fontSize = 14.sp, color = Color(0xFFF3646C)),
                    modifier = Modifier.padding(14.dp)
                )
            }


        }

    }

    LaunchedEffect(visible) {
        if (visible) {
            delay(100) // Optional delay before showing the toast
            delay(duration.toLong())
        }
    }
}




