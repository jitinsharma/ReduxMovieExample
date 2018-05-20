package io.github.jitinsharma.reduxmovieexample.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.jitinsharma.reduxmovieexample.MovieApplication
import io.github.jitinsharma.reduxmovieexample.helpers.BASE_API_URL
import io.github.jitinsharma.reduxmovieexample.middlewares.DatabaseMiddleWare
import io.github.jitinsharma.reduxmovieexample.middlewares.MovieMiddleWare
import io.github.jitinsharma.reduxmovieexample.middlewares.NetworkMiddleWare
import io.github.jitinsharma.reduxmovieexample.network.ApiInterface
import io.github.jitinsharma.reduxmovieexample.reducers.appReducer
import io.github.jitinsharma.reduxmovieexample.states.AppStore
import io.github.jitinsharma.reduxmovieexample.storage.MovieDao
import io.github.jitinsharma.reduxmovieexample.storage.MovieDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.geothings.rekotlin.Store
import javax.inject.Singleton

/**
 * Created by dmba on 5/17/18.
 */

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: MovieApplication): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Context): MovieDatabase = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movieDB")
            .build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()


    @Provides
    @Singleton
    fun provideStore(movieMiddleWare: MovieMiddleWare,
                     networkMiddleWare: NetworkMiddleWare,
                     databaseMiddleWare: DatabaseMiddleWare): AppStore = Store(
            reducer = ::appReducer,
            state = null,
            middleware = listOf(networkMiddleWare, databaseMiddleWare, movieMiddleWare)
    )
}
