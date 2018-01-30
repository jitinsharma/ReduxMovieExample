package io.github.jitinsharma.reduxmovieexample.actions

import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 30/01/18.
 */

data class checkForFavorites(val unit: Unit? = null) : Action

data class setInitialCount(val count: Int) : Action

data class increment(val unit: Unit? = null) : Action

data class decrement(val unit: Unit? = null) : Action