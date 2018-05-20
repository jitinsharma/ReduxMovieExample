package io.github.jitinsharma.reduxmovieexample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.jitinsharma.reduxmovieexample.di.AppComponent
import io.github.jitinsharma.reduxmovieexample.di.DaggerAppComponent
import io.github.jitinsharma.reduxmovieexample.helpers.debugMode
import timber.log.Timber

/**
 * Created by jsharma on 15/01/18.
 */

class MovieApplication : DaggerApplication() {

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)

        debugMode { Timber.plant(Timber.DebugTree()) }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component

}
