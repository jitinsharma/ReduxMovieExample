package io.github.jitinsharma.reduxmovieexample

import android.app.Application
import android.arch.persistence.room.Room
import io.github.jitinsharma.reduxmovieexample.helpers.debugMode
import io.github.jitinsharma.reduxmovieexample.middlewares.databaseMiddleWare
import io.github.jitinsharma.reduxmovieexample.middlewares.movieMiddleWare
import io.github.jitinsharma.reduxmovieexample.middlewares.networkMiddleWare
import io.github.jitinsharma.reduxmovieexample.reducers.appReducer
import io.github.jitinsharma.reduxmovieexample.storage.MovieDatabase
import timber.log.Timber
import tw.geothings.rekotlin.Store

/**
 * Created by jsharma on 15/01/18.
 */

val store = Store(
        reducer = ::appReducer,
        state = null,
        middleware = listOf(networkMiddleWare, databaseMiddleWare, movieMiddleWare)
)

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        debugMode { Timber.plant(Timber.DebugTree()) }
        movieDataBase = Room.databaseBuilder(this, MovieDatabase::class.java,
                "movieDB").build()
    }

    companion object {
        @get:Synchronized lateinit var instance: MovieApplication
            private set
        var movieDataBase: MovieDatabase? = null
    }
}