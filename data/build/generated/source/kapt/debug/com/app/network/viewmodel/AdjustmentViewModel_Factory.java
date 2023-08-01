package com.app.network.viewmodel;

import com.app.network.useCase.AdjustmentUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AdjustmentViewModel_Factory implements Factory<AdjustmentViewModel> {
  private final Provider<AdjustmentUseCase> adjustmentUseCaseProvider;

  public AdjustmentViewModel_Factory(Provider<AdjustmentUseCase> adjustmentUseCaseProvider) {
    this.adjustmentUseCaseProvider = adjustmentUseCaseProvider;
  }

  @Override
  public AdjustmentViewModel get() {
    return newInstance(adjustmentUseCaseProvider.get());
  }

  public static AdjustmentViewModel_Factory create(
      Provider<AdjustmentUseCase> adjustmentUseCaseProvider) {
    return new AdjustmentViewModel_Factory(adjustmentUseCaseProvider);
  }

  public static AdjustmentViewModel newInstance(AdjustmentUseCase adjustmentUseCase) {
    return new AdjustmentViewModel(adjustmentUseCase);
  }
}
