package com.example.trackbooks.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}