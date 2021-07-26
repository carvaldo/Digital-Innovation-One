package com.github.carvaldo.fimo.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.carvaldo.fimo.datasource.local.dao.*
import com.github.carvaldo.fimo.datasource.local.entity.*
import com.github.carvaldo.fimo.datasource.local.util.Converters

@Database(version = 3, exportSchema = true,
    entities = [
        ResultMovie::class, Searched::class, SearchedResult::class, Star::class, Person::class,
        Director::class, MovieDetail::class, StarMovie::class, DirectorMovie::class, CastMovie::class
    ]
)
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
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }

    abstract fun getMovieDao(): MovieDao
    abstract fun getSearchedDao(): SearchedDao
    abstract fun getSearchedMoviedDao(): SearchedMoviedDao
    abstract fun getDirectorDao(): DirectorDao
    abstract fun getStarDao(): StarDao
    abstract fun getStarMovieDao(): StarMovieDao
    abstract fun getDirectorMovieDa(): DirectorMovieDao
    abstract fun getCastDao(): CastDao
    abstract fun getPersonDao(): PersonDao
}