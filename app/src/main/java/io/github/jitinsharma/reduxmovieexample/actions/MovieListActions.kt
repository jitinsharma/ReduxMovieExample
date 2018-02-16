package io.github.jitinsharma.reduxmovieexample.actions

import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 15/01/18.
 */

class InitializeMovieList(val movieObjects: List<MovieObject>) : Action

class DisplayMovies(val movieObjects: List<MovieObject>) : Action

class LoadTopRatedMovies : Action

class AddMovieToFavorites(val movieObject: MovieObject) : Action

class RemoveMovieFromFavorites(val movieObject: MovieObject) : Action