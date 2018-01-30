package io.github.jitinsharma.reduxmovieexample.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.github.jitinsharma.reduxmovieexample.models.MovieObject

/**
 * Created by jsharma on 20/01/18.
 */

@Dao
interface MovieDao {

    @Query("SELECT * from movies")
    fun getAll(): List<MovieObject>

    @Insert
    fun insertAll(vararg movieObject: MovieObject)

    @Insert
    fun insert(movieObject: MovieObject)

    @Delete
    fun delete(movieObject: MovieObject)
}