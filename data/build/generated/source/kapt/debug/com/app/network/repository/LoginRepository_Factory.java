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
public final class LoginRepository_Factory implements Factory<LoginRepository> {
  private final Provider<APIService> apiServiceProvider;

  private final Provider<APIService> apiServiceAuthInterceptorProvider;

  public LoginRepository_Factory(Provider<APIService> apiServiceProvider,
      Provider<APIService> apiServiceAuthInterceptorProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.apiServiceAuthInterceptorProvider = apiServiceAuthInterceptorProvider;
  }

  @Override
  public LoginRepository get() {
    return newInstance(apiServiceProvider.get(), apiServiceAuthInterceptorProvider.get());
  }

  public static LoginRepository_Factory create(Provider<APIService> apiServiceProvider,
      Provider<APIService> apiServiceAuthInterceptorProvider) {
    return new LoginRepository_Factory(apiServiceProvider, apiServiceAuthInterceptorProvider);
  }

  public static LoginRepository newInstance(APIService apiService,
      APIService apiServiceAuthInterceptor) {
    return new LoginRepository(apiService, apiServiceAuthInterceptor);
  }
}
