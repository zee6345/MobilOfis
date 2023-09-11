package com.app.transfer.transfers.transferdetails

import android.content.Context
import android.content.Intent
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.network.helper.Keys
import com.app.network.models.DataState
import com.app.network.models.requestModels.FileDetails
import com.app.network.models.requestModels.GetPdfList
import com.app.network.models.responseModels.GetPdfResponse
import com.app.network.models.responseModels.GetTransactionDetails
import com.app.network.models.responseModels.TransactionDetails
import com.app.network.viewmodel.HomeViewModel
import com.app.transfer.R
import com.app.transfer.signatureauth.Signing
import com.app.transfer.signatureauth.auth.asanImzaSelectedIndex
import com.app.transfer.signatureauth.auth.googleAuthSelectedIndex
import com.app.transfer.signatureauth.auth.signAuthSelectedIndex
import com.app.transfer.signatureauth.signingType
import com.app.uikit.borders.dashedBorder
import com.app.uikit.borders.rightVerticalDashedBorder
import com.app.uikit.bottomSheet.BankSignBottomSheet
import com.app.uikit.dialogs.ShowProgressDialog
import com.app.uikit.models.AuthType
import com.app.uikit.utils.SharedModel
import com.app.uikit.utils.Utils
import com.app.uikit.views.AutoResizedText
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Details(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val isLoading = remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(true) }
    var isExpanded by remember { mutableStateOf(true) }
    val isSigned = remember { mutableStateOf(false) }
    val signBottomSheet = remember { mutableStateOf(false) }

    val detailsData by viewModel.getTransactionDetails.collectAsState()
    val pdfList by viewModel.getTransactionPdf.collectAsState()
    val detail = remember { mutableStateOf<TransactionDetails?>(null) }
    val fileDetailList = remember { mutableListOf<com.app.network.models.responseModels.FileDetails>() }
    val transferDetails = detail.value

    val context: Context = LocalContext.current
    val coroutine = rememberCoroutineScope()

    //fetch item data
    val data = SharedModel.init().signatureData.value
    isSigned.value = data!!.isSignRequired

    val ibankRef = if (isSigned.value) {
        data.transferList?.get(0)!!.ibankRef
    } else {
        data.transfer?.ibankRef.toString()
    }

    val loginType = viewModel.session.getInt(Keys.KEY_LOGIN_TYPE)


    LaunchedEffect(Unit) {
        coroutine.launch {
            viewModel.getTransactionDetails(ibankRef)
            viewModel.getTransferPdfList(
                GetPdfList(
                    listOf(FileDetails(ibankRef.toInt()))
                )
            )
        }
    }

    if (transferDetails != null) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {


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
                            .dashedBorder(2.dp, colorResource(R.color.border_grey)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier
                                .weight(0.5f)
                                .rightVerticalDashedBorder(
                                    2.dp,
                                    colorResource(R.color.border_grey)
                                )
                                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                .fillMaxWidth()

                        ) {

                            Text(
                                text = "Source of origin",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),

                                    )
                            )

                            Text(
                                text = if ("${transferDetails!!.source}" == "WEB") "Internet Office" else "${transferDetails!!.source}",
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
                                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                .fillMaxWidth()
                        ) {

                            Text(
                                text = "Document no", style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${transferDetails!!.PMTCUSTID}", style = TextStyle(
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
                                text = stringResource(R.string.transfer_type),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${transferDetails!!.trnType}", style = TextStyle(
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
                                text = stringResource(R.string.sender), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${transferDetails!!.CUSTNAME}",
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
                            .padding(horizontal = 10.sdp, vertical = 8.sdp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {

                            Text(
                                text = stringResource(id = R.string.from_the_account),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${transferDetails!!.BENEFACC} ${
                                    Utils.formatCurrency(
                                        transferDetails!!.ccyType
                                    )
                                }",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
                                )
                            )
                        }

                    }

                }
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.sdp, horizontal = 10.sdp),
                backgroundColor = Color.White
            ) {
                Column {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .dashedBorder(2.dp, colorResource(R.color.border_grey))
                            .padding(horizontal = 10.sdp, vertical = 8.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Beneficiary",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),

                                )
                        )

                        Image(
                            painter = if (isExpanded) painterResource(id = R.drawable.ic_option_collapse) else painterResource(
                                id = R.drawable.ic_option_expand
                            ),
                            contentDescription = "",
                            Modifier
                                .size(height = 26.dp, width = 26.dp)
                                .padding(4.dp)
                                .clickable {
                                    coroutine.launch {
                                        isExpanded = !isExpanded
                                    }
                                }
                        )

                    }

                    if (isExpanded) {

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
                                    text = stringResource(R.string.name), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = colorResource(R.color.grey_text),
                                    )
                                )

                                Text(
                                    text = transferDetails!!.BENEFNAME ?: "-",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }


                        }


                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                Modifier
                                    .weight(0.7f)
                                    .rightVerticalDashedBorder(
                                        2.dp,
                                        colorResource(R.color.border_grey)
                                    )
                                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                    .fillMaxWidth()

                            ) {

                                Text(
                                    text = stringResource(R.string.account_number),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = colorResource(R.color.grey_text),

                                        )
                                )

                                AutoResizedText(
                                    text = transferDetails!!.BENEFACC ?: "-",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }

                            Column(
                                Modifier
                                    .weight(0.3f)
                                    .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    text = stringResource(R.string.tin), style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = colorResource(R.color.grey_text),
                                    )
                                )

                                AutoResizedText(
                                    text = transferDetails!!.BENEFTAXID ?: "-", style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),

                                        )
                                )
                            }
                        }


                    }

                }
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.sdp, horizontal = 10.sdp),
                backgroundColor = Color.White
            ) {
                Column {


                    Row(
                        Modifier
                            .fillMaxWidth()
                            .dashedBorder(2.dp, colorResource(R.color.border_grey))
                            .padding(horizontal = 10.sdp, vertical = 8.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Beneficiary's Bank",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),

                                )
                        )


                        Image(
                            painter = if (expanded) painterResource(id = R.drawable.ic_option_collapse) else painterResource(
                                id = R.drawable.ic_option_expand
                            ),
                            contentDescription = "",
                            Modifier
                                .size(height = 26.dp, width = 26.dp)
                                .padding(4.dp)
                                .clickable {
                                    coroutine.launch {
                                        expanded = !expanded
                                    }
                                }
                        )

                    }


                    if (expanded) {

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.sdp, vertical = 8.sdp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Column {

                                Text(
                                    text = stringResource(R.string.code_and_name),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = colorResource(R.color.grey_text),
                                    )
                                )

                                AutoResizedText(
                                    text = "${transferDetails!!.BENEFBANKCODE ?: ""} - ${transferDetails!!.BENEFBANKNAME ?: ""}",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                        color = Color(0xFF223142),
                                    )
                                )
                            }


                        }


                    }


                }
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.sdp, horizontal = 10.sdp),
                backgroundColor = Color.White
            ) {
                Column {


                    Row(
                        Modifier
                            .fillMaxWidth()
                            .dashedBorder(3.dp, colorResource(R.color.border_grey)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier
                                .weight(0.7f)
                                .rightVerticalDashedBorder(
                                    2.dp,
                                    colorResource(R.color.border_grey)
                                )
                                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                .fillMaxWidth()

                        ) {

                            Text(
                                text = stringResource(id = R.string.amount), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),

                                    )
                            )

                            Text(
                                text = "${Utils.formatAmountWithSpaces(transferDetails!!.AMOUNT)} ${
                                    Utils.formatCurrency(
                                        transferDetails!!.ccyType
                                    )
                                }",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFF223142),
                                )
                            )
                        }

                        Column(
                            Modifier
                                .weight(0.3f)
                                .padding(horizontal = 10.sdp, vertical = 8.sdp)
                                .fillMaxWidth()
                        ) {

                            Text(
                                text = stringResource(R.string.commission), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = "${Utils.formatAmountWithSpaces(transferDetails!!.COMMAMOUNT)} ${
                                    Utils.formatCurrency(
                                        transferDetails!!.ccyType
                                    )
                                }",
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
                                text = "Payment Purpose",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = transferDetails!!.PMTDET ?: "-",
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
                            .padding(horizontal = 10.sdp, vertical = 8.sdp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Column {

                            Text(
                                text = "Information for Beneficiary",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Text(
                                text = transferDetails!!.NOTE ?: "-",
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
                            .dashedBorder(3.dp, colorResource(R.color.border_grey))
                            .padding(horizontal = 10.sdp, vertical = 8.sdp),

                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Column {

                            Text(
                                text = stringResource(R.string.attached_pdf), style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = colorResource(R.color.grey_text),
                                )
                            )

                            Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))


                            if (transferDetails!!.PDF_LIST.isNotEmpty()) {
                                LazyRow(
                                    contentPadding = PaddingValues(
                                        vertical = 5.dp,
                                        horizontal = 5.dp
                                    )

                                ) {

                                    transferDetails.PDF_LIST.forEachIndexed { index, fileDetails ->
                                        item {
                                            PdfItem(fileDetails) {
                                                CoroutineScope(Dispatchers.IO).launch {
                                                    if (fileDetails.fileBase64 != null) {
                                                        Utils.downloadPDF(
                                                            fileDetails.fileBase64,
                                                            fileDetails.fileName
                                                        ) {
                                                            android.os.Handler(Looper.getMainLooper())
                                                                .post {
                                                                    Toast.makeText(
                                                                        context,
                                                                        it,
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }

                    }
                }

            }


            if (fileDetailList.isNotEmpty()) {
                LazyRow(
                    contentPadding = PaddingValues(
                        vertical = 5.dp,
                        horizontal = 5.dp
                    )
                ) {
                    fileDetailList.forEachIndexed { index, fileDetails ->
                        item {
                            PdfItem(fileDetails) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    if (fileDetails.fileBase64 != null) {
                                        Utils.downloadPDF(
                                            fileDetails.fileBase64,
                                            fileDetails.fileName
                                        ) {
                                            android.os.Handler(Looper.getMainLooper()).post {
                                                Toast.makeText(context, it, Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }



            if (isSigned.value) {
                CardInfo6(navController = navController) {

                    when (loginType) {
                        0 -> {
                            signingType.value = AuthType.SMS
                            signAuthSelectedIndex.value = 2

                            initSign(context)
                        }

                        1 -> {
                            signingType.value = AuthType.GOOGLE_AUTH
                            googleAuthSelectedIndex.value = 2

                            initSign(context)
                        }

                        2 -> {
                            signingType.value = AuthType.ASAN_IMZA
                            asanImzaSelectedIndex.value = 2

                            initSign(context)
                        }

                        3 -> {
                            //if user login with pin then show modal sheet for login again
                            signBottomSheet.value = true
                        }
                    }

                }
            } else {
                CardInfo5(navController = navController)
            }

            Spacer(modifier = Modifier.size(height = 20.dp, width = 1.dp))

        }

    } else {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No transaction details found!")
        }
    }


    detailsData?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false
            }

            is DataState.Success -> {
                isLoading.value = false

                val response = it.data as TransactionDetails
                detail.value = response

            }
        }
    }


    pdfList?.let {
        when (it) {
            is DataState.Loading -> {
                isLoading.value = true
            }

            is DataState.Error -> {
                isLoading.value = false

            }

            is DataState.Success -> {
                isLoading.value = false

                val data = it.data as GetPdfResponse
                data.let {
                    fileDetailList.apply {
                        clear()
                        addAll(it.data.fileDetailsList)
                    }
                }

            }
        }
    }



    BankSignBottomSheet(signBottomSheet) {
        when (it) {
            AuthType.SMS -> {

                signingType.value = it
                signAuthSelectedIndex.value = 0

                initSign(context)
            }

            AuthType.GOOGLE_AUTH -> {

                signingType.value = it
                googleAuthSelectedIndex.value = 0

                initSign(context)
            }

            AuthType.ASAN_IMZA -> {

                signingType.value = it
                asanImzaSelectedIndex.value = 0

                initSign(context)
            }

        }

    }


    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }


}

@Composable
private fun PdfItem(
    fileDetails: com.app.network.models.responseModels.FileDetails,
    onPdfClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .clickable {
                onPdfClick()
            }
    ) {

        Box(
            Modifier
                .shadow(
                    elevation = 9.dp, spotColor = Color(0xE6000000), ambientColor = Color(
                        0xE6000000
                    )
                )
                .width(56.dp)
                .height(56.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp)),

            contentAlignment = Alignment.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_option_attach_pdf),
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
        }


        Spacer(modifier = Modifier.size(height = 5.dp, width = 1.dp))

        Row {
            Text(
                text = fileDetails.fileName ?: "-",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFAEAFC9),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(100.dp)
                    .padding(horizontal = 5.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

    }

}

@Composable
private fun CardInfo5(navController: NavController) {
    val context = LocalContext.current

    Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

    Button(
        onClick = {
//            navController.popBackStack()
            (context as ComponentActivity).finish()
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF3F7FA), // Change the background color here
            contentColor = Color(0xFF203657) // Change the text color here if needed
        ),
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .fillMaxWidth()
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


}

@Composable
private fun CardInfo6(navController: NavController, onClick: () -> Unit) {

    val context = LocalContext.current

    Spacer(modifier = Modifier.size(height = 10.dp, width = 1.dp))

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
//                navController.popBackStack()
                (context as ComponentActivity).finish()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF3F7FA),
                contentColor = Color(0xFF203657)
            ),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth()
                .weight(1f)
                .border(
                    width = 2.dp,
                    color = Color(0xFF203657),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                stringResource(R.string.back),
                modifier = Modifier.padding(vertical = 10.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    shadow = null
                )
            )
        }

        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF203657),
                contentColor = Color(0xFF203657)
            ),
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth()
                .weight(1f)
                .border(
                    width = 2.dp,
                    color = Color(0xFF203657),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                "Sign",
                modifier = Modifier.padding(vertical = 10.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    shadow = null,
                    color = Color.White
                )
            )
        }
    }
}

private fun initSign(context: Context){
    val intent = Intent(context, Signing::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)

    (context as ComponentActivity).finish()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetails() {
    Details(rememberNavController())
}