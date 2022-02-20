package com.lucas.schiavini.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie (
    //    val name: String?, if attribute matches the name got from server, no need for @SerializedName
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "duration")
    @SerializedName("duration")
    val duration: String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val score: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val imageUrl: String?,

) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

    @Ignore
    val imageBaseUrl: String = "https://image.tmdb.org/t/p/original/"
}