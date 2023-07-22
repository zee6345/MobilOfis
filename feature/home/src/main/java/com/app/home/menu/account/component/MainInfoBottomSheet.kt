package com.app.home.menu.account.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.home.R
import com.app.home.menu.component.dashedBorder
import ir.kaaveh.sdpcompose.sdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainInfoBottomSheet(showModalBottomSheet: MutableState<Boolean>) {

    val rowModify = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.sdp)
        .dashedBorder(3.dp, Color(0x99C9CACC))

    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = {
            showModalBottomSheet.value = !showModalBottomSheet.value
        },
        containerColor = Color.White,

        ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {


            Row(
                rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_option_circulation),
                    contentDescription = "",
                    modifier = Modifier.size(
                        width = 32.dp, height = 32.dp
                    )
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(
                    text = "Circulation",
                    style = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                    ),

                    )


                Spacer(modifier = Modifier.size(width = 1.dp, height = 5.dp))
            }


            Row(
                rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_option_transfer),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier.size(
                        width = 32.dp, height = 32.dp
                    )
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(
                    text = "Transfers",
                    style = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                    ),
                )

                Spacer(modifier = Modifier.size(width = 1.dp, height = 5.dp))

            }



            Row(
                rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_option_card_statement),
                    contentDescription = "",
                    modifier = Modifier.size(
                        width = 32.dp, height = 32.dp
                    ),
                    colorFilter = ColorFilter.tint(Color.Black),
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(
                    text = "Extract",
                    style = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                    ),

                    )

                Spacer(modifier = Modifier.size(width = 1.dp, height = 5.dp))

            }

            Row(
                rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_option_card_info),
                    contentDescription = "",
                    modifier = Modifier.size(
                        width = 32.dp, height = 32.dp
                    ),
                    colorFilter = ColorFilter.tint(Color.Black),
                )

                Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                Text(
                    text = "Details",
                    style = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                    ),

                    )

                Spacer(modifier = Modifier.size(width = 1.dp, height = 5.dp))

            }


            Row(
                rowModify,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_option_card_details),
                    contentDescription = "",
                    modifier = Modifier.size(
                        width = 32.dp, height = 32.dp
                    ),
                    colorFilter = ColorFilter.tint(Color.Black),
                )

                Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

                Text(
                    text = "Account details",
                    style = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                    ),

                    )

                Spacer(modifier = Modifier.size(width = 1.dp, height = 5.dp))

            }


            Row(
                rowModify,
                verticalAlignment = Alignment.CenterVertically,

            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_option_card_edit),
                    contentDescription = "",
                    modifier = Modifier.size(
                        width = 32.dp, height = 32.dp
                    ),
                    colorFilter = ColorFilter.tint(Color.Black),
                )

                Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

                Text(
                    text = "Change your name",
                    style = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                    ),

                    )

                Spacer(modifier = Modifier.size(width = 1.dp, height = 5.dp))
            }


        }
    }


}