package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.helper.Error.handleException
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.DataState
import com.app.network.models.requestModels.ChangeCompanyName
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetCustomerBalance
import com.app.network.models.responseModels.GetLoans
import com.app.network.models.responseModels.GetNewCards
import com.app.network.models.responseModels.GetOldCards
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetTrusts
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val _session: Session
) : ViewModel() {

    val session get() = _session

    private val _accountsData = MutableStateFlow<DataState<Any>?>(null)
    val accountsData: MutableStateFlow<DataState<Any>?> get() = _accountsData

    private val _oldBusinessCardsData = MutableStateFlow<DataState<Any>?>(null)
    val oldBusinessCards: MutableStateFlow<DataState<Any>?> get() = _oldBusinessCardsData

    private val _newBusinessCardsData = MutableStateFlow<DataState<Any>?>(null)
    val newBusinessCards: MutableStateFlow<DataState<Any>?> get() = _newBusinessCardsData

    private val _accountBalance = MutableStateFlow<DataState<Any>?>(null)
    val accountBalance: MutableStateFlow<DataState<Any>?> get() = _accountBalance

    private val _customerLoans = MutableStateFlow<DataState<Any>?>(null)
    val customerLoans: MutableStateFlow<DataState<Any>?> get() = _customerLoans

    private val _customerTrusts = MutableStateFlow<DataState<Any>?>(null)
    val customerTrusts: MutableStateFlow<DataState<Any>?> get() = _customerTrusts

    private val _recentOps = MutableStateFlow<DataState<Any>?>(null)
    val recentOps: MutableStateFlow<DataState<Any>?> get() = _recentOps

    private val _setCustomerName = MutableStateFlow<DataState<Any>?>(null)
    val setCustomerName: MutableStateFlow<DataState<Any>?> get() = _setCustomerName

    private val _businessDate = MutableStateFlow<DataState<Any>?>(null)
    val businessDate: MutableStateFlow<DataState<Any>?> get() = _businessDate


    private val _getAccounts = MutableStateFlow<DataState<Any>?>(null)
    val accounts: MutableStateFlow<DataState<Any>?> get() = _getAccounts

    private val _getTransferCountSummary = MutableStateFlow<DataState<Any>?>(null)
    val transferCountSummary: MutableStateFlow<DataState<Any>?> get() = _getTransferCountSummary

    private val _getTransferList = MutableStateFlow<DataState<Any>?>(null)
    val transferList: MutableStateFlow<DataState<Any>?> get() = _getTransferList

    fun getAccounts(customerId: Int) {

        _accountsData.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getAccounts(session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetAccounts> {
                    override fun onResponse(
                        call: Call<GetAccounts>,
                        response: Response<GetAccounts>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _accountsData.value = DataState.Success(response.body()!!)
                        } else {
                            _accountsData.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetAccounts>, t: Throwable) {
                        _accountsData.value = DataState.Error(handleException(t))
                    }

                })
        }
    }

    fun getLoans(customerId: Int) {

        _customerLoans.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getLoans(session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetLoans> {
                    override fun onResponse(
                        call: Call<GetLoans>,
                        response: Response<GetLoans>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _customerLoans.value = DataState.Success(response.body()!!)
                        } else {
                            _customerLoans.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetLoans>, t: Throwable) {
                        _customerLoans.value = DataState.Error(handleException(t))
                    }

                })
        }
    }


    fun getTrusts(customerId: Int) {

        _customerTrusts.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getTrusts(session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetTrusts> {
                    override fun onResponse(
                        call: Call<GetTrusts>,
                        response: Response<GetTrusts>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _customerTrusts.value = DataState.Success(response.body()!!)
                        } else {
                            _customerTrusts.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetTrusts>, t: Throwable) {
                        _customerTrusts.value = DataState.Error(handleException(t))
                    }

                })
        }
    }

    fun getBalance(customerId: Int) {
        _accountBalance.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getUserBalance(session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetCustomerBalance> {
                    override fun onResponse(
                        call: Call<GetCustomerBalance>,
                        response: Response<GetCustomerBalance>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _accountBalance.value = DataState.Success(response.body()!!)
                        } else {
                            _accountBalance.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetCustomerBalance>, t: Throwable) {
                        _accountBalance.value = DataState.Error(t.message.toString())
                    }
                })
        }
    }

    fun getAccountBlockByIBAN(customerId: Int, IBAN: String) {
        _accountsData.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAccountBlockByIban(session[Keys.KEY_TOKEN]!!, customerId, IBAN)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _accountsData.value = DataState.Success(response.body()!!)
                        } else {
                            _accountsData.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _accountsData.value = DataState.Error(t.message.toString())
                    }
                })
        }
    }

    fun getOldBusinessCards(customerId: Int) {
        _oldBusinessCardsData.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getOldBusinessCards(session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetOldCards> {
                    override fun onResponse(
                        call: Call<GetOldCards>,
                        response: Response<GetOldCards>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _oldBusinessCardsData.value = DataState.Success(response.body()!!)
                        } else {
                            _oldBusinessCardsData.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetOldCards>, t: Throwable) {
                        _oldBusinessCardsData.value = DataState.Error(handleException(t))
                    }
                })
        }
    }

    fun getNewBusinessCards(customerId: Int) {
        _newBusinessCardsData.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getNewBusinessCards(session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetNewCards> {
                    override fun onResponse(
                        call: Call<GetNewCards>,
                        response: Response<GetNewCards>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _newBusinessCardsData.value = DataState.Success(response.body()!!)
                        } else {
                            _newBusinessCardsData.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetNewCards>, t: Throwable) {
                        _newBusinessCardsData.value = DataState.Error(handleException(t))
                    }
                })
        }
    }

    fun getRecentOps(customerId: Int) {
        _recentOps.value = DataState.Loading

        viewModelScope.launch {
            repository.getRecentOps(session[Keys.KEY_TOKEN]!!, customerId)
                .enqueue(object : Callback<GetRecentOps> {
                    override fun onResponse(
                        call: Call<GetRecentOps>,
                        response: Response<GetRecentOps>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _recentOps.value = DataState.Success(response.body()!!)
                        } else {
                            _recentOps.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetRecentOps>, t: Throwable) {
                        _recentOps.value = DataState.Error(handleException(t))
                    }

                })
        }
    }

    fun setCustomerName(changeCompanyName: ChangeCompanyName) {
        _setCustomerName.value = DataState.Loading

        viewModelScope.launch {

            repository.setCustomerName(session[Keys.KEY_TOKEN]!!, changeCompanyName)
                .enqueue(object : Callback<LoginVerifyResponse> {
                    override fun onResponse(
                        call: Call<LoginVerifyResponse>,
                        response: Response<LoginVerifyResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _setCustomerName.value = DataState.Success(response.body()!!)
                        } else {
                            _setCustomerName.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<LoginVerifyResponse>, t: Throwable) {
                        _setCustomerName.value = DataState.Error(handleException(t))
                    }

                })

        }
    }


    fun getBusinessDate() {
        _businessDate.value = DataState.Loading

        viewModelScope.launch {

            repository.getBusinessDate(session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _businessDate.value = DataState.Success(response.body()!!)
                        } else {
                            _businessDate.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _businessDate.value = DataState.Error(handleException(t))
                    }

                })

        }
    }


    fun getAccounts() {
        _getAccounts.value = DataState.Loading

        viewModelScope.launch {

            repository.getAccounts(session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getAccounts.value = DataState.Success(response.body()!!)
                        } else {
                            _getAccounts.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _getAccounts.value = DataState.Error(handleException(t))
                    }

                })

        }
    }


    fun getTransferCountSummary(startDate:String,endDate:String) {
        _getTransferCountSummary.value = DataState.Loading

        viewModelScope.launch {

            repository.getTransferCountSummary(session[Keys.KEY_TOKEN]!!,startDate, endDate)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getTransferCountSummary.value = DataState.Success(response.body()!!)
                        } else {
                            _getTransferCountSummary.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _getTransferCountSummary.value = DataState.Error(handleException(t))
                    }

                })

        }
    }

    fun getTransferList(dateStart: String, dateEnd: String, page: Int) {
        _getTransferList.value = DataState.Loading

        viewModelScope.launch {

            repository.getTransferList(session[Keys.KEY_TOKEN]!!,dateStart,dateEnd,page)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getTransferList.value = DataState.Success(response.body()!!)
                        } else {
                            _getTransferList.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _getTransferList.value = DataState.Error(handleException(t))
                    }

                })

        }
    }


}