package io.github.jitinsharma.reduxmovieexample.states

import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import tw.geothings.rekotlin.StateType

/**
 * Created by jsharma on 11/02/18.
 */

data class FavoriteListState(
        val displayNoFavoriteMessage: Boolean = false,
        val favorites: List<MovieObject>? = null
): StateType