package com.app.uikit.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import com.app.uikit.R
import com.app.uikit.bottomSheet.StatusModel
import com.app.uikit.bottomSheet.TypeModel
import com.app.uikit.models.AccountMenuModel
import com.app.uikit.models.CardFilters
import com.app.uikit.models.CardMenu
import com.app.uikit.models.CompanyListName
import com.app.uikit.models.CurrencyModel
import com.app.uikit.models.ExchangeRatesModel
import com.app.uikit.models.FilterModel
import com.app.uikit.models.FilterType
import com.app.uikit.models.LoansData
import com.app.uikit.models.Task
import com.app.uikit.models.TaskDate
import com.app.uikit.models.TrustsData


object DataProvider {

    val companyList =
        listOf(
            CompanyListName(
                name = "International Real Estate and Valuation Agency LLC",
            ),
            CompanyListName(name = "Value Services MMC"),
            CompanyListName(name = "Səba MMC"),
        )


    val exchangeList = listOf(
        ExchangeRatesModel(
            country_name = "USD",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "1.68",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.7025",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "1.68"
        ),
        ExchangeRatesModel(
            country_name = "EUR",
            purchase_icon = R.drawable.ic_arrow_down,
            purchase_rate = "1.947",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.98",
            bank_icon = R.drawable.ic_arrow_down,
            bank_rate = "1.947"
        ),
        ExchangeRatesModel(
            country_name = "RUB",
            purchase_icon = R.drawable.ic_arrow_up,
            purchase_rate = "0.0234",
            sale_icon = R.drawable.ic_arrow_down,
            sale_rate = "0.0244",
            bank_icon = R.drawable.ic_arrow_up,
            bank_rate = "0.0234"
        ),
        ExchangeRatesModel(
            country_name = "GBP",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "1.68",
            sale_icon = R.drawable.ic_rectangle,
            sale_rate = "1.7025",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "1.68"
        ),
        ExchangeRatesModel(
            country_name = "JPY",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "1.68",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.7025",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "1.68"
        ),
        ExchangeRatesModel(
            country_name = "UAH",
            purchase_icon = R.drawable.ic_arrow_down,
            purchase_rate = "1.947",
            sale_icon = R.drawable.ic_arrow_up,
            sale_rate = "1.98",
            bank_icon = R.drawable.ic_arrow_down,
            bank_rate = "1.947"
        ),
        ExchangeRatesModel(
            country_name = "ZEF",
            purchase_icon = R.drawable.ic_arrow_up,
            purchase_rate = "0.0234",
            sale_icon = R.drawable.ic_arrow_down,
            sale_rate = "0.0244",
            bank_icon = R.drawable.ic_arrow_up,
            bank_rate = "0.0234"
        ),
        ExchangeRatesModel(
            country_name = "CHF",
            purchase_icon = R.drawable.ic_rectangle,
            purchase_rate = "2.288",
            sale_icon = R.drawable.ic_rectangle,
            sale_rate = "2.358",
            bank_icon = R.drawable.ic_rectangle,
            bank_rate = "2.288"
        ),
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


    val loanDataList = listOf(
        LoansData(
            title = "SME Loan",
            color = Color(0xFF0FBF1B),
            snNumber = "002LCAR201270001",
            amount = "10000.00 $"
        ),

        LoansData(
            "Loan for renovation in new office",
            color = Color(0xFFFF4E57),
            snNumber = "002LCAR201270002",
            amount = "10000.00 $"
        )
    )

    val trustsDataList = listOf(
        TrustsData(
            title = "Term deposit",
            amountPrcnt = "8%",
            snNumber = "AZ47BRES38690AZ0021693229004",
            amount = "10000.00 $"
        ),

        TrustsData(
            "Term deposit",
            amountPrcnt = "8%",
            snNumber = "AZ47BRES38690AZ0021693229002",
            amount = "90000.00 $"
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
        com.app.uikit.bottomSheet.StatusModel(
            color = Color(0xFFC74375), title = "Not processed"
        ),
        com.app.uikit.bottomSheet.StatusModel(
            color = Color(0xFF88B04B), title = "Processing"
        ),
    )
    val AccountItems = listOf(
        AccountMenuModel(
            title = "AZ76BRES58380394402462924501", subTitle = "3 150 952.00 AZN", showIcon = false
        ),
        AccountMenuModel(
            title = "AZ76BRES58380394402462924501", subTitle = "3 150 952.00 AZN", showIcon = false
        ),
        AccountMenuModel(
            title = "AZ76BRES58380394402462924501", subTitle = "3 150 952.00 AZN", showIcon = true
        )
    )


    val filtersModelList = listOf(
        FilterModel(FilterType.DATE, "This Week", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.ACCOUNT, "From the account", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.TYPE, "Type", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.FIELD, "Field", Icons.Filled.Search, false),
        FilterModel(FilterType.APPOINTMENT, "Appointment", Icons.Filled.Search, false),
        FilterModel(FilterType.AMOUNT, "Amount", Icons.Filled.Search, false),
        FilterModel(FilterType.CURRENCY, "Currency", Icons.Filled.ArrowDropDown, false),
        FilterModel(FilterType.STATUS, "Status", Icons.Filled.ArrowDropDown, false)

    )

    val taskDates = listOf(
        TaskDate(
            date = "18 Februrary 2023", tasks = listOf(
                Task(
                    "GARABAG REVIVAL FUND",
                    id = 0,
                    amount = 999000000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Guliyev Ayten Samir",
                    id = 0,
                    amount = 150000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Məmmədov Məhəmməd...",
                    id = 0,
                    amount = 100000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                )
            )
        ), TaskDate(
            date = "18 Februrary 2023", tasks = listOf(
                Task(
                    "GARABAG REVIVAL FUND",
                    id = 0,
                    amount = 999000000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Guliyev Ayten Samir",
                    id = 0,
                    amount = 150000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                ),

                Task(
                    "Məmmədov Məhəmməd...",
                    id = 0,
                    amount = 100000.00,
                    status = "Execution done",
                    paymentBy = "AniPay - Non-budget",
                    timeSmall = "18:24"
                )
            )
        )

    )
}