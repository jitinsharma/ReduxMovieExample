package io.github.jitinsharma.reduxmovieexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.jitinsharma.reduxmovieexample.ui.MainActivity

/**
 * Created by dmba on 5/17/18.
 */

@Module
interface ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentsBuilderModule::class])
    fun contributeMainActivity(): MainActivity
}
