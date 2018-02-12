package io.github.jitinsharma.reduxmovieexample.reducers

import io.github.jitinsharma.reduxmovieexample.states.AppState
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 18/01/18.
 */

fun appReducer(action: Action, appState: AppState?): AppState =
        AppState(
                movieListState = movieListReducer(action, appState?.movieListState),
                favoriteCounterState = favoriteCounterReducer(action, appState?.favoriteCounterState),
                favoriteListState = favoriteListReducer(action, appState?.favoriteListState)
        )