package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetAccounts
import com.app.network.data.responseModels.GetCustomerBalance
import com.app.network.data.responseModels.GetLoans
import com.app.network.data.responseModels.GetNewCards
import com.app.network.data.responseModels.GetOldCards
import com.app.network.data.responseModels.GetRecentOps
import com.app.network.data.responseModels.GetTrusts
import com.app.network.helper.Error.handleException
import com.app.network.helper.Keys
import com.app.network.helper.MainApp
import com.app.network.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HomeViewModel : ViewModel() {

    private val repository: HomeRepository = HomeRepository()

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

    fun getAccounts(customerId: Int) {

        _accountsData.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getAccounts(MainApp.session[Keys.KEY_TOKEN]!!, customerId)
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

            repository.getLoans(MainApp.session[Keys.KEY_TOKEN]!!, customerId)
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

            repository.getTrusts(MainApp.session[Keys.KEY_TOKEN]!!, customerId)
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

    fun getBalance(customerId: Int){
        _accountBalance.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getUserBalance(MainApp.session[Keys.KEY_TOKEN]!!, customerId)
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
            repository.getAccountBlockByIban(MainApp.session[Keys.KEY_TOKEN]!!, customerId, IBAN)
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
            repository.getOldBusinessCards(MainApp.session[Keys.KEY_TOKEN]!!, customerId).enqueue(object : Callback<GetOldCards> {
                override fun onResponse(
                    call: Call<GetOldCards>,
                    response: Response<GetOldCards>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _oldBusinessCardsData.value = DataState.Success(response.body()!!)
                    } else {
                        _oldBusinessCardsData.value = DataState.Error(response.errorBody()!!.string())
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
            repository.getNewBusinessCards(MainApp.session[Keys.KEY_TOKEN]!!, customerId).enqueue(object : Callback<GetNewCards> {
                override fun onResponse(
                    call: Call<GetNewCards>,
                    response: Response<GetNewCards>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _newBusinessCardsData.value = DataState.Success(response.body()!!)
                    } else {
                        _newBusinessCardsData.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<GetNewCards>, t: Throwable) {
                    _newBusinessCardsData.value = DataState.Error(handleException(t))
                }
            })
        }
    }

    fun getRecentOps(customerId: Int){
        _recentOps.value = DataState.Loading

        viewModelScope.launch {
            repository.getRecentOps(MainApp.session[Keys.KEY_TOKEN]!!, customerId).enqueue(object :Callback<GetRecentOps>{
                override fun onResponse(
                    call: Call<GetRecentOps>,
                    response: Response<GetRecentOps>
                ) {
                    if (response.isSuccessful && response.body() != null){
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



}