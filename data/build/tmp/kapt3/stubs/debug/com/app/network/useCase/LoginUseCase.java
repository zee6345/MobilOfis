package com.app.network.useCase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lcom/app/network/useCase/LoginUseCase;", "", "loginRepository", "Lcom/app/network/repository/LoginRepository;", "(Lcom/app/network/repository/LoginRepository;)V", "changePasswordRequest", "Lcom/app/network/model/responseModels/ChangePasswordResponse;", "Lcom/app/network/model/callModels/ChangePasswordRequest;", "(Lcom/app/network/model/callModels/ChangePasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "changePasswordVerify", "Lcom/app/network/model/responseModels/VerifyChangePasswordResponse;", "verifyChangePasswordRequest", "Lcom/app/network/model/callModels/VerifyChangePasswordRequest;", "(Lcom/app/network/model/callModels/VerifyChangePasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendLoginAsanRequest", "Lcom/app/network/model/responseModels/LoginAsanResponse;", "loginAsanRequest", "Lcom/app/network/model/callModels/LoginAsanRequest;", "(Lcom/app/network/model/callModels/LoginAsanRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendLoginRequestGoogleAuth", "Lcom/app/network/model/responseModels/LoginResponse;", "loginRequest", "Lcom/app/network/model/callModels/LoginRequest;", "(Lcom/app/network/model/callModels/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendLoginVerificationRequest", "Lcom/app/network/model/responseModels/LoginVerifyResponse;", "loginVerificationRequest", "Lcom/app/network/model/callModels/LoginVerificationRequest;", "(Lcom/app/network/model/callModels/LoginVerificationRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class LoginUseCase {
    private final com.app.network.repository.LoginRepository loginRepository = null;
    
    @javax.inject.Inject
    public LoginUseCase(@org.jetbrains.annotations.NotNull
    com.app.network.repository.LoginRepository loginRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendLoginRequestGoogleAuth(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.LoginRequest loginRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.LoginResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendLoginVerificationRequest(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.LoginVerificationRequest loginVerificationRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.LoginVerifyResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendLoginAsanRequest(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.LoginAsanRequest loginAsanRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.LoginAsanResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object changePasswordRequest(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.ChangePasswordRequest changePasswordRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.ChangePasswordResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object changePasswordVerify(@org.jetbrains.annotations.NotNull
    com.app.network.model.callModels.VerifyChangePasswordRequest verifyChangePasswordRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.app.network.model.responseModels.VerifyChangePasswordResponse> continuation) {
        return null;
    }
}