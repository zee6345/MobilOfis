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
public final class HomeRepository_Factory implements Factory<HomeRepository> {
  private final Provider<APIService> apiServiceProvider;

  public HomeRepository_Factory(Provider<APIService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public HomeRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static HomeRepository_Factory create(Provider<APIService> apiServiceProvider) {
    return new HomeRepository_Factory(apiServiceProvider);
  }

  public static HomeRepository newInstance(APIService apiService) {
    return new HomeRepository(apiService);
  }
}
