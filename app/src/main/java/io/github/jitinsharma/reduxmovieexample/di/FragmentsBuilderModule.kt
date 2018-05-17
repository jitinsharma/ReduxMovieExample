package io.github.jitinsharma.reduxmovieexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.jitinsharma.reduxmovieexample.ui.FavoriteListFragment
import io.github.jitinsharma.reduxmovieexample.ui.MovieListFragment

/**
 * Created by dmba on 5/17/18.
 */

@Module
interface FragmentsBuilderModule {
    @ContributesAndroidInjector
    fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    fun contributeFavouriteListFragment(): FavoriteListFragment
}
