package com.app.adjustment

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.companies.companydisplay.navigation.displayDuringLogin
import com.app.adjustment.exchangerate.navigation.adjustmentToExchangeRates
import com.app.adjustment.navigation.securityScreen
import com.app.adjustment.userprofile.adjustmentToUserProfile

import com.app.uikit.bottomSheet.AboutBankSheet
import com.app.uikit.views.CustomSwitch

const val SESSION = "SESSION_EVENTS"

@Composable
fun AdjustmentsScreen(navController: NavController) {
    val aboutBankState = rememberSaveable { mutableStateOf(false) }
    val contactState = rememberSaveable { mutableStateOf(false) }
    val context: Context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.border_light_grey))
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .then(Modifier.wrapContentHeight()),
            color = Color(0xFF203657),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        modifier = Modifier
                            .size(width = 32.dp, height = 25.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                navController.popBackStack()
                            },
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(R.string.adjustments),
                        style = TextStyle(color = Color.White, fontSize = 18.sp),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 8.dp)
                    )
                }

            }
//            Row(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .padding(20.dp),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_back_arrow),
//                    modifier = Modifier
//                        .size(width = 32.dp, height = 25.dp)
//                        .align(CenterVertically)
//                        .clickable {
//                            navController.popBackStack()
//                        },
//                    contentDescription = ""
//                )
//                Text(
//                    text = stringResource(R.string.adjustments),
//                    style = TextStyle(color = Color.White, fontSize = 18.sp),
//                    modifier = Modifier
//                        .align(CenterVertically)
//                        .padding(horizontal = 8.dp)
//                )
//
//
//            }
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 10.dp),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val (selected, setSelected) = remember {
                                    mutableStateOf(true)
                                }
                                Text(
                                    text = stringResource(R.string.language_of_the_application),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                    modifier = Modifier.padding(
                                        vertical = 10.dp,
                                        horizontal = 12.dp
                                    )
                                )
                                ThreeBoxComponent()

                            }

                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clickable {
                                    navController.navigate(displayDuringLogin)
                                },
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.company_to_display_during_login),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                    modifier = Modifier.padding(
                                        vertical = 10.dp,
                                        horizontal = 12.dp
                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_option_arrow_forward),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .align(CenterVertically)
                                        .padding(end = 12.dp),
                                )
                            }


                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clickable {
                                    navController.navigate(adjustmentToUserProfile)
                                },
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.user_s_profile),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                    modifier = Modifier.padding(
                                        vertical = 10.dp,
                                        horizontal = 12.dp
                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_option_arrow_forward),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .align(CenterVertically)
                                        .padding(end = 12.dp),
                                )
                            }


                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp), shape = RoundedCornerShape(12.dp)

                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(securityScreen)
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = stringResource(id = R.string.security),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                    modifier = Modifier.padding(
                                        vertical = 10.dp,
                                        horizontal = 12.dp
                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_option_arrow_forward),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .align(CenterVertically)
                                        .padding(end = 12.dp),
                                )
                            }


                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.receive_push_notifications),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                )

                                CustomSwitch()

                            }


                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.night_mode),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                )

                                CustomSwitch()
                            }


                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clickable {
                                    aboutBankState.value = !aboutBankState.value
                                },
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.about_the_bank),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                    modifier = Modifier.padding(
                                        vertical = 12.dp,
                                        horizontal = 12.dp
                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_option_arrow_forward),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .align(CenterVertically)
                                        .padding(end = 12.dp),
                                )
                            }


                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clickable {
                                    contactState.value = !contactState.value
                                },
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(R.string.contact_information),
                                    style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                                    modifier = Modifier.padding(
                                        vertical = 10.dp,
                                        horizontal = 12.dp
                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_option_arrow_forward),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .align(CenterVertically)
                                        .padding(end = 12.dp),
                                )
                            }

                        }

                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(50.dp))

                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(

                                    )
                                    .clickable {
                                        // In your sending code
                                        val intent = Intent()
                                        intent.action = SESSION
                                        intent.putExtra("data", "exit")
                                        LocalBroadcastManager.getInstance(context)
                                            .sendBroadcast(intent)
                                    }
                            ) {
                                Text(
                                    text = stringResource(R.string.safe_exit),
                                    style = TextStyle(color = Color(0xFFFF4E57), fontSize = 16.sp),
                                    modifier = Modifier.padding(
                                        vertical = 12.dp,
                                        horizontal = 12.dp
                                    )
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.exit_icon),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .align(CenterVertically)
                                        .padding(end = 12.dp),
                                )
                            }

                        }



                        Text(
                            text = stringResource(R.string.application_version_00001),
                            style = TextStyle(color = Color.Black, fontSize = 12.sp),
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }


            }


        }


    }



    AboutBankSheet(aboutBankState, navController) {
        navController.navigate(adjustmentToExchangeRates)
    }
    com.app.uikit.bottomSheet.ContactBottomSheet(contactState)
}


//@Composable
//fun Switch() {
//    var switchCheckedState by remember { mutableStateOf(false) }
//
//    Switch(
//        checked = switchCheckedState,
//        onCheckedChange = { switchCheckedState = it },
//        colors = SwitchDefaults.colors(
//            checkedThumbColor = Color.White,
//            checkedTrackColor = Color(0xFF1DD580),
//            uncheckedThumbColor = Color.White,
//            uncheckedTrackColor = colorResource(R.color.border_grey),
//
//            )
//    )
//
//}


@Composable
private fun ThreeBoxComponent() {
    val selectedBoxIndex = remember { mutableStateOf(1) }

    Row() {
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 0) Color(0xFF223142) else colorResource(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 0 }) {
            Text(
                text = stringResource(R.string.az),
                modifier = Modifier.padding(6.dp),
                style = TextStyle(
                    if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 1) Color(0xFF223142) else colorResource(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 1 }) {
            Text(
                text = stringResource(R.string.en),
                modifier = Modifier.padding(6.dp),
                style = TextStyle(
                    if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 2) Color(0xFF223142) else colorResource(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 2 }) {
            Text(
                text = stringResource(R.string.ru),
                modifier = Modifier.padding(6.dp),
                style = TextStyle(
                    if (selectedBoxIndex.value == 2) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun AdjustmentsScreenPreview() {
    val navController = rememberNavController()
    AdjustmentsScreen(navController)
}