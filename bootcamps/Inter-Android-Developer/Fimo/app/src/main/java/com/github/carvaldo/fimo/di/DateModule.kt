package com.github.carvaldo.fimo.di

import com.github.carvaldo.fimo.App
import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import java.text.SimpleDateFormat
import java.util.*

@Module
@InstallIn(ActivityComponent::class)
object DateModule {

    @Provides
    fun provideSimpleDateFormat(): SimpleDateFormat {
        return SimpleDateFormat("MM/yyyy", Locale("pt", "BR"))
    }
}