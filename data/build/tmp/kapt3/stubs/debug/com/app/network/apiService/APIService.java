package com.app.network.apiService;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0001\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\'J\u001b\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\u000e2\b\b\u0001\u0010\u0019\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\'J\u0018\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\r2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\'J\u001b\u0010\u001d\u001a\u00020\u000e2\b\b\u0001\u0010\u001e\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u001b\u0010\u001f\u001a\u00020 2\b\b\u0001\u0010!\u001a\u00020\"H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u001b\u0010$\u001a\u00020%2\b\b\u0001\u0010$\u001a\u00020&H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J\u001b\u0010(\u001a\u00020)2\b\b\u0001\u0010*\u001a\u00020+H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010,J\u001b\u0010-\u001a\u00020\u000e2\b\b\u0001\u0010.\u001a\u00020/H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00061"}, d2 = {"Lcom/app/network/apiService/APIService;", "", "changePassword", "Lcom/app/network/model/responseModels/ChangePasswordResponse;", "changePasswordRequest", "Lcom/app/network/model/callModels/ChangePasswordRequest;", "(Lcom/app/network/model/callModels/ChangePasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "changePasswordVerify", "Lcom/app/network/model/responseModels/VerifyChangePasswordResponse;", "verifyChangePasswordRequest", "Lcom/app/network/model/callModels/VerifyChangePasswordRequest;", "(Lcom/app/network/model/callModels/VerifyChangePasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountBlockByIBAN", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "customerId", "", "iban", "", "getAccounts", "Lcom/app/network/model/responseModels/GetAccounts;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBalance", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastLogin", "token", "getNewBusinessCards", "getOldBusinessCards", "Lcom/app/network/model/responseModels/GetOldCards;", "getUserInfo", "customerNo", "loginAsan", "Lcom/app/network/model/responseModels/LoginAsanResponse;", "loginAsanRequest", "Lcom/app/network/model/callModels/LoginAsanRequest;", "(Lcom/app/network/model/callModels/LoginAsanRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginVerification", "Lcom/app/network/model/responseModels/LoginVerifyResponse;", "Lcom/app/network/model/callModels/LoginVerificationRequest;", "(Lcom/app/network/model/callModels/LoginVerificationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginWithUserName", "Lcom/app/network/model/responseModels/LoginResponse;", "loginBody", "Lcom/app/network/model/callModels/LoginRequest;", "(Lcom/app/network/model/callModels/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAccountNickName", "accountNickNameRequest", "Lcom/app/network/model/callModels/AccountNickNameRequest;", "(Lcom/app/network/model/callModels/AccountNickNameRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface APIService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "auth/login")
    public abstract java.lang.Object loginWithUserName(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.app.network.model.callModels.LoginRequest loginBody, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.LoginResponse> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "auth/login/verify")
    public abstract java.lang.Object loginVerification(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.app.network.model.callModels.LoginVerificationRequest loginVerification, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.LoginVerifyResponse> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "auth/asan")
    public abstract java.lang.Object loginAsan(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.app.network.model.callModels.LoginAsanRequest loginAsanRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.LoginAsanResponse> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "auth/changepassword")
    public abstract java.lang.Object changePassword(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.app.network.model.callModels.ChangePasswordRequest changePasswordRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.ChangePasswordResponse> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "auth/changepassword/verify")
    public abstract java.lang.Object changePasswordVerify(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.VerifyChangePasswordRequest verifyChangePasswordRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.VerifyChangePasswordResponse> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "customers/{customerId}/accounts")
    public abstract java.lang.Object getAccounts(@retrofit2.http.Path(value = "customerId")
    int customerId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.GetAccounts> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "customers/{customerId}/accounts/nickname")
    public abstract java.lang.Object setAccountNickName(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.app.network.model.callModels.AccountNickNameRequest accountNickNameRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "customers/{customerId}/balance")
    public abstract java.lang.Object getBalance(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Path(value = "customerId")
    java.lang.String customerId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "auth/getlastlogin")
    public abstract java.lang.Object getLastLogin(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "auth_token")
    java.lang.String token, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "auth/getuserinfo")
    public abstract java.lang.Object getUserInfo(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "customerNo")
    java.lang.String customerNo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "customers/{customerId}/accounts/blocksbyiban/{iban}")
    public abstract retrofit2.Call<okhttp3.ResponseBody> getAccountBlockByIBAN(@retrofit2.http.Path(value = "customerId")
    int customerId, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Path(value = "iban")
    java.lang.String iban);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "brcards/get-old-business-cards-by-cif/{customerId}")
    public abstract retrofit2.Call<com.app.network.model.responseModels.GetOldCards> getOldBusinessCards(@retrofit2.http.Path(value = "customerId")
    int customerId);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "brcards/get-new-business-cards-by-cif/{customerId}")
    public abstract retrofit2.Call<okhttp3.ResponseBody> getNewBusinessCards(@retrofit2.http.Path(value = "customerId")
    int customerId);
}