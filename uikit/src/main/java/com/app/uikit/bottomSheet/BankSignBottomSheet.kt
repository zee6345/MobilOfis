@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.app.uikit.bottomSheet

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.uikit.R
import com.app.uikit.models.AuthType

import ir.kaaveh.sdpcompose.sdp

@Composable
fun BankSignSheet(navController: NavController) {
    val aboutBankState = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(R.string.show_status_bottomsheet)),
//                    color = Color(0xFF203657),
            onClick = {
                aboutBankState.value = !aboutBankState.value
            }

        )
    }

    BankSignBottomSheet(aboutBankState) {

    }
}

private data class BankSign(
    val title: String,
    val type: AuthType
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSignBottomSheet(aboutBankState: MutableState<Boolean>, onItemClick: (AuthType) -> Unit) {
    val context = LocalContext.current


    val signingList = remember {
        mutableListOf(
            BankSign("SMS", AuthType.SMS),
            BankSign("Google Auth", AuthType.GOOGLE_AUTH),
            BankSign("Asan Imza", AuthType.ASAN_IMZA)
        )
    }

    val isSelected = remember { mutableStateOf<BankSign?>(null) }

    if (aboutBankState.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { aboutBankState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
    ) {

        Column(
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text(
                text = "Signing method",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                color = Color(0xFF223142)
            )
            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))

            LazyColumn {
                signingList.forEachIndexed { index, bankSign ->
                    item {
                        SigningItem(bankSign, isSelected.value) {
                            isSelected.value = it
                        }
                    }
                }
            }


//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(
//                        3.dp, colorResource(R.color.border_grey)
//                    ),
//                horizontalArrangement = Arrangement.Start
//
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 5.dp)
//                        .fillMaxWidth()
//                        .clickable {
//                            isSelected1.value = true
//                            isSelected2.value = false
//                            isSelected3.value = false
//
//
//                            authType = AuthType.SMS
//                        },
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//
//                    RadioButton(selected = isSelected1.value, onClick = {
//
//                    })
//                    androidx.compose.material.Text(
//                        text = "SMS",
//                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
//                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .dashedBorder(
//                        2.dp, colorResource(R.color.border_grey)
//                    ),
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 5.dp)
//                        .fillMaxWidth()
//                        .clickable {
//
//                            isSelected1.value = false
//                            isSelected2.value = true
//                            isSelected3.value = false
//
//
//                            authType = AuthType.GOOGLE_AUTH
//
//                        },
//
//                    verticalAlignment = Alignment.CenterVertically
//
//                ) {
//
//
//                    RadioButton(selected = isSelected2.value, onClick = {
//
//                    })
//
//                    androidx.compose.material.Text(
//                        text = "Google Auth",
//                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
//                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.size(width = 5.sdp, height = 1.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
////                        aboutBankState.value = false
//
//
//                        isSelected1.value = false
//                        isSelected2.value = false
//                        isSelected3.value = true
//
//                        authType = AuthType.ASAN_IMZA
//
//                    },
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 5.dp)
//                        .fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    RadioButton(selected = isSelected3.value, onClick = {
//
//                    })
//
//                    androidx.compose.material.Text(
//                        text = stringResource(R.string.easy_signature),
//                        style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
//                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
//                    )
//                }
//            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.sdp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Apply",
                    style = TextStyle(
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier
                        .clickable {
                            if (isSelected.value == null) {
                                Toast.makeText(context,"Please select signing type!",Toast.LENGTH_SHORT).show()
                            } else {
                                onItemClick(isSelected.value!!.type)
                                aboutBankState.value = false
                            }
                        }
                )
            }

        }
    }

}

@Composable
private fun SigningItem(
    bankSign: BankSign,
    isSelected: BankSign?,
    onItemClick: (BankSign) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(bankSign)
            },
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            RadioButton(selected = isSelected == bankSign, onClick = {

            })

            androidx.compose.material.Text(
                text = bankSign.title,
                style = TextStyle(color = Color(0xFF223142), fontSize = 14.sp),
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun BankSignSheetView() {
    BankSignSheet(rememberNavController())
}