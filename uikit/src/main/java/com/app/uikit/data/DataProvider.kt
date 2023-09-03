package com.app.uikit.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import com.app.uikit.R
import com.app.uikit.bottomSheet.StatusModel
import com.app.uikit.bottomSheet.TypeModel
import com.app.uikit.models.CardFilters
import com.app.uikit.models.CardMenu
import com.app.uikit.models.CompanyListName
import com.app.uikit.models.CurrencyModel
import com.app.uikit.models.DateType
import com.app.uikit.models.DurationDateModel
import com.app.uikit.models.FilterModel
import com.app.uikit.models.FilterType


object DataProvider {

    val companyList =
        listOf(
            CompanyListName(
                name = "International Real Estate and Valuation Agency LLC",
            ),
            CompanyListName(name = "Value Services MMC"),
            CompanyListName(name = "Səba MMC"),
        )


    val filtersList = listOf(
        CardFilters(
            "Account name and IBAN",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "User",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "Status",
            R.drawable.ic_filter_drop_down
        ),


        CardFilters(
            "End date",
            R.drawable.ic_filter_sort
        ),

        CardFilters(
            "Balance",
            R.drawable.ic_filter_sort
        ),

        )

    val filtersRecentList = listOf(
        CardFilters(
            "All",
            null
        ),

        CardFilters(
            "Income",
            null
        ),

        CardFilters(
            "Expenditure",
            null
        ),


        CardFilters(
            "History",
            R.drawable.ic_filter_drop_down
        ),

        CardFilters(
            "Sender/Recipient",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "Amount",
            R.drawable.ic_filter_drop_down
        ),

        )


    val filtersLoanList = listOf(
        CardFilters(
            "N of the loan agreement",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "Currency",
            R.drawable.ic_filter_drop_down
        )
    )


    val filtersTrustsList = listOf(
        CardFilters(
            "Deposit name",
            R.drawable.ic_filter_search
        ),

        CardFilters(
            "Account number",
            R.drawable.ic_filter_search
        ),
        CardFilters(
            "Currency",
            R.drawable.ic_filter_drop_down
        )
    )


    val menuList = listOf(
        CardMenu(
            title = "Signature\n" +
                    "Waiting",
            color = Color(R.color.blueColor),
            number = 3
        ),
        CardMenu(
            title = "Time\n" +
                    "past",
            color = Color(0xff916FDD),
            number = 1
        ),
        CardMenu(
            title = "Sent to\n" +
                    "Bank",
            color = Color(0xffF48A1D),
            number = 1
        ),
        CardMenu(
            title = "Emal\nolunmadri",
            color = Color(0xFFC74375),
            number = 1
        ),
        CardMenu(
            title = "Silinib",
            color = Color(0xFF939597),
            number = 1
        )
    )

    val currencyModelList = listOf(
        CurrencyModel("ALL"),
        CurrencyModel("AZN"),
        CurrencyModel("USD"),
        CurrencyModel("EUR"),

        )

    val TypeModelList = listOf(
        TypeModel("IM", " - To my own account within the bank"),
        TypeModel("IT", " - To another account within the bank"),
        TypeModel("FX", " - Currency exchange"),
        TypeModel("AZ", " - AZIPS"),
        TypeModel("XO", " - XOHKS"),
        TypeModel("XT", " - Budget - DXA"),
    )

    val StatusList = listOf(
        StatusModel(
            color = Color(0xFF26D978), title = "Executed"
        ),
        StatusModel(
            color = Color(0xFF939597), title = "Deleted"
        ),
        StatusModel(
            color = Color(0xFF916FDD), title = "Expired date"
        ),
        StatusModel(
            color = Color(0xFFFF4E57), title = "Refused"
        ),
        StatusModel(
            color = Color(0xFFD7C20A), title = "Sent to the bank"
        ),
        StatusModel(
            color = Color(0xFFC74375), title = "Not processed"
        ),
        StatusModel(
            color = Color(0xFF88B04B), title = "Processing"
        ),
    )


    val filtersModelList = listOf(
        FilterModel(FilterType.DATE, "Duration", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.STATUS, "Status", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.ACCOUNT, "From the account", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.TYPE, "Type", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.FIELD, "Beneficiary", Icons.Filled.Search, false),
        FilterModel(FilterType.APPOINTMENT, "Details", Icons.Filled.Search, false),
        FilterModel(FilterType.AMOUNT, "Amount", Icons.Filled.Search, false),
        FilterModel(FilterType.CURRENCY, "Currency", Icons.Filled.ArrowDropDown, false),

    )


    val filterDateList = listOf(
        DurationDateModel("Today", DateType.TODAY),
        DurationDateModel("Yesterday", DateType.YESTERDAY),
        DurationDateModel("This Week", DateType.THIS_WEEK),
        DurationDateModel("Last Week", DateType.LAST_WEEK),
        DurationDateModel("This Month", DateType.THIS_MONTH),
        DurationDateModel("Last Month", DateType.LAST_MONTH),
    )

}