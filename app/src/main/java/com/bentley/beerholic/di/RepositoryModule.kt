package com.bentley.beerholic.di

import com.bentley.beerholic.data.remote.ApiService
import com.bentley.beerholic.data.remote.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        apiService: ApiService,
    ): SearchRepository {
        return SearchRepository(apiService)
    }
}