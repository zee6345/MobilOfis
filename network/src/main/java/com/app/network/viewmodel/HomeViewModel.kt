package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetAccounts
import com.app.network.data.responseModels.GetCustomerBalance
import com.app.network.data.responseModels.GetNewCards
import com.app.network.data.responseModels.GetOldCards
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



//    fun getLastLogin(token:String){
//        _lastLoginResponse.value = DataState.Loading
//        CoroutineScope(Dispatchers.IO).launch {
//            try{
//
//                // for reference its response data model is LastLoginTime
//                val post = async { repository.getLastLogin(token) }
//                withContext(Dispatchers.Main){
//                    _lastLoginResponse.value = DataState.Success(post.await())
//                }
//            } catch (e:Exception){
//                e.printStackTrace()
//                _lastLoginResponse.value = DataState.Error(e.message.toString())
//            }
//        }
//    }

    //    fun getUserInfo(token: String,customerNo: String){
//        _userInfo.value = DataState.Loading
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val post = async { repository.getUserInfo(token,customerNo) }
//                withContext(Dispatchers.Main){
//                    _userInfo.value = DataState.Success(post.await())
//                }
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
//        }
//    }
//
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

//    fun setUserNickName(token: String,accountNickNameRequest: AccountNickNameRequest){
//        _setAccountNickNameResponse.value = DataState.Loading
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val post = async { repository.setUserNickName(token,accountNickNameRequest) }
//                withContext(Dispatchers.Main){
//                    _setAccountNickNameResponse.value = DataState.Success(post.await())
//                }
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
//        }
//    }

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

    private fun handleException(throwable: Throwable):String {
        return when (throwable) {
            is ConnectException -> {
                "no internet connection"
            }

            is SocketTimeoutException -> {
                "connection timeout"
            }

            is UnknownHostException -> {
                "failed to reached network"
            }

            is HttpException -> {
                when (throwable.code()) {
                    401 -> {
                        // HTTP 401 Unauthorized: Invalid credentials
                        "Unauthorized: Invalid credentials"
                    }
                    403 -> {
                        // HTTP 403 Forbidden: Access denied
                        "Forbidden: Access denied"
                    }
                    404 -> {
                        // HTTP 404 Not Found: Requested resource not found
                        "Not Found: Requested resource not found"
                    }
                    // Add more cases for other HTTP error codes if needed
                    else -> {
                        // Handle other HTTP error codes with a generic message
                        "Failed to connect to server"
                    }
                }
            }

            else -> {
                ""
            }
        }
    }

}