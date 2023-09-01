package com.app.uikit.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.borders.dashedBorder


@Preview(showBackground = true)
@Composable
fun PreviewBottomsSheetOptions() {

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

    BusinessCardOptionsSheet(businessCardOptions)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCardOptionsSheet(showModalBottomSheet: MutableState<Boolean>) {

    var switchCheckedState by remember { mutableStateOf(true) }

    if (showModalBottomSheet.value) ModalBottomSheet(
        onDismissRequest = { showModalBottomSheet.value = !showModalBottomSheet.value },
        containerColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier.fillMaxWidth()


        ) {

            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),
            ) {

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painterResource(id = R.drawable.ic_option_card_details),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)

                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = stringResource(R.string.card_account_details), style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }



            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),

                ) {

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painterResource(id = R.drawable.ic_option_card_info),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)

                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = stringResource(id = R.string.details), style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }



            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),
            ) {

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painterResource(id = R.drawable.ic_option_card_statement),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)

                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = stringResource(R.string.card_account_statement), style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }



            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),

                ) {

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painterResource(id = R.drawable.ic_option_card_statement),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)
                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = stringResource(R.string.card_statement), style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }

            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painterResource(id = R.drawable.ic_option_card_limits),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)
                    )
                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = stringResource(R.string.limits), style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }



            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),

                ) {

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painterResource(id = R.drawable.ic_option_card_block),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)

                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = stringResource(R.string.block_the_card), style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }

            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),
            ) {

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painterResource(id = R.drawable.ic_option_card_pin),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)

                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = stringResource(R.string.reset_pin_attempts), style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }



            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),

                ) {

                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painterResource(id = R.drawable.ic_option_card_sms),
                            contentDescription = "",
                            modifier = Modifier.size(width = 32.dp, height = 32.dp)

                        )

                        Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                        Text(
                            text = stringResource(R.string.sms_alert), style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular))
                            )
                        )
                    }


                    Switch(
                        checked = switchCheckedState,
                        onCheckedChange = { switchCheckedState = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFF1DD580),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color(R.color.border_grey),

                            )
                    )


                }

            }

            Row(
                modifier = Modifier
                    .dashedBorder(2.dp, Color(0x99C9CACC))
                    .fillMaxWidth(),
            ) {

                Row(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painterResource(id = R.drawable.ic_option_card_edit),
                        contentDescription = "",
                        modifier = Modifier.size(width = 32.dp, height = 32.dp)

                    )

                    Spacer(modifier = Modifier.size(width = 10.dp, height = 1.dp))

                    Text(
                        text = "Change name", style = TextStyle(
                            fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.roboto_regular))
                        )
                    )
                }

            }


        }
    }
}