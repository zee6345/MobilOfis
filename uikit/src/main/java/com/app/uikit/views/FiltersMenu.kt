package com.app.uikit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.uikit.R
import com.app.uikit.bottomSheet.DateModel
import com.app.uikit.data.DataProvider
import com.app.uikit.models.DateType
import com.app.uikit.models.FilterModel
import com.app.uikit.models.FilterType
import ir.kaaveh.sdpcompose.sdp


val transfersDate = mutableStateOf(DateModel("", "", null))
val transfersType = mutableStateOf("Type")
val transfersBeneficiary = mutableStateOf("Beneficiary")
val transfersCurrency = mutableStateOf("Currency")
val transfersStatus = mutableStateOf("Status")
val transfersAccount = mutableStateOf("From the account")

@Composable
fun FiltersTopRow(onFilterClick: (FilterType?) -> Unit) {

    val menu = remember { DataProvider.filtersModelList }
    var isSelected by remember { mutableStateOf<FilterModel?>(null) }
    var filterTitle by remember { mutableStateOf("") }

    LazyRow {

        menu.forEachIndexed { index, filterModel ->

            item {

                filterTitle = when (index) {
                    0 -> {
                        when (transfersDate.value.type) {
                            DateType.TODAY -> {
                                "Today"
                            }

                            DateType.YESTERDAY -> {
                                "Yesterday"
                            }

                            DateType.THIS_WEEK -> {
                                "This Week"
                            }

                            DateType.LAST_WEEK -> {
                                "${transfersDate.value.startDate} - ${transfersDate.value!!.endDate}"
                            }

                            DateType.THIS_MONTH -> {
                                "${transfersDate.value!!.startDate} - ${transfersDate.value!!.endDate}"
                            }

                            DateType.LAST_MONTH -> {
                                "${transfersDate.value!!.startDate} - ${transfersDate.value!!.endDate}"
                            }

                            DateType.CUSTOM -> {
                                "${transfersDate.value!!.startDate} - ${transfersDate.value!!.endDate}"
                            }

                            else -> {
                                "Duration"
                            }
                        }

                    }

                    1 -> {
                        transfersStatus.value
                    }

                    2 -> {
                        transfersAccount.value
                    }

                    3 -> {
                        transfersType.value
                    }

                    4 -> {
                        transfersBeneficiary.value
                    }

                    7 -> {
                        transfersCurrency.value
                    }

                    else -> {
                        filterModel.title
                    }
                }


                Box(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .background(
                            color = if (isSelected == filterModel) Color(0xFFE7EEFC) else Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.clickable {

                            isSelected = filterModel

                            onFilterClick(filterModel.type)

                        }) {

                        Text(
                            text = filterTitle,
                            modifier = Modifier
                                .padding(5.sdp)
                                .padding(horizontal = 7.dp)
                                .align(Alignment.CenterVertically),
                            fontSize = 14.sp
                        )
                        Icon(
                            imageVector = if (isSelected == filterModel) {
                                Icons.Filled.Close
                            } else {
                                filterModel.icon
                            },
                            contentDescription = "Close",
                            tint = if (isSelected == filterModel) {
                                colorResource(id = R.color.red)
                            } else {
                                colorResource(R.color.black)
                            },
                            modifier = Modifier
                                .padding(5.sdp)
                                .align(Alignment.CenterVertically)
                                .size(10.sdp, 10.sdp)
                                .clickable {
                                    isSelected = null

                                    onFilterClick(null)

                                    // Reset all filters to original values when isSelected is null
                                    transfersDate.value = DateModel("", "", null)
                                    transfersAccount.value = "From the account"
                                    transfersStatus.value = "Status"
                                    transfersType.value = "Type"
                                    transfersCurrency.value = "Currency"


                                }
                        )

                    }
                }

                Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))

            }

        }


    }


}


@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun FiltersMenu() {
    FiltersTopRow {

    }
}