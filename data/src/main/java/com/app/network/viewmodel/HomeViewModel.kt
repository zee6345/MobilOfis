package com.app.network.viewmodel

import androidx.lifecycle.ViewModel
import com.app.network.helper.Error.handleException
import com.app.network.helper.Keys
import com.app.network.helper.Session
import com.app.network.models.DataState
import com.app.network.models.requestModels.ChangeCompanyName
import com.app.network.models.requestModels.GetPdfList
import com.app.network.models.requestModels.SendToBankModel
import com.app.network.models.requestModels.SignApproveRequest
import com.app.network.models.responseModels.GetAccountBlocks
import com.app.network.models.responseModels.GetAccounts
import com.app.network.models.responseModels.GetCustomerBalance
import com.app.network.models.responseModels.GetLoans
import com.app.network.models.responseModels.GetNewCards
import com.app.network.models.responseModels.GetOldCards
import com.app.network.models.responseModels.GetPdfResponse
import com.app.network.models.responseModels.GetRecentOps
import com.app.network.models.responseModels.GetTransactionDetails
import com.app.network.models.responseModels.GetTrusts
import com.app.network.models.responseModels.GetUserRoles
import com.app.network.models.responseModels.LoginVerifyResponse
import com.app.network.models.responseModels.SignApproveResponse
import com.app.network.models.responseModels.TransactionDetails
import com.app.network.models.responseModels.transferModels.TransferCountSummaryResponse
import com.app.network.models.responseModels.transferModels.TransferListResponse
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

    private val _accountsBlockByIban = MutableStateFlow<DataState<Any>?>(null)
    val accountsBlockByIban: MutableStateFlow<DataState<Any>?> get() = _accountsBlockByIban

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


    private val _getTransferCountSummary = MutableStateFlow<DataState<Any>?>(null)
    val getTransferCountSummary: MutableStateFlow<DataState<Any>?> get() = _getTransferCountSummary

    private val _getTransferList = MutableStateFlow<DataState<Any>?>(null)
    val transferList: MutableStateFlow<DataState<Any>?> get() = _getTransferList

    private val _getTransactionDetails = MutableStateFlow<DataState<Any>?>(null)
    val getTransactionDetails: MutableStateFlow<DataState<Any>?> get() = _getTransactionDetails

    private val _getTransactionPdf = MutableStateFlow<DataState<Any>?>(null)
    val getTransactionPdf: MutableStateFlow<DataState<Any>?> get() = _getTransactionPdf

    private val _getSignOrApprove = MutableStateFlow<DataState<Any>?>(null)
    val getSignOrApprove: MutableStateFlow<DataState<Any>?> get() = _getSignOrApprove

    private val _sendToBank = MutableStateFlow<DataState<Any>?>(null)
    val sendToBank: MutableStateFlow<DataState<Any>?> get() = _sendToBank

    private val _transactionStatus = MutableStateFlow<DataState<Any>?>(null)
    val getTransactionStatus: MutableStateFlow<DataState<Any>?> get() = _transactionStatus

    private val _userRoles = MutableStateFlow<DataState<Any>?>(null)
    val getUserRoles: MutableStateFlow<DataState<Any>?> get() = _userRoles


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
        _accountsBlockByIban.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.getAccountBlockByIban(session[Keys.KEY_TOKEN]!!, customerId, IBAN)
                .enqueue(object : Callback<GetAccountBlocks> {
                    override fun onResponse(
                        call: Call<GetAccountBlocks>,
                        response: Response<GetAccountBlocks>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _accountsBlockByIban.value = DataState.Success(response.body()!!)
                        } else {
                            _accountsBlockByIban.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetAccountBlocks>, t: Throwable) {
                        _accountsBlockByIban.value = DataState.Error(t.message.toString())
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

    fun getRecentOps(customerId: Int, incomeFlag: String) {
        _recentOps.value = DataState.Loading

        if (incomeFlag.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.getRecentOps(session[Keys.KEY_TOKEN]!!, customerId, incomeFlag)
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
        } else {
            CoroutineScope(Dispatchers.IO).launch {
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
    }

    fun setCustomerName(changeCompanyName: ChangeCompanyName) {
        _setCustomerName.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

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

        CoroutineScope(Dispatchers.IO).launch {

            repository.getBusinessDate(session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _businessDate.value = DataState.Success(response.body()!!.string())
                        } else {
                            _businessDate.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        _businessDate.value = DataState.Error(handleException(t))
                    }

                })

        }
    }

    fun getTransferCountSummary(startDate: String, endDate: String) {
        _getTransferCountSummary.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getTransferCountSummary(session[Keys.KEY_TOKEN]!!, startDate, endDate)
                .enqueue(object : Callback<TransferCountSummaryResponse> {
                    override fun onResponse(
                        call: Call<TransferCountSummaryResponse>,
                        response: Response<TransferCountSummaryResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getTransferCountSummary.value = DataState.Success(response.body()!!)
                        } else {
                            _getTransferCountSummary.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<TransferCountSummaryResponse>, t: Throwable) {
                        _getTransferCountSummary.value = DataState.Error(handleException(t))
                    }

                })

        }
    }

    fun getTransferList(dateStart: String, dateEnd: String, page: Int) {
        _getTransferList.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            repository.getTransferList(session[Keys.KEY_TOKEN]!!, dateStart, dateEnd, page)
                .enqueue(object : Callback<TransferListResponse> {
                    override fun onResponse(
                        call: Call<TransferListResponse>,
                        response: Response<TransferListResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getTransferList.value = DataState.Success(response.body()!!)
                        } else {
                            _getTransferList.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<TransferListResponse>, t: Throwable) {
                        _getTransferList.value = DataState.Error(handleException(t))
                    }

                })

        }
    }

    fun getUserRoles() {
        _userRoles.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.userRoles(session[Keys.KEY_TOKEN]!!)
                .enqueue(object : Callback<GetUserRoles> {
                    override fun onResponse(
                        call: Call<GetUserRoles>,
                        response: Response<GetUserRoles>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _userRoles.value = DataState.Success(response.body()!!)
                        } else {
                            _userRoles.value = DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetUserRoles>, t: Throwable) {
                        _userRoles.value = DataState.Error(handleException(t))
                    }
                })
        }
    }

    fun getTransferPdfList(getPdfList: GetPdfList) {
        _getTransactionPdf.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.getTransferPdfList(session[Keys.KEY_TOKEN]!!, getPdfList)
                .enqueue(object : Callback<GetPdfResponse> {
                    override fun onResponse(
                        call: Call<GetPdfResponse>,
                        response: Response<GetPdfResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getTransactionPdf.value = DataState.Success(response.body()!!)
                        } else {
                            _getTransactionPdf.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<GetPdfResponse>, t: Throwable) {
                        _getTransactionPdf.value = DataState.Error(handleException(t))
                    }

                })
        }
    }

    fun getTransactionDetails(ibankRef: String) {
        _getTransactionDetails.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.getTransactionDetails(session[Keys.KEY_TOKEN]!!, ibankRef)
                .enqueue(object : Callback<TransactionDetails> {
                    override fun onResponse(
                        call: Call<TransactionDetails>,
                        response: Response<TransactionDetails>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getTransactionDetails.value =
                                DataState.Success(response.body()!!)

                        } else {
                            _getTransactionDetails.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<TransactionDetails>, t: Throwable) {
                        _getTransactionDetails.value = DataState.Error(handleException(t))
                    }

                })

        }


    }

    fun signOrApprove(signApproveRequest: SignApproveRequest) {
        _getSignOrApprove.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.signOrApprove(session[Keys.KEY_TOKEN]!!, signApproveRequest)
                .enqueue(object : Callback<SignApproveResponse> {
                    override fun onResponse(
                        call: Call<SignApproveResponse>,
                        response: Response<SignApproveResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _getSignOrApprove.value =
                                DataState.Success(response.body()!!)

                        } else {
                            _getSignOrApprove.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<SignApproveResponse>, t: Throwable) {
                        _getSignOrApprove.value = DataState.Error(handleException(t))
                    }

                })
        }
    }

    fun transactionStatus(code: Int) {
        _transactionStatus.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.transactionStatus(session[Keys.KEY_TOKEN]!!, code)
                .enqueue(object : Callback<SignApproveResponse> {
                    override fun onResponse(
                        call: Call<SignApproveResponse>,
                        response: Response<SignApproveResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _transactionStatus.value =
                                DataState.Success(response.body()!!)

                        } else {
                            _transactionStatus.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<SignApproveResponse>, t: Throwable) {
                        _transactionStatus.value = DataState.Error(handleException(t))
                    }

                })
        }
    }

    fun sendToBankAPI(sendToBankModel: SendToBankModel) {
        _sendToBank.value = DataState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            repository.sendToBankAPI(session[Keys.KEY_TOKEN]!!, sendToBankModel)
                .enqueue(object : Callback<SignApproveResponse> {
                    override fun onResponse(
                        call: Call<SignApproveResponse>,
                        response: Response<SignApproveResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            _sendToBank.value =
                                DataState.Success(response.body()!!)

                        } else {
                            _sendToBank.value =
                                DataState.Error(response.errorBody()!!.string())
                        }
                    }

                    override fun onFailure(call: Call<SignApproveResponse>, t: Throwable) {
                        _sendToBank.value = DataState.Error(handleException(t))
                    }

                })
        }
    }
}