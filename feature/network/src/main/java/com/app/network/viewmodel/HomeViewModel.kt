package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import com.app.network.data.DataState
import com.app.network.data.responseModels.GetOldCards
import com.app.network.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val repository: HomeRepository = HomeRepository()

    private val _data = MutableStateFlow<DataState<Any>?>(null)
    val data: MutableStateFlow<DataState<Any>?> get() = _data

    fun getAccounts(token: String, customerId: Int) {
        _data.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.getAccounts(token, customerId) }
                withContext(Dispatchers.Main) {
                    _data.value = DataState.Success(post.await())
                }
            } catch (e: Exception) {
                _data.value = DataState.Error(e.message.toString())
            }
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
//    fun getBalance(token: String,customerId: String){
//        _userBalance.value = DataState.Loading
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val post = async { repository.getUserBalance(token,customerId) }
//                withContext(Dispatchers.Main){
//                    _userBalance.value = DataState.Success(post.await())
//                }
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
//        }
//    }
//
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

    fun getAccountBlockByIBAN(token: String, customerId: Int, IBAN: String) {
        _data.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAccountBlockByIban(token, customerId, IBAN)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _data.value = DataState.Success(response.body()!!)
                        } else {
                            _data.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _data.value = DataState.Error(t.message.toString())
                    }
                })

//            try {
//                val post = async { repository.getAccountBlockByIban(token, customerId, IBAN) }
//                withContext(Dispatchers.Main) {
//                    _data.value = DataState.Success(post.await())
//                }
//            } catch (e: Exception) {
//                _data.value = DataState.Error(e.message.toString())
//            }
        }
    }

    fun getOldBusinessCards(customerId: Int) {
        _data.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getOldBusinessCards(customerId).enqueue(object : Callback<GetOldCards> {
                override fun onResponse(
                    call: Call<GetOldCards>,
                    response: Response<GetOldCards>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _data.value = DataState.Success(response.body()!!)
                    } else {
                        _data.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<GetOldCards>, t: Throwable) {
                    _data.value = DataState.Error(t.message.toString())
                }
            })
        }
    }

    fun getNewBusinessCards(customerId: Int) {
        _data.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            repository.getNewBusinessCards(customerId).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        _data.value = DataState.Success(response.body()!!)
                    } else {
                        _data.value = DataState.Error(response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    _data.value = DataState.Error(t.message.toString())
                }
            })
        }
    }


}