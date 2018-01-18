package io.github.jitinsharma.reduxmovieexample.models

import com.google.gson.annotations.SerializedName

/**
 * Created by jsharma on 18/01/18.
 */

data class MovieResponse(
        @SerializedName("page")
        var page : Int = 0,
        @SerializedName("results")
        var results : List<MovieObject>? = listOf(),
        @SerializedName("total_results")
        var totalResults : Int = 0,
        @SerializedName("total_pages")
        var totalPages : Int = 0
)