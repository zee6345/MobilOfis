package com.app.uikit.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R




@Composable
fun CallTopAlertDialog(
    backgroundColor: Color,
    cornerRadius:Dp,
    title: String,
    message: String,
    onClose: () -> Unit
) {
    AnimatedVisibility(
        visible = true, // Set the visibility state based on your requirement
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            AlertDialog(
                onDismissRequest = onClose,
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.grey_text),
                            modifier = Modifier.padding(end = 16.dp),
                            fontSize = 18.sp,
                            fontFamily = appFontFamily
                        )
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close",
                                tint = colorResource(R.color.grey_text)
                            )
                        }
                    }
                },
                text = {
                    Column() {
                        Text(
                            text = message,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = appFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                },
                buttons = {},
                shape = RoundedCornerShape(cornerRadius),
                backgroundColor = backgroundColor
            )
        }
    }
}



private val appFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.roboto_black,
            weight = FontWeight.W900,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_blackitalic,
            weight = FontWeight.W900,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.roboto_bold,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_regular,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        )
    )
)