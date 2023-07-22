package com.app.adjustment.companies.companydisplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.adjustment.R

import com.app.adjustment.companies.companylist.CompanyListName
import com.app.adjustment.companies.companylist.navigation.companiesDisplayToCompanies
import com.app.adjustment.companies.data.DataProvider



@Composable
fun CompanyDisplayList(navController: NavController) {
    val companydisplayList = remember { DataProvider.companyList }
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp)
    ) {
        items(items = companydisplayList, itemContent = {
            CompanyDisplayListItem(list = it){
                navController.navigate(companiesDisplayToCompanies)
            }
        })
    }
}

@Composable
private fun CompanyDisplayListItem(list: CompanyListName, onClick:() -> Unit) {

    var isClicked by remember { mutableStateOf(false) }
    var isClickedStar by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onClick()
            }
        , shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 12.dp, bottom = 3.dp),
            ) {
                Box(modifier = Modifier.size(50.dp)) {
                    Image(painter = if (isClicked) painterResource(R.drawable.company_img) else painterResource(
                        R.drawable.company
                    ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { isClicked = !isClicked })

                    // Camera/Cross Icon
                    Image(painter = if (isClicked) painterResource(R.drawable.remove_image) else painterResource(
                        R.drawable.camera
                    ),
                        contentDescription = if (isClicked) "Remove Company Icon" else "Camera Icon",
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-2).dp, y = 1.dp)
                            .clickable { isClicked = !isClicked })
                }

                Text(
                    text = list.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    color = Color(0xff223142),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }

            Image(painter = if (isClickedStar) painterResource(R.drawable.ic_star_filled) else painterResource(
                R.drawable.ic_star_outlined
            ),
                contentDescription = if (isClickedStar) "Filled Star Icon" else "Outlined Star Icon",
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.CenterVertically)
                    .offset(x = (-2).dp, y = 1.dp)
                    .clickable { isClickedStar = !isClickedStar })
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
fun CompanyDisplayPreview() {
    CompanyDisplayList(rememberNavController())
}