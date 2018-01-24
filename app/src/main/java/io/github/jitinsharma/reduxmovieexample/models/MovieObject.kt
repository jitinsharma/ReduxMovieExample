package io.github.jitinsharma.reduxmovieexample.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieObject(
        @ColumnInfo(name = "adult")
        @SerializedName("adult")
        var adult: Boolean? = null,

        @ColumnInfo(name = "backdrop_path")
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,

        @Ignore
        var genreIds: List<Long>? = null,

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: Long? = null,

        @ColumnInfo(name = "original_language")
        @SerializedName("original_language")
        var originalLanguage: String? = null,

        @ColumnInfo(name = "original_title")
        @SerializedName("original_title")
        var originalTitle: String? = null,

        @ColumnInfo(name = "overview")
        @SerializedName("overview")
        var overview: String? = null,

        @ColumnInfo(name = "popularity")
        @SerializedName("popularity")
        var popularity: Double? = null,

        @ColumnInfo(name = "poster_path")
        @SerializedName("poster_path")
        var posterPath: String? = null,

        @ColumnInfo(name = "release_date")
        @SerializedName("release_date")
        var releaseDate: String? = null,

        @ColumnInfo(name = "title")
        @SerializedName("title")
        var title: String? = null,

        @ColumnInfo(name = "video")
        @SerializedName("video")
        var video: Boolean? = null,

        @ColumnInfo(name = "vote_average")
        @SerializedName("vote_average")
        var voteAverage: Double? = null,

        @ColumnInfo(name = "vote_count")
        @SerializedName("vote_count")
        var voteCount: Long? = null
)