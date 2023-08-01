package com.app.network.retrofitClient;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\u0013"}, d2 = {"Lcom/app/network/retrofitClient/BaseRetrofitClient;", "", "()V", "apiService", "Lcom/app/network/apiService/APIService;", "getApiService", "()Lcom/app/network/apiService/APIService;", "setApiService", "(Lcom/app/network/apiService/APIService;)V", "apiServiceAuthInterceptor", "getApiServiceAuthInterceptor", "setApiServiceAuthInterceptor", "client", "Lretrofit2/Retrofit;", "httpClient", "Lokhttp3/OkHttpClient;", "createApiService", "createApiServiceAuthIntercepter", "Companion", "data_debug"})
public final class BaseRetrofitClient {
    @org.jetbrains.annotations.NotNull
    public static final com.app.network.retrofitClient.BaseRetrofitClient.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static java.lang.String AUTH_TOKEN = "testing";
    public com.app.network.apiService.APIService apiService;
    public com.app.network.apiService.APIService apiServiceAuthInterceptor;
    
    public BaseRetrofitClient() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.apiService.APIService getApiService() {
        return null;
    }
    
    public final void setApiService(@org.jetbrains.annotations.NotNull
    com.app.network.apiService.APIService p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.apiService.APIService getApiServiceAuthInterceptor() {
        return null;
    }
    
    public final void setApiServiceAuthInterceptor(@org.jetbrains.annotations.NotNull
    com.app.network.apiService.APIService p0) {
    }
    
    private final com.app.network.apiService.APIService createApiService() {
        return null;
    }
    
    private final retrofit2.Retrofit client(okhttp3.OkHttpClient httpClient) {
        return null;
    }
    
    private final com.app.network.apiService.APIService createApiServiceAuthIntercepter() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/app/network/retrofitClient/BaseRetrofitClient$Companion;", "", "()V", "AUTH_TOKEN", "", "getAUTH_TOKEN", "()Ljava/lang/String;", "setAUTH_TOKEN", "(Ljava/lang/String;)V", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getAUTH_TOKEN() {
            return null;
        }
        
        public final void setAUTH_TOKEN(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
    }
}