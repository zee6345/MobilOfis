package com.app.network.di

import android.content.Context
import com.app.network.helper.Session
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SessionModule {

    @Singleton
    @Provides
    fun provideSession(@ApplicationContext context: Context): Session {
        return Session(context)
    }

}