package com.app.home.main.account.details


import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.app.home.R
import com.app.uikit.bottomSheet.MainInfoBottomSheet

import com.app.network.models.responseModels.GetAccountsItem
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.viewmodel.LoginViewModel
import com.app.uikit.borders.dashedBorder
import com.app.uikit.borders.rightVerticalDashedBorder
import com.app.uikit.utils.Utils
import ir.kaaveh.sdpcompose.sdp

@Composable
fun MainInformation(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {


    val mainInfoOptions = rememberSaveable { mutableStateOf(false) }

    val str = viewModel.session[Keys.KEY_MAIN_INFO]
    val data = Converter.fromJson(str!!, GetAccountsItem::class.java)




    var title = ""
    if (data.ACCOUNT_TYPE != null) {
        when (data.ACCOUNT_TYPE) {
            "CARD-ACCOUNT" -> {
                title = if (data.NICKNAME != null) {
                    data.NICKNAME.ifEmpty {
                        "Settlement-card account"
                    }
                }else {
                    "Settlement-card account"
                }

//                isCardAccount = true

            }

            "ACCOUNT" -> {
                title = if (data.NICKNAME != null) {
                    data.NICKNAME.ifEmpty {
                        "Settlement account"
                    }
                } else{
                    "Settlement account"
                }

//                isCardAccount = false
            }
        }
    }


    val shareIntentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        // This block will be executed after returning from the sharing activity
        // You can perform any necessary actions here
    }


    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp, horizontal = 10.sdp),
        backgroundColor = Color.White
    ) {
        Column(

        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title, style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_medium))
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_options), contentDescription = "",
                    Modifier
                        .size(height = 28.dp, width = 28.dp)
                        .clickable {
                            mainInfoOptions.value = true
                        }
                )
            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Column {

                    Text(
                        text = stringResource(R.string.new_iban), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )


                    Text(
                        text = "${data.IBAN}", style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }


                Image(
                    painter = painterResource(id = R.drawable.ic_share), contentDescription = "",
                    Modifier
                        .size(height = 20.dp, width = 20.dp)
                        .clickable {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "${data.IBAN}")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            shareIntentLauncher.launch(shareIntent)
                        }
                )


            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = stringResource(R.string.old_iban), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.ORJ_IBAN}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }


                Image(
                    painter = painterResource(id = R.drawable.ic_share), contentDescription = "",
                    Modifier.size(height = 20.dp, width = 20.dp)
                        .clickable {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "${data.ORJ_IBAN}")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            shareIntentLauncher.launch(shareIntent)
                        }
                )


            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically

            ) {


                Image(
                    painter = painterResource(id = R.drawable.ic_wallet), contentDescription = "",
                    Modifier.size(height = 21.dp, width = 21.dp)
                )

                Spacer(Modifier.size(width = 5.dp, height = 1.dp))

                Text(
                    text = "${data.ACCOUNT_TYPE}", style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        color = colorResource(R.color.background_card_blue),

                        )
                )


            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey))
                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Text(
                        text = stringResource(R.string.branch), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = "${data.BRANCH_NAME}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(id = R.string.balance), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.BALANCE.toDouble()), style = TextStyle(

                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),
                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(R.string.currency), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = "${data.CCY_NAME}", style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()

                ) {

                    Text(
                        text = stringResource(id = R.string.blocked), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.BLOCKSAMOUNT.toDouble()), style = TextStyle(
                            fontSize = 14.sp,
                            color = colorResource(R.color.red_2)

                        )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.free_balance), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.REAL_BALANCE.toDouble()), style = TextStyle(
                            fontSize = 14.sp,
                            color = if (data.REAL_BALANCE.startsWith("-")) colorResource(R.color.red_2) else colorResource(R.color.background_card_blue)

                        )
                    )
                }
            }


            Row(
                Modifier
                    .fillMaxWidth()
                    .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .weight(0.5f)
                        .rightVerticalDashedBorder(3.dp, colorResource(R.color.border_grey))
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.bank_execution), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),

                            )
                    )

                    Text(
                        text =Utils.formatAmountWithSpaces(data.WFA_AMOUNT.toDouble()) , style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }

                Column(
                    Modifier
                        .weight(0.5f)
                        .padding(horizontal = 10.sdp, vertical = 8.sdp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.after_execution), style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.grey_text),
                        )
                    )

                    Text(
                        text = Utils.formatAmountWithSpaces(data.LAST_BALANCE.toDouble()), style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            color = colorResource(R.color.background_card_blue),

                            )
                    )
                }
            }


        }
    }


    MainInfoBottomSheet(mainInfoOptions)

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMainInformation() {
    MainInformation(navController = rememberNavController())
}