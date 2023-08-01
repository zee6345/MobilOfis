package com.app.network.model.responseModels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\bT\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u00f5\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0001\u0012\u0006\u0010!\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\"J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\t\u0010E\u001a\u00020\u0003H\u00c6\u0003J\t\u0010F\u001a\u00020\u0003H\u00c6\u0003J\t\u0010G\u001a\u00020\u000fH\u00c6\u0003J\t\u0010H\u001a\u00020\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\t\u0010J\u001a\u00020\u0003H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\t\u0010P\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0003H\u00c6\u0003J\t\u0010R\u001a\u00020\u0003H\u00c6\u0003J\t\u0010S\u001a\u00020\u0003H\u00c6\u0003J\t\u0010T\u001a\u00020\u0003H\u00c6\u0003J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\u0003H\u00c6\u0003J\t\u0010W\u001a\u00020\u0003H\u00c6\u0003J\t\u0010X\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0001H\u00c6\u0003J\t\u0010Z\u001a\u00020\u0003H\u00c6\u0003J\t\u0010[\u001a\u00020\u0001H\u00c6\u0003J\t\u0010\\\u001a\u00020\u0003H\u00c6\u0003J\t\u0010]\u001a\u00020\u0003H\u00c6\u0003J\t\u0010^\u001a\u00020\u0003H\u00c6\u0003J\t\u0010_\u001a\u00020\u0003H\u00c6\u0003J\t\u0010`\u001a\u00020\u0003H\u00c6\u0003J\t\u0010a\u001a\u00020\u0003H\u00c6\u0003J\u00b5\u0002\u0010b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\u0001H\u00c6\u0001J\u0013\u0010c\u001a\u00020d2\b\u0010e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010f\u001a\u00020\u000fH\u00d6\u0001J\t\u0010g\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010$R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010$R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010$R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010$R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010$R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010$R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010$R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010$R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010$R\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010$R\u0011\u0010\u0017\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010$R\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010$R\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010$R\u0011\u0010\u001a\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010$R\u0011\u0010\u001b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010$R\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010$R\u0011\u0010\u001d\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010$R\u0011\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010$R\u0011\u0010\u001f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010$R\u0011\u0010 \u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0011\u0010!\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010B\u00a8\u0006h"}, d2 = {"Lcom/app/network/model/responseModels/GetAccountsItem;", "", "ACCOUNT_NO", "", "ACCOUNT_TYPE", "BALANCE", "BALANCE_LC", "BLOCKSAMOUNT", "BLOCKSTATUS", "BRANCH_NAME", "BRANCH_NO", "CCY_NAME", "COMPASSACCOUNT", "CREDIT_BALANCE", "CUSTOMER_NO", "", "DEBIT_BALANCE", "IBAN", "INTEREST_ACC", "LAST_BALANCE", "LAST_DAY_BALANCE", "NICKNAME", "OPENDATE", "ORJ_IBAN", "PRODUCT_CODE", "PRODUCT_NAME", "REAL_BALANCE", "STATUS", "WFA_AMOUNT", "WFA_COMMISSION", "WFA_CREDIT", "WFA_DEBIT", "ccyId", "productId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", "getACCOUNT_NO", "()Ljava/lang/String;", "getACCOUNT_TYPE", "getBALANCE", "getBALANCE_LC", "getBLOCKSAMOUNT", "getBLOCKSTATUS", "getBRANCH_NAME", "getBRANCH_NO", "getCCY_NAME", "getCOMPASSACCOUNT", "getCREDIT_BALANCE", "getCUSTOMER_NO", "()I", "getDEBIT_BALANCE", "getIBAN", "getINTEREST_ACC", "getLAST_BALANCE", "getLAST_DAY_BALANCE", "getNICKNAME", "getOPENDATE", "getORJ_IBAN", "getPRODUCT_CODE", "getPRODUCT_NAME", "getREAL_BALANCE", "getSTATUS", "getWFA_AMOUNT", "getWFA_COMMISSION", "getWFA_CREDIT", "getWFA_DEBIT", "getCcyId", "()Ljava/lang/Object;", "getProductId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "data_debug"})
public final class GetAccountsItem {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ACCOUNT_NO = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ACCOUNT_TYPE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String BALANCE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String BALANCE_LC = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String BLOCKSAMOUNT = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String BLOCKSTATUS = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String BRANCH_NAME = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String BRANCH_NO = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String CCY_NAME = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String COMPASSACCOUNT = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String CREDIT_BALANCE = null;
    private final int CUSTOMER_NO = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String DEBIT_BALANCE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String IBAN = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String INTEREST_ACC = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String LAST_BALANCE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String LAST_DAY_BALANCE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String NICKNAME = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String OPENDATE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ORJ_IBAN = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String PRODUCT_CODE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String PRODUCT_NAME = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String REAL_BALANCE = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String STATUS = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String WFA_AMOUNT = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String WFA_COMMISSION = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String WFA_CREDIT = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String WFA_DEBIT = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.Object ccyId = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.Object productId = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.app.network.model.responseModels.GetAccountsItem copy(@org.jetbrains.annotations.NotNull
    java.lang.String ACCOUNT_NO, @org.jetbrains.annotations.NotNull
    java.lang.String ACCOUNT_TYPE, @org.jetbrains.annotations.NotNull
    java.lang.String BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String BALANCE_LC, @org.jetbrains.annotations.NotNull
    java.lang.String BLOCKSAMOUNT, @org.jetbrains.annotations.NotNull
    java.lang.String BLOCKSTATUS, @org.jetbrains.annotations.NotNull
    java.lang.String BRANCH_NAME, @org.jetbrains.annotations.NotNull
    java.lang.String BRANCH_NO, @org.jetbrains.annotations.NotNull
    java.lang.String CCY_NAME, @org.jetbrains.annotations.NotNull
    java.lang.String COMPASSACCOUNT, @org.jetbrains.annotations.NotNull
    java.lang.String CREDIT_BALANCE, int CUSTOMER_NO, @org.jetbrains.annotations.NotNull
    java.lang.String DEBIT_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String IBAN, @org.jetbrains.annotations.NotNull
    java.lang.String INTEREST_ACC, @org.jetbrains.annotations.NotNull
    java.lang.String LAST_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String LAST_DAY_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String NICKNAME, @org.jetbrains.annotations.NotNull
    java.lang.String OPENDATE, @org.jetbrains.annotations.NotNull
    java.lang.String ORJ_IBAN, @org.jetbrains.annotations.NotNull
    java.lang.String PRODUCT_CODE, @org.jetbrains.annotations.NotNull
    java.lang.String PRODUCT_NAME, @org.jetbrains.annotations.NotNull
    java.lang.String REAL_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String STATUS, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_AMOUNT, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_COMMISSION, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_CREDIT, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_DEBIT, @org.jetbrains.annotations.NotNull
    java.lang.Object ccyId, @org.jetbrains.annotations.NotNull
    java.lang.Object productId) {
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
    
    public GetAccountsItem(@org.jetbrains.annotations.NotNull
    java.lang.String ACCOUNT_NO, @org.jetbrains.annotations.NotNull
    java.lang.String ACCOUNT_TYPE, @org.jetbrains.annotations.NotNull
    java.lang.String BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String BALANCE_LC, @org.jetbrains.annotations.NotNull
    java.lang.String BLOCKSAMOUNT, @org.jetbrains.annotations.NotNull
    java.lang.String BLOCKSTATUS, @org.jetbrains.annotations.NotNull
    java.lang.String BRANCH_NAME, @org.jetbrains.annotations.NotNull
    java.lang.String BRANCH_NO, @org.jetbrains.annotations.NotNull
    java.lang.String CCY_NAME, @org.jetbrains.annotations.NotNull
    java.lang.String COMPASSACCOUNT, @org.jetbrains.annotations.NotNull
    java.lang.String CREDIT_BALANCE, int CUSTOMER_NO, @org.jetbrains.annotations.NotNull
    java.lang.String DEBIT_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String IBAN, @org.jetbrains.annotations.NotNull
    java.lang.String INTEREST_ACC, @org.jetbrains.annotations.NotNull
    java.lang.String LAST_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String LAST_DAY_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String NICKNAME, @org.jetbrains.annotations.NotNull
    java.lang.String OPENDATE, @org.jetbrains.annotations.NotNull
    java.lang.String ORJ_IBAN, @org.jetbrains.annotations.NotNull
    java.lang.String PRODUCT_CODE, @org.jetbrains.annotations.NotNull
    java.lang.String PRODUCT_NAME, @org.jetbrains.annotations.NotNull
    java.lang.String REAL_BALANCE, @org.jetbrains.annotations.NotNull
    java.lang.String STATUS, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_AMOUNT, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_COMMISSION, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_CREDIT, @org.jetbrains.annotations.NotNull
    java.lang.String WFA_DEBIT, @org.jetbrains.annotations.NotNull
    java.lang.Object ccyId, @org.jetbrains.annotations.NotNull
    java.lang.Object productId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getACCOUNT_NO() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getACCOUNT_TYPE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBALANCE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBALANCE_LC() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBLOCKSAMOUNT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBLOCKSTATUS() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBRANCH_NAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBRANCH_NO() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCCY_NAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCOMPASSACCOUNT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCREDIT_BALANCE() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int getCUSTOMER_NO() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDEBIT_BALANCE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getIBAN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getINTEREST_ACC() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLAST_BALANCE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLAST_DAY_BALANCE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNICKNAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getOPENDATE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getORJ_IBAN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPRODUCT_CODE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPRODUCT_NAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getREAL_BALANCE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSTATUS() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWFA_AMOUNT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWFA_COMMISSION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWFA_CREDIT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWFA_DEBIT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object component29() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getCcyId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object component30() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.Object getProductId() {
        return null;
    }
}