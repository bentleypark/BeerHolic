package com.bentley.beerholic.di

import android.content.Context
import android.content.SharedPreferences
import com.bentley.beerholic.utils.PreferenceUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Singleton
    @Provides
    fun providePreferenceUtil(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceUtil.getPref(context)
    }
}