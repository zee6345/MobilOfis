package com.app.network.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tJ!\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00062\u0006\u0010\n\u001a\u00020\u000bJ!\u0010\u0015\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u0017\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lcom/app/network/repository/HomeRepository;", "", "apiService", "Lcom/app/network/apiService/APIService;", "(Lcom/app/network/apiService/APIService;)V", "getAccountBlockByIban", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "token", "", "customerId", "", "iban", "getAccounts", "Lcom/app/network/model/responseModels/GetAccounts;", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastLogin", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNewBusinessCards", "getOldBusinessCards", "Lcom/app/network/model/responseModels/GetOldCards;", "getUserBalance", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserInfo", "customerNo", "setUserNickName", "accountNickNameRequest", "Lcom/app/network/model/callModels/AccountNickNameRequest;", "(Ljava/lang/String;Lcom/app/network/model/callModels/AccountNickNameRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class HomeRepository {
    private final com.app.network.apiService.APIService apiService = null;
    
    @javax.inject.Inject
    public HomeRepository(@org.jetbrains.annotations.NotNull
    com.app.network.apiService.APIService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAccounts(@org.jetbrains.annotations.NotNull
    java.lang.String token, int customerId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.GetAccounts> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getLastLogin(@org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUserInfo(@org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String customerNo, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUserBalance(@org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String customerId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setUserNickName(@org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.AccountNickNameRequest accountNickNameRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final retrofit2.Call<okhttp3.ResponseBody> getAccountBlockByIban(@org.jetbrains.annotations.NotNull
    java.lang.String token, int customerId, @org.jetbrains.annotations.NotNull
    java.lang.String iban) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final retrofit2.Call<com.app.network.model.responseModels.GetOldCards> getOldBusinessCards(int customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final retrofit2.Call<okhttp3.ResponseBody> getNewBusinessCards(int customerId) {
        return null;
    }
}