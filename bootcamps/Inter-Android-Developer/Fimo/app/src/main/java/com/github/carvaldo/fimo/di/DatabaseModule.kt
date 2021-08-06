package com.github.carvaldo.fimo.di

import com.github.carvaldo.fimo.App
import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(): DatabaseApp {
        return App.database
    }
}