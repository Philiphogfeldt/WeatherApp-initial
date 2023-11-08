package com.plcoding.weatherapp.di

import android.content.Context
import com.plcoding.weatherapp.data.location.DefaultLocationTracker
import com.plcoding.weatherapp.domain.location.LocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationTracker(@ApplicationContext context: Context): LocationTracker {
        return DefaultLocationTracker(context)
    }
}
