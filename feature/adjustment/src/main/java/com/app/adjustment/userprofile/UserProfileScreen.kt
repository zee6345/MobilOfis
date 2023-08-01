package com.app.adjustment.userprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.adjustment.components.dashedBorder
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetUserProfile
import com.app.network.data.responseModels.LoginVerifyResponse
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.helper.MainApp.Companion.context
import com.app.network.utils.Message
import com.app.network.viewmodel.AdjustmentViewModel
import ir.kaaveh.sdpcompose.sdp


lateinit var lang: MutableState<String>


@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: AdjustmentViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val userProfileInfo by viewModel.userInfo.collectAsState()

    val userInfo = fetchUserDetails()
    LaunchedEffect(key1 = true) {
        //fetch accounts list
        viewModel.getUserInfo(
            userInfo.customerNo.toString()
        )
    }

    val userName = remember { mutableStateOf("") }
    val customerName = remember { mutableStateOf("") }
    val customerLastName = remember { mutableStateOf("") }
    val customerFatherName = remember { mutableStateOf("") }
    val lang = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val TOTPEnabled = remember { mutableStateOf("") }
    val TOTPChangeDate = remember { mutableStateOf("") }
    val nonOtpEnabled = remember { mutableStateOf("") }

    userProfileInfo?.let {
        when (it) {
            is DataState.Loading -> {

            }

            is DataState.Error -> {
                Message.showMessage(context, "Failed to Get UserInfo")
            }

            is DataState.Success -> {
                val userAccounts = it.data as GetUserProfile
                userAccounts.apply {

                        userName.value = this.userName
                        customerName.value = this.customerName
                        customerLastName.value = this.customerLastName
                        customerFatherName.value = this.customerAtaAdi
                        lang.value = this.lang
                        phoneNumber.value = this.phoneNumber
                        email.value = "${this.email}"
                        TOTPEnabled.value = this.TOTPEnabled
                        TOTPChangeDate.value = this.TOTPChangeDate
                        nonOtpEnabled.value = this.nonOtpEnabled

                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), enabled = true)
            .background(color = Color(R.color.border_light_grey))
    ) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .fillMaxWidth()
                .weight(0.1f),
            color = Color(0xFF203657),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier
                        .size(height = 25.dp, width = 32.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { navController.popBackStack() },
                    contentDescription = ""
                )
                Text(
                    text = stringResource(R.string.user_s_profile),
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                )


            }
        }



        Column(
            modifier = Modifier
                .weight(0.9f)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),

                ) {

                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.sdp),
                    backgroundColor = Color.White
                ) {
                    Column(

                    ) {

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .dashedBorder(3.dp, Color(R.color.border_grey))
                                .padding(horizontal = 10.sdp, vertical = 8.sdp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.source_of_origin), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(R.color.grey_text),

                                        )
                                )

                                Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.internet_office), style = TextStyle(

                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }

                            Column(
                                Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.document_no), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(R.color.grey_text),
                                    )
                                )

                                Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.p10001080), style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),

                                        )
                                )
                            }

                            Column(
                                Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.father_s_name), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(R.color.grey_text),
                                    )
                                )

                                Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = customerFatherName.value, style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),

                                        )
                                )
                            }
                        }



                        Row(
                            Modifier
                                .fillMaxWidth()
                                .dashedBorder(3.dp, Color(R.color.border_grey))
                                .padding(horizontal = 10.sdp, vertical = 8.sdp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Column {

                                Text(
                                    text = stringResource(R.string.login), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(R.color.grey_text),
                                    )
                                )

                                Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                Text(
                                    text = userName.value, style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }


                        }


                        Row(
                            Modifier
                                .fillMaxWidth()
                                .dashedBorder(3.dp, Color(R.color.border_grey))
                                .padding(horizontal = 10.sdp, vertical = 8.sdp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()

                            ) {

                                Text(
                                    text = stringResource(R.string.mobile_number_for_sms), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(R.color.grey_text),

                                        )
                                )

                                Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))


                                Text(
                                    text = phoneNumber.value, style = TextStyle(

                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }



                            Column(
                                Modifier
                                    .weight(0.5f)

                                    .fillMaxWidth()
                            ) {

                                Text(
                                    text = stringResource(R.string.easy_signature_mobile_number),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(R.color.grey_text),
                                    )
                                )

                                Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))


                                Text(
                                    text = stringResource(R.string._505553468), style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),

                                        )
                                )
                            }


                        }




                        Row(
                            Modifier
                                .fillMaxWidth()
                                .dashedBorder(3.dp, Color(R.color.border_grey))
                                .padding(horizontal = 10.sdp, vertical = 8.sdp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Column {

                                Text(
                                    text = stringResource(R.string.e_mail), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(R.color.grey_text),
                                    )
                                )

                                Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                Text(
                                    text = stringResource(R.string.text_email), style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }


                        }


                        Row(
                            Modifier
                                .fillMaxWidth()
                                .dashedBorder(3.dp, Color(R.color.border_grey))
                                .padding(horizontal = 10.sdp, vertical = 8.sdp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Text(
                                text = stringResource(R.string.language), style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(R.color.grey_text),
                                )
                            )


                            ThreeBoxComponent()


                        }


                        Row(
                            Modifier
                                .fillMaxWidth()
                                .dashedBorder(3.dp, Color(R.color.border_grey))
                                .padding(horizontal = 10.sdp, vertical = 8.sdp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Text(
                                text = stringResource(R.string.google_authenticator), style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(R.color.grey_text),
                                )
                            )


                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Color(0xFF203657),
                                        shape = RoundedCornerShape(size = 6.dp)
                                    )
                                    .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)

                            ) {
                                Text(
                                    text = stringResource(R.string.activate),
                                    style = TextStyle(

                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFFFFFF),
                                        textAlign = TextAlign.Center,


                                        )
                                )
                            }


                        }


                    }
                }



                Spacer(modifier = Modifier.size(height = 100.dp, width = 1.dp))

            }


        }

    }
}




@Composable
private fun ThreeBoxComponent() {
    val selectedBoxIndex = remember { mutableStateOf(-1) }

    Row() {
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 0 }) {
            Text(
                text = "AZ", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 1 }) {
            Text(
                text = "EN", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 2) Color(0xFF223142) else Color(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { selectedBoxIndex.value = 2 }) {
            Text(
                text = "RU", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 2) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
    }
}


private fun fetchUserDetails(): LoginVerifyResponse {
    val str = MainApp.session[Keys.KEY_USER_DETAILS]
    return Converter.fromJson(str!!, LoginVerifyResponse::class.java)
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen(rememberNavController())
}