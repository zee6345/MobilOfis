package com.app.adjustment.userprofile

import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.adjustment.googleauth.ActivateAuth
import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.responseModels.GetUserProfile
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.viewmodel.AdjustmentViewModel
import com.app.uikit.borders.dashedBorder
import com.app.uikit.dialogs.ShowProgressDialog
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

const val adjustmentToUserProfile = "adjustmentToUserProfile"

@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: AdjustmentViewModel = hiltViewModel()
) {

    val userProfileInfo by viewModel.userInfo.collectAsState()
    val userDisable2FA by viewModel.disable2FA.collectAsState()
    val userEnable2FA by viewModel.enable2FA.collectAsState()

    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userInfo = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val userData = remember { mutableStateOf<GetUserProfile?>(null) }
    val isLoading = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        viewModel.getUserInfo(
            userInfo.customerNo.toString()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), enabled = true)
            .background(color = colorResource(R.color.border_light_grey))
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

                if (userData.value != null) {

                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.sdp),
                        backgroundColor = Color.White
                    ) {
                        Column {

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
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
                                        text = stringResource(R.string.source_of_origin),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = colorResource(R.color.grey_text),

                                            )
                                    )

                                    Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "${userData.value!!.customerLastName}"
                                            .replace("X", "-")
                                            .replace("x", "-"),
                                        style = TextStyle(
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
                                        text = stringResource(R.string.document_no),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = colorResource(R.color.grey_text),
                                        )
                                    )

                                    Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "${userData.value!!.customerName}",
                                        style = TextStyle(
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
                                        text = stringResource(R.string.father_s_name),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = colorResource(R.color.grey_text),
                                        )
                                    )

                                    Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "${userData.value!!.customerAtaAdi}"
                                            .replace("X", "-")
                                            .replace("x", "-"),
                                        style = TextStyle(
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
                                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {

                                Column {

                                    Text(
                                        text = stringResource(R.string.login), style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = colorResource(R.color.grey_text),
                                        )
                                    )

                                    Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                    Text(
                                        text = "${userData.value!!.userName}", style = TextStyle(
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
                                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                                    .padding(horizontal = 10.sdp, vertical = 8.sdp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    Modifier
//                                        .weight(0.5f)
                                        .fillMaxWidth()

                                ) {

                                    Text(
                                        text = stringResource(R.string.mobile_number_for_sms),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = colorResource(R.color.grey_text),

                                            )
                                    )

                                    Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))


                                    Text(
                                        text = "${userData.value!!.phoneNumber}", style = TextStyle(

                                            fontSize = 14.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = Color(0xFF223142),
                                        )
                                    )
                                }


//                                Column(
//                                    Modifier
//                                        .weight(0.5f)
//                                        .fillMaxWidth()
//                                ) {
//
//                                    Text(
//                                        text = stringResource(R.string.easy_signature_mobile_number),
//                                        style = TextStyle(
//                                            fontSize = 12.sp,
//                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                            color = colorResource(R.color.grey_text),
//                                        )
//                                    )
//
//                                    Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))
//
//
//                                    Text(
//                                        text = "${userData.value!!.phoneNumber}", style = TextStyle(
//                                            fontSize = 14.sp,
//                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                                            color = Color(0xFF223142),
//
//                                            )
//                                    )
//                                }


                            }




                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {

                                Column {

                                    Text(
                                        text = stringResource(R.string.e_mail), style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = colorResource(R.color.grey_text),
                                        )
                                    )

                                    Spacer(modifier = Modifier.size(height = 8.dp, width = 1.dp))

                                    Text(
                                        text = if (userData.value!!.email != null) "${userData.value!!.email}" else "-",
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )

                                }


                            }


                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .dashedBorder(2.dp, colorResource(R.color.border_grey))
                                    .padding(horizontal = 10.sdp, vertical = 8.sdp),

                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {

                                Text(
                                    text = stringResource(R.string.language), style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = colorResource(R.color.grey_text),
                                    )
                                )


                                ThreeBoxComponent(userData.value!!.lang)


                            }


                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.sdp, vertical = 10.sdp),

                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {

                                Text(
                                    text = stringResource(R.string.google_authenticator),
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = colorResource(R.color.grey_text),
                                    )
                                )


                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFF203657),
                                            shape = RoundedCornerShape(size = 6.dp)
                                        )
                                        .padding(
                                            start = 10.dp,
                                            top = 5.dp,
                                            end = 10.dp,
                                            bottom = 5.dp
                                        )
                                        .clickable {
                                            if (userData.value!!.TOTPEnabled == null) {
                                                coroutineScope.launch {
                                                    viewModel.enable2FA()
                                                }
                                            } else {
                                                coroutineScope.launch {
                                                    viewModel.disable2FA()
                                                }
                                            }
                                        }

                                ) {
                                    Text(
                                        text = if (userData.value!!.TOTPEnabled == null) stringResource(
                                            R.string.activate
                                        ) else stringResource(
                                            R.string.deactivate
                                        ),
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

                }

                Spacer(modifier = Modifier.size(height = 100.dp, width = 1.dp))

            }


        }

    }




    userProfileInfo?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
//                Message.showMessage(context, "Failed to Get UserInfo")
            }

            is DataState.Success -> {
                isLoading.value = false

                val userAccounts = it.data as GetUserProfile
                userAccounts?.let { user ->

                    userData.value = user

                }
            }
        }
    }

    userDisable2FA?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = false
            }

            is DataState.Error -> {
                isLoading.value = false
//                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {
                isLoading.value = true


            }
        }
    }

    userEnable2FA?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
//                Message.showMessage(context, it.errorMessage)
            }

            is DataState.Success -> {

                isLoading.value = false

                LaunchedEffect(Unit) {
//                    navController.navigate(userProfileToGoogleAuth)
                    val intent = Intent(context, ActivateAuth::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)

                }
            }
        }
    }


    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }

}


@Composable
private fun ThreeBoxComponent(value: String) {
    val selectedBoxIndex = remember { mutableStateOf(-1) }

    when (value) {
        "AZ" -> selectedBoxIndex.value = 0
        "EN" -> selectedBoxIndex.value = 1
        "RU" -> selectedBoxIndex.value = 2
    }

    Row() {
        Box(modifier = Modifier
            .padding(6.dp)
            .background(
                if (selectedBoxIndex.value == 0) Color(0xFF223142) else colorResource(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
//                selectedBoxIndex.value = 0
            }) {
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
                if (selectedBoxIndex.value == 1) Color(0xFF223142) else colorResource(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
//                selectedBoxIndex.value = 1
            }) {
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
                if (selectedBoxIndex.value == 2) Color(0xFF223142) else colorResource(R.color.border_grey),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
//                selectedBoxIndex.value = 2
            }) {
            Text(
                text = "RU", modifier = Modifier.padding(6.dp), style = TextStyle(
                    if (selectedBoxIndex.value == 2) Color.White else Color(0xFF223142),
                    fontSize = 12.sp
                )
            )
        }
    }
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen(rememberNavController())
}