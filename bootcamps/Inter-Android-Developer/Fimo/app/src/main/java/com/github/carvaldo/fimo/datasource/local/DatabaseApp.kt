package com.github.carvaldo.fimo.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.carvaldo.fimo.datasource.local.dao.MovieDao
import com.github.carvaldo.fimo.datasource.local.dao.SearchedDao
import com.github.carvaldo.fimo.datasource.local.dao.SearchedMoviedDao

@Database(version = 1, exportSchema = false,
    entities = [ResultMovie::class, Searched::class, SearchedResult::class])
@TypeConverters(Converters::class)
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
    abstract fun getSearchedDao(): SearchedDao
    abstract fun getSearchedMoviedDao(): SearchedMoviedDao
}