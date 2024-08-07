package com.dlrjsgml.doparich.data.info

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {
    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context) = UserRepository(context)
}