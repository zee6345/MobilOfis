package com.app.home.menu.cards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.home.R
import com.app.home.menu.component.dashedBorder
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
fun BusinessCardOptionSheet(showModalBottomSheet: MutableState<Boolean>) {

    var switchCheckedState by remember { mutableStateOf(true) }

    val rowModify = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.sdp, vertical = 5.sdp)
        .dashedBorder(3.dp, Color(0x99C9CACC))

    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = !showModalBottomSheet.value },
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 22.dp)

        ) {


            Row(
                modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically,

            ) {

                Image(
                    painterResource(id = R.drawable.ic_option_card_details),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Card account details")

            }


            Row(
                modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(painterResource(id = R.drawable.ic_option_card_info), contentDescription = "")

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Details")

            }

            Row(
                modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painterResource(id = R.drawable.ic_option_card_statement),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Card account statement")

            }

            Row(modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically
                ) {

                Image(
                    painterResource(id = R.drawable.ic_option_card_statement),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Card statement")

            }

            Row(modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically
                ) {

                Image(
                    painterResource(id = R.drawable.ic_option_card_limits),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Limits")

            }

            Row (modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically
            ){

                Image(
                    painterResource(id = R.drawable.ic_option_card_block),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Block the card")

            }

            Row(modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically
                ) {

                Image(painterResource(id = R.drawable.ic_option_card_pin), contentDescription = "")


                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Reset PIN attempts")

            }

            Row(
                modifier = rowModify,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painterResource(id = R.drawable.ic_option_card_sms),
                        contentDescription = ""
                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(text = "SMS alert")

                }



                Switch(
                    checked = switchCheckedState,
                    onCheckedChange = { switchCheckedState = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF1DD580),
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFFE7EEFC),

                        )
                )
            }

            Row(
                modifier = rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(painterResource(id = R.drawable.ic_option_card_edit), contentDescription = "")

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(text = "Change your name")

            }


        }
    }
}