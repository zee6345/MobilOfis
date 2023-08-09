package com.app.uikit.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

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
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(id = R.string.show_account_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                showAmountBottomSheet.value = !showAmountBottomSheet.value
            }
        )
    }
//    AccountBottomSheet(showAmountBottomSheet, null)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountBottomSheet(showAmountBottomSheet: MutableState<Boolean>){
    val minAmountState = remember { mutableStateOf("") }
    val maxAmountState = remember { mutableStateOf("") }
    var sliderPosition by remember { mutableStateOf(0f..100000f) }

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
            Row(modifier = Modifier.fillMaxWidth(),
                Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.amount),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    color = Color(0xFF223142)
                )
            }
            Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
//            TypeList()
            Row (modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = minAmountState.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f),
                    onValueChange = {
                        minAmountState.value = it
                    },
                    label = {
                        Text(
                            text = stringResource(R.string._20000),
                            fontSize = 14.sp
                        )

                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF223142),
                        unfocusedBorderColor = Color(R.color.border_grey),
                        unfocusedLabelColor = Color(R.color.grey_text),
                        focusedLabelColor = Color(0xFF223142),
                    ),
                    singleLine = true
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.01f)
                        .width(2.sdp)
                        .height(1.sdp)
                        .background(
                            color = Color(R.color.border_grey), shape = RoundedCornerShape(size = 10.sdp)
                        )
                        .align(Alignment.CenterVertically)
                )
                OutlinedTextField(
                    value = minAmountState.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f),
                    onValueChange = {
                        /* Handle value change */
                        maxAmountState.value = it
                    },
                    label = {
                        Text(
                            text = stringResource(R.string._10000000),
                            fontSize = 14.sp
                        )

                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color(0xFF223142),
                        unfocusedBorderColor = Color(R.color.border_grey),
                        unfocusedLabelColor = Color(R.color.grey_text),
                        focusedLabelColor = Color(0xFF223142),
                    ),
                    singleLine = true
                )
            }
                Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
                RangeSlider(
                    modifier = Modifier.semantics { contentDescription = "Localized Description" },
                    steps = 5,
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    valueRange = 0f..10000000f,
                    onValueChangeFinished = {
                        // launch some business logic update with the state you hold
                        // viewModel.updateSelectedSliderValue(sliderPosition)
                    })

                Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))
                Row(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
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
                            showAmountBottomSheet.value = false
                        },

                        )
                }

     }
  }
}


@Composable
@ExperimentalMaterial3Api
fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SliderDefaults.colors(),
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    startThumb: @Composable (SliderPositions) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = startInteractionSource,
            colors = colors,
            enabled = enabled
        )
    },
    endThumb: @Composable (SliderPositions) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = endInteractionSource,
            colors = colors,
            enabled = enabled
        )
    },
    track: @Composable (SliderPositions) -> Unit = { sliderPositions ->
        SliderDefaults.Track(
            colors = colors,
            enabled = enabled,
            sliderPositions = sliderPositions
        )
    },
    /*@IntRange(from = 0)*/
    steps: Int = 0,
) {
}