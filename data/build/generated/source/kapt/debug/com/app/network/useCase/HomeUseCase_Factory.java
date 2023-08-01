package com.app.network.useCase;

import com.app.network.repository.HomeRepository;
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
public final class HomeUseCase_Factory implements Factory<HomeUseCase> {
  private final Provider<HomeRepository> homeRepositoryProvider;

  public HomeUseCase_Factory(Provider<HomeRepository> homeRepositoryProvider) {
    this.homeRepositoryProvider = homeRepositoryProvider;
  }

  @Override
  public HomeUseCase get() {
    return newInstance(homeRepositoryProvider.get());
  }

  public static HomeUseCase_Factory create(Provider<HomeRepository> homeRepositoryProvider) {
    return new HomeUseCase_Factory(homeRepositoryProvider);
  }

  public static HomeUseCase newInstance(HomeRepository homeRepository) {
    return new HomeUseCase(homeRepository);
  }
}
