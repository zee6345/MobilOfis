package com.app.network.model.responseModels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lcom/app/network/model/responseModels/LoginAsanResponse;", "", "resultCode", "", "errCode", "", "errMessage", "gniAuthResponseType", "Lcom/app/network/model/responseModels/GniAuthResponseType;", "(ILjava/lang/String;Ljava/lang/String;Lcom/app/network/model/responseModels/GniAuthResponseType;)V", "getErrCode", "()Ljava/lang/String;", "getErrMessage", "getGniAuthResponseType", "()Lcom/app/network/model/responseModels/GniAuthResponseType;", "getResultCode", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "data_debug"})
public final class LoginAsanResponse {
    private final int resultCode = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String errCode = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String errMessage = null;
    @org.jetbrains.annotations.NotNull
    private final com.app.network.model.responseModels.GniAuthResponseType gniAuthResponseType = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.LoginAsanResponse copy(int resultCode, @org.jetbrains.annotations.Nullable
    java.lang.String errCode, @org.jetbrains.annotations.Nullable
    java.lang.String errMessage, @org.jetbrains.annotations.NotNull
    com.app.network.model.responseModels.GniAuthResponseType gniAuthResponseType) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public LoginAsanResponse(int resultCode, @org.jetbrains.annotations.Nullable
    java.lang.String errCode, @org.jetbrains.annotations.Nullable
    java.lang.String errMessage, @org.jetbrains.annotations.NotNull
    com.app.network.model.responseModels.GniAuthResponseType gniAuthResponseType) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getResultCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getErrCode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getErrMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.GniAuthResponseType component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.GniAuthResponseType getGniAuthResponseType() {
        return null;
    }
}