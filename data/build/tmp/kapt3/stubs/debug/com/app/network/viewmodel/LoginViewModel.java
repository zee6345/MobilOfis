package com.app.network.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u000e\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/app/network/viewmodel/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "loginUseCase", "Lcom/app/network/useCase/LoginUseCase;", "(Lcom/app/network/useCase/LoginUseCase;)V", "_data", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/app/network/model/DataState;", "", "changePassword", "Landroidx/lifecycle/MutableLiveData;", "Lcom/app/network/model/responseModels/ChangePasswordResponse;", "getChangePassword", "()Landroidx/lifecycle/MutableLiveData;", "changePasswordVerification", "Lcom/app/network/model/responseModels/VerifyChangePasswordResponse;", "getChangePasswordVerification", "data", "getData", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "loginAsan", "Lcom/app/network/model/responseModels/LoginAsanResponse;", "getLoginAsan", "", "changePasswordRequest", "Lcom/app/network/model/callModels/ChangePasswordRequest;", "verifyChangePasswordRequest", "Lcom/app/network/model/callModels/VerifyChangePasswordRequest;", "loginAsanRequest", "Lcom/app/network/model/callModels/LoginAsanRequest;", "loginAuthVerification", "loginVerificationRequest", "Lcom/app/network/model/callModels/LoginVerificationRequest;", "loginWithUserName", "loginRequest", "Lcom/app/network/model/callModels/LoginRequest;", "data_debug"})
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    private final com.app.network.useCase.LoginUseCase loginUseCase = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.app.network.model.DataState<java.lang.Object>> _data = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.app.network.model.responseModels.LoginAsanResponse> loginAsan = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.app.network.model.responseModels.ChangePasswordResponse> changePassword = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.app.network.model.responseModels.VerifyChangePasswordResponse> changePasswordVerification = null;
    
    @javax.inject.Inject
    public LoginViewModel(@org.jetbrains.annotations.NotNull
    com.app.network.useCase.LoginUseCase loginUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.MutableStateFlow<com.app.network.model.DataState<java.lang.Object>> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.app.network.model.responseModels.LoginAsanResponse> getLoginAsan() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.app.network.model.responseModels.ChangePasswordResponse> getChangePassword() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.app.network.model.responseModels.VerifyChangePasswordResponse> getChangePasswordVerification() {
        return null;
    }
    
    public final void loginWithUserName(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.LoginRequest loginRequest) {
    }
    
    public final void loginAuthVerification(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.LoginVerificationRequest loginVerificationRequest) {
    }
    
    public final void loginAsan(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.LoginAsanRequest loginAsanRequest) {
    }
    
    public final void changePassword(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.ChangePasswordRequest changePasswordRequest) {
    }
    
    public final void changePasswordVerification(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.VerifyChangePasswordRequest verifyChangePasswordRequest) {
    }
}