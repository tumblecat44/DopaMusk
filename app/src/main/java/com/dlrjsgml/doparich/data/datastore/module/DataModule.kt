package com.dlrjsgml.doparich.data.datastore.module

import android.content.Context
import com.dlrjsgml.doparich.data.datastore.repository.ImplRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)= ImplRepository(context)
}