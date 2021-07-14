package com.github.carvaldo.cartaovisitas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.carvaldo.fimo.datasource.local.Result
import com.github.carvaldo.fimo.datasource.local.dao.MovieDao

@Database(version = 1, exportSchema = false,
    entities = [Result::class])
abstract class DatabaseApp: RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: DatabaseApp? = null

        fun getDatabase(context: Context): DatabaseApp {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseApp::class.java,
                    "fimo_db"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }

    abstract fun getMovieDao(): MovieDao
}