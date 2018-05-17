package io.github.jitinsharma.reduxmovieexample.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.jitinsharma.reduxmovieexample.MovieApplication
import javax.inject.Singleton

/**
 * Created by dmba on 5/17/18.
 */

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class
])
interface AppComponent : AndroidInjector<MovieApplication> {

    override fun inject(app: MovieApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MovieApplication): Builder

        fun build(): AppComponent
    }
}
