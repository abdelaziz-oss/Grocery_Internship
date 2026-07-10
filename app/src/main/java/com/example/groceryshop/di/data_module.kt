package com.example.groceryshop.di

import android.content.Context
import com.example.groceryshop.authentication.data.local.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object data_module {
    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences = UserPreferences(context)
}