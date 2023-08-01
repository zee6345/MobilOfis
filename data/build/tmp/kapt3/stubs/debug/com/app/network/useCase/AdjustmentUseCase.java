package com.app.network.useCase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/app/network/useCase/AdjustmentUseCase;", "", "adjustmentRepository", "Lcom/app/network/repository/AdjustmentRepository;", "(Lcom/app/network/repository/AdjustmentRepository;)V", "getUserInfo", "Lokhttp3/ResponseBody;", "token", "", "customerId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class AdjustmentUseCase {
    private final com.app.network.repository.AdjustmentRepository adjustmentRepository = null;
    
    @javax.inject.Inject
    public AdjustmentUseCase(@org.jetbrains.annotations.NotNull
    com.app.network.repository.AdjustmentRepository adjustmentRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUserInfo(@org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String customerId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> continuation) {
        return null;
    }
}