package com.github.carvaldo.fimo.di

import com.github.carvaldo.fimo.datasource.remote.service.MovieService
import com.github.carvaldo.fimo.datasource.remote.service.PersonService
import com.github.carvaldo.fimo.datasource.remote.util.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    fun provideMovieService(): MovieService {
        return ServiceGenerator.create()
    }

    @Provides
    fun providePersonService(): PersonService {
        return ServiceGenerator.create()
    }
}