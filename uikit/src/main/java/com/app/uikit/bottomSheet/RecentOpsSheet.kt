package com.app.uikit.bottomSheet

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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.app.uikit.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun RecentOpsSheetPreview() {
    val selectCompanyState = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(modifier = Modifier.padding(5.dp),
            text = AnnotatedString(text = stringResource(R.string.show_status_bottomsheet)),
            onClick = {
                selectCompanyState.value = !selectCompanyState.value
            }

        )
    }

    RecentOpsSheet(selectCompanyState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentOpsSheet(selectCompanyState: MutableState<Boolean>) {
    if (selectCompanyState.value) ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { selectCompanyState.value = false },
        shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp),
    ) {
        Column(Modifier.fillMaxWidth()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
//                        isBottomSheetExpanded = !isBottomSheetExpanded
//
//                        coroutineScope.launch {
//                            if (isBottomSheetExpanded) {
//                                scaffoldState.bottomSheetState.expand()
//                            } else {
//                                scaffoldState.bottomSheetState.collapse()
//                            }
//                        }

                    }
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_options_up),
                    modifier = Modifier.size(width = 24.sdp, height = 24.sdp),
                    tint = Color(0xFF223142),
                    contentDescription = ""
                )
            }


            Text(
                text = "Recent operations on accounts",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.sdp, bottom = 5.sdp),
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row {

                    Icon(
                        painterResource(id = R.drawable.ic_options_arrow_in),
                        modifier = Modifier.size(width = 18.sdp, height = 12.sdp),
                        tint = Color(0xFF223142),
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                    )

                    Text(
                        text = "5600.00",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF203657),
                            textAlign = TextAlign.Right,
                        )
                    )

                }

                Spacer(
                    modifier = Modifier.size(width = 20.sdp, height = 1.sdp)
                )

                Row {

                    Icon(
                        painterResource(id = R.drawable.ic_options_arrow_out),
                        modifier = Modifier.size(width = 18.sdp, height = 12.sdp),
                        tint = Color(0xFFFF4E57),
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.size(width = 10.sdp, height = 1.sdp)
                    )


                    Text(
                        text = "-5600.00",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFF4E57),
                            textAlign = TextAlign.Right,
                        )
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val selectedBoxIndex = remember { mutableStateOf(-1) }

                Row() {
                    Box(modifier = Modifier
                        .padding(6.dp)
                        .background(
                            if (selectedBoxIndex.value == 0) Color(0xFF223142) else Color(
                                R.color.border_grey
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedBoxIndex.value = 0 }) {
                        Text(
                            text = "All", modifier = Modifier.padding(6.dp), style = TextStyle(
                                if (selectedBoxIndex.value == 0) Color.White else Color(0xFF223142),
                                fontSize = 12.sp
                            )
                        )
                    }
                    Box(modifier = Modifier
                        .padding(6.dp)
                        .background(
                            if (selectedBoxIndex.value == 1) Color(0xFF223142) else Color(
                                R.color.border_grey
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedBoxIndex.value = 1 }) {
                        Text(
                            text = "Income", modifier = Modifier.padding(6.dp), style = TextStyle(
                                if (selectedBoxIndex.value == 1) Color.White else Color(0xFF223142),
                                fontSize = 12.sp
                            )
                        )
                    }
                    Box(modifier = Modifier
                        .padding(6.dp)
                        .background(
                            if (selectedBoxIndex.value == 2) Color(0xFF223142) else Color(
                                R.color.border_grey
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedBoxIndex.value = 2 }) {
                        Text(
                            text = "Expenditure",
                            modifier = Modifier.padding(6.dp),
                            style = TextStyle(
                                if (selectedBoxIndex.value == 2) Color.White else Color(0xFF223142),
                                fontSize = 12.sp
                            )
                        )
                    }
                }

                Box() {
                    Text(
                        text = "More"
                    )
                }

                Box() {
                    Icon(
                        painterResource(R.drawable.ic_transfers),
                        tint = Color(0xFF223142),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 10.sdp)
                    )
                }

            }

            LazyColumn() {
                item {
                    Text(
                        text = "date",
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background)
                    )
                }

                item {
                    CardsItem()
                }
            }


        }
    }
}

@Composable
private fun CardsItem() {

    Card(
        modifier = Modifier
            .padding(vertical = 5.sdp, horizontal = 5.sdp)
            .fillMaxWidth()
            .clickable {

            },
        elevation = 1.dp,
        backgroundColor = Color(R.color.border_grey),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 5.sdp),

            ) {


            Column(
                Modifier.fillMaxWidth()
            ) {

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        "PHARMASCOPE LIMITED",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF223142),
                        ),
                        modifier = Modifier.padding(vertical = 5.sdp)
                    )


                    Text(
                        "-13000.00",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF203657),
                            textAlign = TextAlign.Right,
                        ),
                        modifier = Modifier.padding(vertical = 5.sdp)
                    )
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        "LIABILITY COMPANY",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.4.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF223142),
                        ),

                        )

                    Text(
                        "18:24",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.1.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_medium)),
                            fontWeight = FontWeight(400),
                            color = Color(R.color.grey_text),
                        ),

                        )
                }


            }

        }


    }

}


@Preview
@Composable
private fun display() {
    RecentOpsSheetPreview()
}