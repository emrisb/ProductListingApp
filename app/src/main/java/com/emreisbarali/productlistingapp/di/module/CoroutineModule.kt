package com.emreisbarali.productlistingapp.di.module

import com.emreisbarali.productlistingapp.di.DispatcherDefault
import com.emreisbarali.productlistingapp.di.DispatcherIo
import com.emreisbarali.productlistingapp.di.DispatcherMain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers


@InstallIn(ApplicationComponent::class)
@Module
object CoroutineModule {

    @DispatcherMain
    @Provides
    fun provideDispatcherMain() = Dispatchers.Main

    @DispatcherIo
    @Provides
    fun provideDispatcherIo() = Dispatchers.IO

    @DispatcherDefault
    @Provides
    fun provideDispatcherDefault() = Dispatchers.Default
}