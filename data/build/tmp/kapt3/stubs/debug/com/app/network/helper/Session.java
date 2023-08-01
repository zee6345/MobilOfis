package com.app.network.helper;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0086\u0002J\u001f\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004H\u0086\u0002J\u0010\u0010\u0015\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u0015\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0011J\u0010\u0010\u0016\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u0016\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u001c\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u0011J\"\u0010\u001c\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u001c\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u0017J\u0018\u0010\u001c\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u0019J\u001a\u0010\u001c\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004J\u001a\u0010\u001f\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/app/network/helper/Session;", "", "()V", "BLANK_STRING_KEY", "", "ENC_PREFERENCE", "INSTANCE", "WRONG_PAIR", "editor", "Landroid/content/SharedPreferences$Editor;", "savedSession", "Landroid/content/SharedPreferences;", "Session", "", "context", "Landroid/content/Context;", "delete", "", "key", "get", "defaultValue", "getBoolean", "getInt", "", "getLong", "", "getNonce", "", "put", "value", "sharedPreferenceName", "putNonce", "data_debug"})
public final class Session {
    @org.jetbrains.annotations.NotNull
    public static final com.app.network.helper.Session INSTANCE = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String BLANK_STRING_KEY = "";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String WRONG_PAIR = "Key-Value pair cannot be blank or null";
    private static final java.lang.String ENC_PREFERENCE = "ENC_PREF";
    private static com.app.network.helper.Session INSTANCE;
    private static android.content.SharedPreferences.Editor editor;
    private static android.content.SharedPreferences savedSession;
    
    private Session() {
        super();
    }
    
    public final void Session(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public final boolean put(@org.jetbrains.annotations.Nullable
    java.lang.String key, int value) {
        return false;
    }
    
    public final boolean put(@org.jetbrains.annotations.Nullable
    java.lang.String key, boolean value) {
        return false;
    }
    
    public final boolean put(@org.jetbrains.annotations.Nullable
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.String value) {
        return false;
    }
    
    public final boolean put(@org.jetbrains.annotations.Nullable
    java.lang.String key, long value) {
        return false;
    }
    
    public final boolean put(@org.jetbrains.annotations.Nullable
    java.lang.String key, boolean value, @org.jetbrains.annotations.Nullable
    java.lang.String sharedPreferenceName) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String get(@org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String get(@org.jetbrains.annotations.Nullable
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.String defaultValue) {
        return null;
    }
    
    public final int getInt(@org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return 0;
    }
    
    public final int getInt(@org.jetbrains.annotations.Nullable
    java.lang.String key, int defaultValue) {
        return 0;
    }
    
    public final long getLong(@org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return 0L;
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return false;
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.Nullable
    java.lang.String key, boolean defaultValue) {
        return false;
    }
    
    public final boolean delete(@org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return false;
    }
    
    public final boolean putNonce(@org.jetbrains.annotations.Nullable
    java.lang.String key, @org.jetbrains.annotations.Nullable
    byte[] value) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final byte[] getNonce(@org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return null;
    }
}