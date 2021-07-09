package com.github.carvaldo.cartaovisitas

import android.app.Application
import androidx.room.RoomDatabase
import com.github.carvaldo.cartaovisitas.data.DatabaseApp

class App: Application() {
    private lateinit var databaseApp: DatabaseApp

    override fun onCreate() {
        super.onCreate()

    }
}