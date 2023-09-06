package com.app.adjustment.changepassword

import android.content.Context
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R

import com.app.network.helper.Converter
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.requestModels.ChangePasswordRequest
import com.app.network.models.responseModels.ChangePasswordResponse
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.utils.Message
import com.app.network.viewmodel.AdjustmentViewModel
import com.app.uikit.dialogs.RoundedCornerToast
import com.app.uikit.dialogs.ShowProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val securityToChangePassword = "securityToChangePassword"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    viewModel: AdjustmentViewModel = hiltViewModel()
) {


    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }
    val pswdErrorCheck1 = remember { mutableStateOf(false) }
    val pswdErrorCheck2 = remember { mutableStateOf(false) }
    val pswdErrorCheck3 = remember { mutableStateOf(false) }

    val error = remember { mutableStateOf("") }


    val context: Context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }

    val changePassword by viewModel.changePassword.collectAsState()
    val coroutine = rememberCoroutineScope()
    val str = viewModel.session[Keys.KEY_USER_DETAILS]
    val userDetails = Converter.fromJson(str!!, LoginVerifyResponse::class.java)

    var isPswdVisible1 by remember { mutableStateOf(false) }
    var isPswdVisible2 by remember { mutableStateOf(false) }
    var isPswdVisible3 by remember { mutableStateOf(false) }
    val passwordVisualTransformation1 =
        if (isPswdVisible1) VisualTransformation.None else PasswordVisualTransformation()
    val passwordVisualTransformation2 =
        if (isPswdVisible2) VisualTransformation.None else PasswordVisualTransformation()
    val passwordVisualTransformation3 =
        if (isPswdVisible3) VisualTransformation.None else PasswordVisualTransformation()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F7FA)),
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
                        .clickable {
//                            navController.popBackStack()
                            (context as ComponentActivity).finish()
                        },
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

                OutlinedTextField(
                    value = currentPassword.value,
                    onValueChange = { currentPassword.value = it },
                    visualTransformation = passwordVisualTransformation1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = {
                        androidx.compose.material3.Text(
                            text = stringResource(R.string.current_password), fontSize = 14.sp
                        )
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = if (isPswdVisible1) R.drawable.ic_pswd_visible else R.drawable.ic_pswd_visible),
                            contentDescription = if (isPswdVisible1) "Hide Password" else "Show Password",
                            modifier = Modifier
                                .pointerInteropFilter { event ->
                                    when (event.action) {
                                        MotionEvent.ACTION_DOWN -> isPswdVisible1 = true
                                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isPswdVisible1 =
                                            false
                                    }
                                    true
                                }
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = colorResource(R.color.background_card_blue),
                        unfocusedBorderColor = colorResource(R.color.border_grey),
                        unfocusedLabelColor = colorResource(R.color.grey_text),
                        focusedLabelColor = colorResource(R.color.background_card_blue)
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )


                OutlinedTextField(
                    value = newPassword.value,
                    onValueChange = { newPassword.value = it },
                    visualTransformation = passwordVisualTransformation2,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = {
                        androidx.compose.material3.Text(
                            text = stringResource(R.string.new_password),
                            fontSize = 14.sp
                        )
                    },
                    trailingIcon = {

                        Image(
                            painter = painterResource(id = if (isPswdVisible2) R.drawable.ic_pswd_visible else R.drawable.ic_pswd_visible),
                            contentDescription = if (isPswdVisible2) "Hide Password" else "Show Password",
                            modifier = Modifier
                                .pointerInteropFilter { event ->
                                    when (event.action) {
                                        MotionEvent.ACTION_DOWN -> isPswdVisible2 = true
                                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isPswdVisible2 =
                                            false
                                    }
                                    true
                                }
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = colorResource(R.color.background_card_blue),
                        unfocusedBorderColor = colorResource(R.color.border_grey),
                        unfocusedLabelColor = colorResource(R.color.grey_text),
                        focusedLabelColor = colorResource(R.color.background_card_blue)
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                OutlinedTextField(
                    value = repeatPassword.value,
                    onValueChange = { repeatPassword.value = it },
                    visualTransformation = passwordVisualTransformation3,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    label = {
                        androidx.compose.material3.Text(
                            text = stringResource(R.string.repeat_the_new_password),
                            fontSize = 14.sp
                        )
                    },
                    trailingIcon = {

                        Image(
                            painter = painterResource(id = if (isPswdVisible3) R.drawable.ic_pswd_visible else R.drawable.ic_pswd_visible),
                            contentDescription = if (isPswdVisible3) "Hide Password" else "Show Password",
                            modifier = Modifier
                                .pointerInteropFilter { event ->
                                    when (event.action) {
                                        MotionEvent.ACTION_DOWN -> isPswdVisible3 = true
                                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isPswdVisible3 =
                                            false
                                    }
                                    true
                                }
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = colorResource(R.color.background_card_blue),
                        unfocusedBorderColor = colorResource(R.color.border_grey),
                        unfocusedLabelColor = colorResource(R.color.grey_text),
                        focusedLabelColor = colorResource(R.color.background_card_blue)
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFF3F7FA), // Change the background color here
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

                        val currentPasswordValid = isPasswordValid(currentPassword.value)
                        val newPasswordValid = isPasswordValid(newPassword.value)
                        val repeatPasswordValid = isPasswordValid(repeatPassword.value)

                        if (currentPassword.value.isEmpty() or newPassword.value.isEmpty() or repeatPassword.value.isEmpty()) {
                            pswdErrorCheck1.value = true
                            error.value = "Please fill all fields"
                            return@Button
                        }

                        if (!currentPasswordValid) {
                            pswdErrorCheck2.value = true
                            error.value =
                                "The password should contain at least 8 characters not more than 20: numerals (0-9), upper (A-Z) and lower (a-z) case letters of the Latin alphabet. Special characters may be used"
                            return@Button
                        }

                        if (!newPasswordValid || !repeatPasswordValid) {
                            pswdErrorCheck3.value = true
                            error.value =
                                "The password should contain at least 8 characters not more than 20: numerals (0-9), upper (A-Z) and lower (a-z) case letters of the Latin alphabet. Special characters may be used"
                            return@Button
                        }

//                        if (currentPassword.value != viewModel.session[Keys.KEY_USER_PIN]) {
//                            pswdErrorCheck2.value = true
//                            error.value = "Incorrect current"
//                            return@Button
//                        }

                        if (newPassword.value != repeatPassword.value) {
                            pswdErrorCheck3.value = true
                            error.value = "New passwords do not match"
                            return@Button
                        }


                        //change password
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.changePassword(
                                ChangePasswordRequest(
                                    userDetails.userName,
                                    currentPassword.value,
                                    newPassword.value,
                                    repeatPassword.value
                                )
                            )

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

    //show empty field toast
    if (pswdErrorCheck1.value or pswdErrorCheck2.value or pswdErrorCheck3.value) {
        RoundedCornerToast(error.value, Toast.LENGTH_SHORT, context)

        LaunchedEffect(Unit) {
            delay(3000)

            pswdErrorCheck1.value = false
            pswdErrorCheck2.value = false
            pswdErrorCheck3.value = false

        }
    }


    changePassword?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true

            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as ChangePasswordResponse
                if (data.errMessage.equals("SUCCESS", true)) {

                    LaunchedEffect(Unit) {
                        navController.navigate(changePasswordToOTP)
                    }

                } else {
                    Message.showMessage(context, "Failed to change password!")
                }

            }
        }
    }

    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }
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