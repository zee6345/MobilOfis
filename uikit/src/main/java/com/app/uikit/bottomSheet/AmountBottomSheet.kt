package com.app.uikit.bottomSheet

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.models.AmountModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun AmountBottomSheet() {
    val showAmountBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    showAmountBottomSheet.value = !showAmountBottomSheet.value
                },
            text = stringResource(id = R.string.show_account_bottomsheet),
        )
    }

    AmountBottomSheet(showAmountBottomSheet){

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountBottomSheet(showAmountBottomSheet: MutableState<Boolean>, onValueChanged: (AmountModel) -> Unit) {
    val minAmountState = remember { mutableStateOf("1000") }
    val maxAmountState = remember { mutableStateOf("1000000") }

    if (showAmountBottomSheet.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { showAmountBottomSheet.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
    ) {
        Column(
            modifier = Modifier
                .padding(3.dp)
                .padding(horizontal = 10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.amount),
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    color = Color(0xFF223142)
                )
            }

            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.sdp)
                        .background(Color.White) // Background color of the box
                        .border(
                            width = 1.dp,          // Outline width
                            color = Color.Black.copy(0.4f),   // Outline color
                            shape = RoundedCornerShape(8.dp) // Rounded corners
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = minAmountState.value,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f)
                        .width(2.sdp)
                        .height(2.sdp)
                        .background(
                            color = colorResource(R.color.border_grey),
                            shape = RoundedCornerShape(size = 10.sdp)
                        )
                        .align(Alignment.CenterVertically)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.sdp)
                        .background(Color.White) // Background color of the box
                        .border(
                            width = 1.dp,          // Outline width
                            color = Color.Black.copy(0.4f),   // Outline color
                            shape = RoundedCornerShape(8.dp) // Rounded corners
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = maxAmountState.value,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(height = 20.dp, width = 1.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Text(text = "0",
                    style = TextStyle(
                        colorResource(id = R.color.grey_text)
                    )
                )
                Text(text = "1 000 000",
                    style = TextStyle(
                        colorResource(id = R.color.grey_text)
                    )
                )

            }

            Spacer(modifier = Modifier.size(height = 20.dp, width = 1.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                RangeSeekBar() {
                    minAmountState.value = it.startAmount.toString()
                    maxAmountState.value = it.endAmount.toString()
                }
            }

            Spacer(modifier = Modifier.size(height = 20.dp, width = 1.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.sdp, horizontal = 5.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.cancel)),
                    style = TextStyle(
                        color = Color(0xFF203657),
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 14.ssp
                    ),
                    onClick = {
                        showAmountBottomSheet.value = false
                    },
                )

                ClickableText(
                    text = AnnotatedString(stringResource(R.string.apply)),
                    style = TextStyle(
                        color = Color(0xFF203657),
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 14.ssp
                    ),
                    onClick = {
                        onValueChanged(AmountModel(minAmountState.value, maxAmountState.value))
                        showAmountBottomSheet.value = false
                    },
                )
            }

        }
    }
}


@Composable
fun RangeSeekBar(
    onValueChanged: (AmountModel) -> Unit
) {
    var sliderValues by remember { mutableStateOf(0f..1000000f) }

    RangeSlider(
        value = sliderValues,
        onValueChange = { sliderValues_ ->
            sliderValues = sliderValues_
        },
        valueRange = 0f..1000000f,
        onValueChangeFinished = {},
        colors = SliderDefaults.colors(
            thumbColor = Color(0xFF203657),
            activeTrackColor = Color(0xFF203657),
            inactiveTrackColor = Color(0xFF859DB5)
        ),
        modifier = Modifier
            .height(2.dp)
    )

    onValueChanged(AmountModel(sliderValues.start.toString(), sliderValues.endInclusive.toString()))

}


