package com.app.uikit.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder

import ir.kaaveh.sdpcompose.sdp


//@Preview(showBackground = true)
@Composable
fun PreviewBottomSheetOptions() {

    val businessCardOptions = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = "Click me!"),
            onClick = {
                businessCardOptions.value = !businessCardOptions.value
            }

        )
    }

//    BusinessCardOptionSheet(businessCardOptions)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanInfoOptionSheet(showModalBottomSheet: MutableState<Boolean>) {

//    var switchCheckedState by remember { mutableStateOf(true) }

//    val rowModify = Modifier
//        .fillMaxWidth()
//        .padding(horizontal = 15.sdp, vertical = 5.sdp)
//        .dashedBorder(3.dp, Color(0x99C9CACC))

    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = !showModalBottomSheet.value },
        containerColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp)

        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(2.dp, Color(0x99C9CACC))
            ) {
                Text(
                    text = stringResource(R.string.contract_payment_schedule),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        color = Color(R.color.background_card_blue)
                    )
                )
            }



            Text(
                text = stringResource(R.string.actual_payment_schedule),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                style =  TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    color = Color(R.color.background_card_blue)
                )
            )


        }
    }
}