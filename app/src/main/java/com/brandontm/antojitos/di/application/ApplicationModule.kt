package com.brandontm.antojitos.di.application

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import com.brandontm.antojitos.di.qualifiers.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @ForApplication
    fun provideAppContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideLayoutInflater(@ForApplication context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }
}
