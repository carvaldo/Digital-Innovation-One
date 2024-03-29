package com.github.carvaldo.fimo

import android.app.Application
import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var database: DatabaseApp
    }

    override fun onCreate() {
        super.onCreate()
        database = DatabaseApp.getDatabase(this)
    }
}