package com.github.carvaldo.cartaovisitas

import android.app.Application
import androidx.room.RoomDatabase
import com.github.carvaldo.cartaovisitas.data.DatabaseApp

class App: Application() {

    companion object {
        lateinit var database: DatabaseApp
    }

    override fun onCreate() {
        super.onCreate()
        database = DatabaseApp.getDatabase(this)
    }
}