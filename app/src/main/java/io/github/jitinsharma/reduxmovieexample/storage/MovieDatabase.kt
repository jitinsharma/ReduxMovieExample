package io.github.jitinsharma.reduxmovieexample.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.jitinsharma.reduxmovieexample.models.MovieObject

/**
 * Created by jsharma on 20/01/18.
 */

@Database(entities = [(MovieObject::class)], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
