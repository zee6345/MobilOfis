package com.app.network.di

import com.app.network.retrofitClient.APIService
import com.app.network.helper.Session
import com.app.network.retrofitClient.BaseRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiClient(session: Session): APIService {
        return BaseRetrofitClient(session).createApiService()
    }

}