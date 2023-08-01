package com.app.network.repository;

import com.app.network.apiService.APIService;
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
public final class AdjustmentRepository_Factory implements Factory<AdjustmentRepository> {
  private final Provider<APIService> apiServiceProvider;

  public AdjustmentRepository_Factory(Provider<APIService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public AdjustmentRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static AdjustmentRepository_Factory create(Provider<APIService> apiServiceProvider) {
    return new AdjustmentRepository_Factory(apiServiceProvider);
  }

  public static AdjustmentRepository newInstance(APIService apiService) {
    return new AdjustmentRepository(apiService);
  }
}
