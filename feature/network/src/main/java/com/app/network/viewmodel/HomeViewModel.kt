package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import com.app.network.data.DataState
import com.app.network.data.callModels.AccountNickNameRequest
import com.app.network.data.callModels.LoginRequest
import com.app.network.repository.HomeRepository
import com.app.network.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel:ViewModel() {

    private val repository: HomeRepository = HomeRepository()

    private val _data = MutableStateFlow<DataState<Any>?>(null)
    private val _lastLoginResponse = MutableStateFlow<DataState<Any>?>(null)
    val data: MutableStateFlow<DataState<Any>?> get() = _data
    val lastLogin : MutableStateFlow<DataState<Any>?> get() = _lastLoginResponse

    private val _userInfo = MutableStateFlow<DataState<Any>?>(null)
    val userInfo : MutableStateFlow<DataState<Any>?> get() = _userInfo

    private val _userBalance = MutableStateFlow<DataState<Any>?>(null)
    val userBalance : MutableStateFlow<DataState<Any>?> get() = _userBalance


    private val _setAccountNickNameResponse = MutableStateFlow<DataState<Any>?>(null)
    val setAccountNickNameResponse : MutableStateFlow<DataState<Any>?> get() = _setAccountNickNameResponse


    private val _getAccountBlockIBAN = MutableStateFlow<DataState<Any>?>(null)
    val getAccountBlockIBAN : MutableStateFlow<DataState<Any>?> get() = _getAccountBlockIBAN

    fun getAccounts(token:String, customerId: Int) {
        _data.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.getAccounts(token, customerId) }
                withContext(Dispatchers.Main) {
                    _data.value = DataState.Success(post.await())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _data.value = DataState.Error(e.message.toString())
            }
        }
    }

    fun getLastLogin(token:String){
        _lastLoginResponse.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            try{

                // for reference its response data model is LastLoginTime
                val post = async { repository.getLastLogin(token) }
                withContext(Dispatchers.Main){
                    _lastLoginResponse.value = DataState.Success(post.await())
                }
            } catch (e:Exception){
                e.printStackTrace()
                _lastLoginResponse.value = DataState.Error(e.message.toString())
            }
        }
    }

    fun getUserInfo(token: String,customerNo: String){
        _userInfo.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.getUserInfo(token,customerNo) }
                withContext(Dispatchers.Main){
                    _userInfo.value = DataState.Success(post.await())
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getBalance(token: String,customerId: String){
        _userBalance.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.getUserBalance(token,customerId) }
                withContext(Dispatchers.Main){
                    _userBalance.value = DataState.Success(post.await())
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun setUserNickName(token: String,accountNickNameRequest: AccountNickNameRequest){
        _setAccountNickNameResponse.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.setUserNickName(token,accountNickNameRequest) }
                withContext(Dispatchers.Main){
                    _setAccountNickNameResponse.value = DataState.Success(post.await())
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getAccountBlockByIBAN(token: String,customerId: String,IBAN:String) {
        _getAccountBlockIBAN.value = DataState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val post = async { repository.getAccountBlockByIban(token, customerId,IBAN) }
                withContext(Dispatchers.Main) {
                    _getAccountBlockIBAN.value = DataState.Success(post.await())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}