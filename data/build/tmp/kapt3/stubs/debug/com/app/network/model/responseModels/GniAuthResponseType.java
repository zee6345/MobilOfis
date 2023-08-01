package com.app.network.model.responseModels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\bW\b\u0087\b\u0018\u00002\u00020\u0001B\u00af\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010%J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010K\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fH\u00c6\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fH\u00c6\u0003J\t\u0010Q\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u0017H\u00c6\u0003\u00a2\u0006\u0002\u0010CJ\u0010\u0010S\u001a\u0004\u0018\u00010\u0017H\u00c6\u0003\u00a2\u0006\u0002\u0010CJ\u000b\u0010T\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010X\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010`\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010c\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010f\u001a\u0004\u0018\u00010\fH\u00c6\u0003\u00a2\u0006\u0002\u00106J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00f4\u0002\u0010h\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010iJ\u0013\u0010j\u001a\u00020\f2\b\u0010k\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010l\u001a\u00020\u0017H\u00d6\u0001J\t\u0010m\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\'R\u0013\u0010$\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\'R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\'R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\'R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\'R\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u001a\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\'R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\'R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010.R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\'R\u0013\u0010 \u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010\'R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\n\n\u0002\u00107\u001a\u0004\b\u000b\u00106R\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\'R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010\'R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\'R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010\'R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010\'R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010\'R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\'R\u0013\u0010#\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010\'R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010\'R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010\'R\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\n\n\u0002\u0010D\u001a\u0004\bB\u0010CR\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\n\n\u0002\u0010D\u001a\u0004\bE\u0010CR\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010\'R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010\'R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010\'R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u0010\'\u00a8\u0006n"}, d2 = {"Lcom/app/network/model/responseModels/GniAuthResponseType;", "", "userName", "", "userLongName", "customerNo", "", "proxyCustomerNo", "phoneNumber", "personalCode", "verification", "isTOTPEnabled", "", "TOTPSecret", "customers", "", "authType", "userId", "timeStamp", "certIdentifier", "certsList", "role", "totalSignerCount", "", "totalApproverCount", "clientId", "channel", "authToken", "locale", "module", "action", "deviceId", "ecid", "refreshToken", "message", "registerOTP", "apiKey", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTOTPSecret", "()Ljava/lang/String;", "getAction", "getApiKey", "getAuthToken", "getAuthType", "getCertIdentifier", "getCertsList", "()Ljava/util/List;", "getChannel", "getClientId", "getCustomerNo", "()J", "getCustomers", "getDeviceId", "getEcid", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLocale", "getMessage", "getModule", "getPersonalCode", "getPhoneNumber", "getProxyCustomerNo", "getRefreshToken", "getRegisterOTP", "getRole", "getTimeStamp", "getTotalApproverCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTotalSignerCount", "getUserId", "getUserLongName", "getUserName", "getVerification", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/app/network/model/responseModels/GniAuthResponseType;", "equals", "other", "hashCode", "toString", "data_debug"})
public final class GniAuthResponseType {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String userName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String userLongName = null;
    private final long customerNo = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String proxyCustomerNo = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String phoneNumber = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String personalCode = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String verification = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean isTOTPEnabled = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String TOTPSecret = null;
    @org.jetbrains.annotations.Nullable
    private final java.util.List<java.lang.String> customers = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String authType = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String userId = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String timeStamp = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String certIdentifier = null;
    @org.jetbrains.annotations.Nullable
    private final java.util.List<java.lang.String> certsList = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String role = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer totalSignerCount = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer totalApproverCount = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String clientId = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String channel = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String authToken = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String locale = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String module = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String action = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String deviceId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String ecid = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String refreshToken = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String message = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String registerOTP = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String apiKey = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.GniAuthResponseType copy(@org.jetbrains.annotations.Nullable
    java.lang.String userName, @org.jetbrains.annotations.Nullable
    java.lang.String userLongName, long customerNo, @org.jetbrains.annotations.Nullable
    java.lang.String proxyCustomerNo, @org.jetbrains.annotations.NotNull
    java.lang.String phoneNumber, @org.jetbrains.annotations.Nullable
    java.lang.String personalCode, @org.jetbrains.annotations.Nullable
    java.lang.String verification, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isTOTPEnabled, @org.jetbrains.annotations.Nullable
    java.lang.String TOTPSecret, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> customers, @org.jetbrains.annotations.Nullable
    java.lang.String authType, @org.jetbrains.annotations.NotNull
    java.lang.String userId, @org.jetbrains.annotations.NotNull
    java.lang.String timeStamp, @org.jetbrains.annotations.Nullable
    java.lang.String certIdentifier, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> certsList, @org.jetbrains.annotations.NotNull
    java.lang.String role, @org.jetbrains.annotations.Nullable
    java.lang.Integer totalSignerCount, @org.jetbrains.annotations.Nullable
    java.lang.Integer totalApproverCount, @org.jetbrains.annotations.Nullable
    java.lang.String clientId, @org.jetbrains.annotations.NotNull
    java.lang.String channel, @org.jetbrains.annotations.Nullable
    java.lang.String authToken, @org.jetbrains.annotations.NotNull
    java.lang.String locale, @org.jetbrains.annotations.Nullable
    java.lang.String module, @org.jetbrains.annotations.Nullable
    java.lang.String action, @org.jetbrains.annotations.Nullable
    java.lang.String deviceId, @org.jetbrains.annotations.Nullable
    java.lang.String ecid, @org.jetbrains.annotations.Nullable
    java.lang.String refreshToken, @org.jetbrains.annotations.Nullable
    java.lang.String message, @org.jetbrains.annotations.Nullable
    java.lang.String registerOTP, @org.jetbrains.annotations.Nullable
    java.lang.String apiKey) {
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
    
    public GniAuthResponseType(@org.jetbrains.annotations.Nullable
    java.lang.String userName, @org.jetbrains.annotations.Nullable
    java.lang.String userLongName, long customerNo, @org.jetbrains.annotations.Nullable
    java.lang.String proxyCustomerNo, @org.jetbrains.annotations.NotNull
    java.lang.String phoneNumber, @org.jetbrains.annotations.Nullable
    java.lang.String personalCode, @org.jetbrains.annotations.Nullable
    java.lang.String verification, @org.jetbrains.annotations.Nullable
    java.lang.Boolean isTOTPEnabled, @org.jetbrains.annotations.Nullable
    java.lang.String TOTPSecret, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> customers, @org.jetbrains.annotations.Nullable
    java.lang.String authType, @org.jetbrains.annotations.NotNull
    java.lang.String userId, @org.jetbrains.annotations.NotNull
    java.lang.String timeStamp, @org.jetbrains.annotations.Nullable
    java.lang.String certIdentifier, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> certsList, @org.jetbrains.annotations.NotNull
    java.lang.String role, @org.jetbrains.annotations.Nullable
    java.lang.Integer totalSignerCount, @org.jetbrains.annotations.Nullable
    java.lang.Integer totalApproverCount, @org.jetbrains.annotations.Nullable
    java.lang.String clientId, @org.jetbrains.annotations.NotNull
    java.lang.String channel, @org.jetbrains.annotations.Nullable
    java.lang.String authToken, @org.jetbrains.annotations.NotNull
    java.lang.String locale, @org.jetbrains.annotations.Nullable
    java.lang.String module, @org.jetbrains.annotations.Nullable
    java.lang.String action, @org.jetbrains.annotations.Nullable
    java.lang.String deviceId, @org.jetbrains.annotations.Nullable
    java.lang.String ecid, @org.jetbrains.annotations.Nullable
    java.lang.String refreshToken, @org.jetbrains.annotations.Nullable
    java.lang.String message, @org.jetbrains.annotations.Nullable
    java.lang.String registerOTP, @org.jetbrains.annotations.Nullable
    java.lang.String apiKey) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserLongName() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getCustomerNo() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getProxyCustomerNo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPersonalCode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getVerification() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean isTOTPEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTOTPSecret() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getCustomers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAuthType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTimeStamp() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCertIdentifier() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getCertsList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRole() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getTotalSignerCount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getTotalApproverCount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getClientId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAuthToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLocale() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getModule() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAction() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDeviceId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getEcid() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRefreshToken() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRegisterOTP() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component30() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getApiKey() {
        return null;
    }
}