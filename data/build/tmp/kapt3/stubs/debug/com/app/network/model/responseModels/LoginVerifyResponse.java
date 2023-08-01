package com.app.network.model.responseModels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b;\b\u0087\b\u0018\u00002\u00020\u0001B\u00a3\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0001\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0001\u0012\u0006\u0010\u000e\u001a\u00020\u0001\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0001\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u001bJ\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003J\t\u00106\u001a\u00020\u0001H\u00c6\u0003J\t\u00107\u001a\u00020\u0005H\u00c6\u0003J\t\u00108\u001a\u00020\u0005H\u00c6\u0003J\t\u00109\u001a\u00020\u0005H\u00c6\u0003J\t\u0010:\u001a\u00020\u0005H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\u0001H\u00c6\u0003J\t\u0010?\u001a\u00020\u0005H\u00c6\u0003J\t\u0010@\u001a\u00020\u0007H\u00c6\u0003J\t\u0010A\u001a\u00020\u0005H\u00c6\u0003J\t\u0010B\u001a\u00020\u0001H\u00c6\u0003J\t\u0010C\u001a\u00020\u0003H\u00c6\u0003J\t\u0010D\u001a\u00020\fH\u00c6\u0003J\t\u0010E\u001a\u00020\u0001H\u00c6\u0003J\t\u0010F\u001a\u00020\u0001H\u00c6\u0003J\u00cd\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u00012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u0001H\u00c6\u0001J\u0013\u0010H\u001a\u00020\f2\b\u0010I\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010J\u001a\u00020\u0005H\u00d6\u0001J\t\u0010K\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\t\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010&R\u0011\u0010\r\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010$R\u0011\u0010\u000e\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0012\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u0011\u0010\u0013\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0014\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001fR\u0011\u0010\u0017\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001dR\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001dR\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001dR\u0011\u0010\u001a\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010$\u00a8\u0006L"}, d2 = {"Lcom/app/network/model/responseModels/LoginVerifyResponse;", "", "customerName", "", "customerNo", "", "customers", "Lcom/app/network/model/responseModels/Customers;", "day", "errMessage", "forcePassword", "isTOTPEnabled", "", "lastLoginDate", "mSignStatus", "permissions", "", "Lcom/app/network/model/responseModels/Permission;", "proxyCustomerNo", "result", "role", "totalApproverCount", "totalSignerCount", "userName", "usersName", "usersSurName", "verification", "(Ljava/lang/String;ILcom/app/network/model/responseModels/Customers;ILjava/lang/Object;Ljava/lang/String;ZLjava/lang/Object;Ljava/lang/Object;Ljava/util/List;Ljava/lang/Object;IIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getCustomerName", "()Ljava/lang/String;", "getCustomerNo", "()I", "getCustomers", "()Lcom/app/network/model/responseModels/Customers;", "getDay", "getErrMessage", "()Ljava/lang/Object;", "getForcePassword", "()Z", "getLastLoginDate", "getMSignStatus", "getPermissions", "()Ljava/util/List;", "getProxyCustomerNo", "getResult", "getRole", "getTotalApproverCount", "getTotalSignerCount", "getUserName", "getUsersName", "getUsersSurName", "getVerification", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "data_debug"})
public final class LoginVerifyResponse {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String customerName = null;
    private final int customerNo = 0;
    @org.jetbrains.annotations.NotNull
    private final com.app.network.model.responseModels.Customers customers = null;
    private final int day = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.Object errMessage = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String forcePassword = null;
    private final boolean isTOTPEnabled = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.Object lastLoginDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.Object mSignStatus = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.app.network.model.responseModels.Permission> permissions = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.Object proxyCustomerNo = null;
    private final int result = 0;
    private final int role = 0;
    private final int totalApproverCount = 0;
    private final int totalSignerCount = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String userName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String usersName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String usersSurName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.Object verification = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.LoginVerifyResponse copy(@org.jetbrains.annotations.NotNull
    java.lang.String customerName, int customerNo, @org.jetbrains.annotations.NotNull
    com.app.network.model.responseModels.Customers customers, int day, @org.jetbrains.annotations.NotNull
    java.lang.Object errMessage, @org.jetbrains.annotations.NotNull
    java.lang.String forcePassword, boolean isTOTPEnabled, @org.jetbrains.annotations.NotNull
    java.lang.Object lastLoginDate, @org.jetbrains.annotations.NotNull
    java.lang.Object mSignStatus, @org.jetbrains.annotations.NotNull
    java.util.List<com.app.network.model.responseModels.Permission> permissions, @org.jetbrains.annotations.NotNull
    java.lang.Object proxyCustomerNo, int result, int role, int totalApproverCount, int totalSignerCount, @org.jetbrains.annotations.NotNull
    java.lang.String userName, @org.jetbrains.annotations.NotNull
    java.lang.String usersName, @org.jetbrains.annotations.NotNull
    java.lang.String usersSurName, @org.jetbrains.annotations.NotNull
    java.lang.Object verification) {
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
    
    public LoginVerifyResponse(@org.jetbrains.annotations.NotNull
    java.lang.String customerName, int customerNo, @org.jetbrains.annotations.NotNull
    com.app.network.model.responseModels.Customers customers, int day, @org.jetbrains.annotations.NotNull
    java.lang.Object errMessage, @org.jetbrains.annotations.NotNull
    java.lang.String forcePassword, boolean isTOTPEnabled, @org.jetbrains.annotations.NotNull
    java.lang.Object lastLoginDate, @org.jetbrains.annotations.NotNull
    java.lang.Object mSignStatus, @org.jetbrains.annotations.NotNull
    java.util.List<com.app.network.model.responseModels.Permission> permissions, @org.jetbrains.annotations.NotNull
    java.lang.Object proxyCustomerNo, int result, int role, int totalApproverCount, int totalSignerCount, @org.jetbrains.annotations.NotNull
    java.lang.String userName, @org.jetbrains.annotations.NotNull
    java.lang.String usersName, @org.jetbrains.annotations.NotNull
    java.lang.String usersSurName, @org.jetbrains.annotations.NotNull
    java.lang.Object verification) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCustomerName() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getCustomerNo() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.Customers component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.Customers getCustomers() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getDay() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getErrMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getForcePassword() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean isTOTPEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getLastLoginDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getMSignStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.app.network.model.responseModels.Permission> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.app.network.model.responseModels.Permission> getPermissions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getProxyCustomerNo() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int getResult() {
        return 0;
    }
    
    public final int component13() {
        return 0;
    }
    
    public final int getRole() {
        return 0;
    }
    
    public final int component14() {
        return 0;
    }
    
    public final int getTotalApproverCount() {
        return 0;
    }
    
    public final int component15() {
        return 0;
    }
    
    public final int getTotalSignerCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUsersName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUsersSurName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getVerification() {
        return null;
    }
}