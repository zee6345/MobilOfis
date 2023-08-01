package com.app.network.useCase;

import com.app.network.repository.AdjustmentRepository;
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
public final class AdjustmentUseCase_Factory implements Factory<AdjustmentUseCase> {
  private final Provider<AdjustmentRepository> adjustmentRepositoryProvider;

  public AdjustmentUseCase_Factory(Provider<AdjustmentRepository> adjustmentRepositoryProvider) {
    this.adjustmentRepositoryProvider = adjustmentRepositoryProvider;
  }

  @Override
  public AdjustmentUseCase get() {
    return newInstance(adjustmentRepositoryProvider.get());
  }

  public static AdjustmentUseCase_Factory create(
      Provider<AdjustmentRepository> adjustmentRepositoryProvider) {
    return new AdjustmentUseCase_Factory(adjustmentRepositoryProvider);
  }

  public static AdjustmentUseCase newInstance(AdjustmentRepository adjustmentRepository) {
    return new AdjustmentUseCase(adjustmentRepository);
  }
}
