package com.app.network.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00068F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/app/network/viewmodel/AdjustmentViewModel;", "Landroidx/lifecycle/ViewModel;", "adjustmentUseCase", "Lcom/app/network/useCase/AdjustmentUseCase;", "(Lcom/app/network/useCase/AdjustmentUseCase;)V", "_data", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/app/network/model/DataState;", "", "data", "getData", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "getUserInfo", "", "token", "", "customerId", "data_debug"})
public final class AdjustmentViewModel extends androidx.lifecycle.ViewModel {
    private final com.app.network.useCase.AdjustmentUseCase adjustmentUseCase = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.app.network.model.DataState<java.lang.Object>> _data = null;
    
    @javax.inject.Inject
    public AdjustmentViewModel(@org.jetbrains.annotations.NotNull
    com.app.network.useCase.AdjustmentUseCase adjustmentUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.MutableStateFlow<com.app.network.model.DataState<java.lang.Object>> getData() {
        return null;
    }
    
    public final void getUserInfo(@org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String customerId) {
    }
}