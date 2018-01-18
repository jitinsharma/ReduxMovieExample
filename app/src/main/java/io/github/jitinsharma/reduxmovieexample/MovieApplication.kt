package io.github.jitinsharma.reduxmovieexample

import android.app.Application
import io.github.jitinsharma.reduxmovieexample.middlewares.networkMiddleWare
import io.github.jitinsharma.reduxmovieexample.reducers.appReducer
import timber.log.Timber
import tw.geothings.rekotlin.Store

/**
 * Created by jsharma on 15/01/18.
 */

val store = Store(
        reducer = ::appReducer,
        state = null,
        middleware = listOf(networkMiddleWare)
)
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        @get:Synchronized lateinit var instance: MovieApplication
            private set
    }
}