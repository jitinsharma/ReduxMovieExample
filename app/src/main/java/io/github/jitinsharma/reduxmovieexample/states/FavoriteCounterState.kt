package io.github.jitinsharma.reduxmovieexample.states

import tw.geothings.rekotlin.StateType

/**
 * Created by jsharma on 30/01/18.
 */

data class FavoriteCounterState(val favoriteCount: Int = 0) : StateType