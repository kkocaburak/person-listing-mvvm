package com.bkarakoca.personlistingmvvm.internal.injection.module

import com.bkarakoca.personlistingmvvm.data.repository.PersonRepository
import com.bkarakoca.personlistingmvvm.data.repository.PersonRepositoryImpl
import com.bkarakoca.personlistingmvvm.data.service.local.LocalDataSource
import com.bkarakoca.personlistingmvvm.internal.util.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class PersonModule {

    @Provides
    @Singleton
    fun providePersonRepository(
        localDataSource: LocalDataSource,
        resProvider: ResourceProvider
    ): PersonRepository = PersonRepositoryImpl(localDataSource, resProvider)
}