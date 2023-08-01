package com.app.adjustment.changepassword

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R
import com.app.adjustment.otp.navigation.changePasswordToOTP
import com.app.adjustment.components.ShowProgressDialog
import com.app.network.data.DataState
import com.app.network.data.callModels.ChangePasswordRequest
import com.app.network.data.responseModels.ChangePasswordResponse
import com.app.network.helper.MainApp
import com.app.network.utils.Message
import com.app.network.viewmodel.AdjustmentViewModel
import kotlinx.coroutines.launch


@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    viewModel: AdjustmentViewModel = viewModel()
) {

    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }
    val pswdErrorCheck1 = remember { mutableStateOf(false) }
    val pswdErrorCheck2 = remember { mutableStateOf(false) }
    val pswdErrorCheck3 = remember { mutableStateOf(false) }
    val errorCheck1 = remember { mutableStateOf("") }
    val errorCheck2 = remember { mutableStateOf("") }
    val errorCheck3 = remember { mutableStateOf("") }
    val context: Context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }

    val changePassword by viewModel.changePassword.collectAsState()
    val coroutine = rememberCoroutineScope()
    val userDetails = MainApp.session.fetchUserDetails()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
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
                    text = stringResource(R.string.changing_the_password),
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
                .padding(horizontal = 12.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                TextFieldWithEndDrawable(
                    stringResource(R.string.current_password),
                    pswdErrorCheck1.value,
                    errorCheck1.value
                ) {
                    currentPassword.value = it

                    if (it.isNotEmpty()) {
                        if (pswdErrorCheck1.value)
                            pswdErrorCheck1.value = false
                    }

                }
                TextFieldWithEndDrawable(
                    stringResource(R.string.new_password),
                    pswdErrorCheck2.value,
                    errorCheck2.value
                ) {
                    newPassword.value = it

                    if (it.isNotEmpty()) {
                        if (pswdErrorCheck2.value)
                            pswdErrorCheck2.value = false
                    }
                }
                TextFieldWithEndDrawable(
                    stringResource(R.string.repeat_the_new_password),
                    pswdErrorCheck3.value,
                    errorCheck3.value
                ) {
                    repeatPassword.value = it

                    if (it.isNotEmpty()) {
                        if (pswdErrorCheck3.value)
                            pswdErrorCheck3.value = false
                    }
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFE7EEFC), // Change the background color here
                        contentColor = Color(0xFF203657) // Change the text color here if needed
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF203657), // Change the border color here
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        stringResource(R.string.back),
                        modifier = Modifier.padding(vertical = 10.dp),
                        style = TextStyle(
                            fontSize = 17.sp, shadow = null
                        )
                    )
                }

                Button(
                    onClick = {

                        if (isPasswordValid(currentPassword.value)) {
                            pswdErrorCheck1.value = false
                            if (isPasswordValid(newPassword.value)) {
                                pswdErrorCheck2.value = false
                                if (isPasswordValid(repeatPassword.value)) {
                                    pswdErrorCheck3.value = false


                                    coroutine.launch {

                                        viewModel.changePassword(
                                            ChangePasswordRequest(
                                                userDetails.userName,
                                                currentPassword.value,
                                                newPassword.value,
                                                repeatPassword.value
                                            )
                                        )

                                    }


                                } else {
                                    pswdErrorCheck3.value = true
                                    errorCheck3.value =
                                        "The password should contain at least 8 characters not more than 20: numerals (0-9), upper (A-Z) and lower (a-z) case letters of the Latin alphabet. Special characters may be used"
                                }
                            } else {
                                pswdErrorCheck2.value = true
                                errorCheck2.value =
                                    "The password should contain at least 8 characters not more than 20: numerals (0-9), upper (A-Z) and lower (a-z) case letters of the Latin alphabet. Special characters may be used"
                            }
                        } else {
                            pswdErrorCheck1.value = true
                            errorCheck1.value =
                                "The password should contain at least 8 characters not more than 20: numerals (0-9), upper (A-Z) and lower (a-z) case letters of the Latin alphabet. Special characters may be used"

                        }

                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF203657), // Change the background color here
                        contentColor = Color.White // Change the text color here if needed
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Text(
                        stringResource(R.string.refresh),
                        modifier = Modifier.padding(vertical = 10.dp),
                        style = TextStyle(
                            color = Color.White, fontSize = 17.sp, shadow = null
                        )
                    )
                }

            }


        }
    }


    changePassword?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
                if (isLoading.value) {
                    ShowProgressDialog(isLoading)
                }
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as ChangePasswordResponse
                if (data.errMessage.equals("SUCCESS", true)) {

                    LaunchedEffect(Unit){
                        navController.navigate(changePasswordToOTP)
                    }

                } else {
                    Message.showMessage(context, "Failed to change password!")
                }

            }
        }
    }
}


@Composable
fun TextFieldWithEndDrawable(
    hint: String,
    pswdErrorCheck: Boolean,
    errorString: String,
    onValueChange: (str: String) -> Unit
) {

    val textState = remember { mutableStateOf("") }
    val isPswdVisible = remember { mutableStateOf(false) }

    Column {


        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            visualTransformation = if (isPswdVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = {
                androidx.compose.material3.Text(
                    text = hint,
                    fontSize = 14.sp
                )
            }, trailingIcon = {
                if (pswdErrorCheck)
                    Icon(
                        Icons.Filled.Info,
                        "Error",
                        tint = Color.Red
                    )

                if (isPswdVisible.value) {
                    Icon(painterResource(id = R.drawable.ic_password_visible),
                        "",
                        modifier = Modifier.clickable {
                            isPswdVisible.value = false
                        })
                } else {
                    Icon(painterResource(id = R.drawable.ic_password_visible_off),
                        "",
                        modifier = Modifier.clickable {
                            isPswdVisible.value = true
                        })
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color(0xFF223142),
                unfocusedBorderColor = Color(0xFFE7EEFC),
                unfocusedLabelColor = Color(0xFF859DB5),
                focusedLabelColor = Color(0xFF223142)
            ),
            singleLine = true
        )

        if (pswdErrorCheck)
            Text(errorString)

    }
    onValueChange(textState.value)
}

private fun isPasswordValid(password: String): Boolean {
    val regex =
        """^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_=+\-\[\]{};:|",.<>\\/?]).{8,20}$""".toRegex()
    return regex.matches(password)
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun ChangePasswordScreenPreview() {
    ForgetPasswordScreen(rememberNavController())
}