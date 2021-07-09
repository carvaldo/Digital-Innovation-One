package com.github.carvaldo.cartaovisitas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = true,
    entities = [])
abstract class DatabaseApp: RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: DatabaseApp? = null

        fun getDatabase(context: Context): DatabaseApp {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseApp::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}