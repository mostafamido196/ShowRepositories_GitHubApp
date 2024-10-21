package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issue_list

import com.google.gson.annotations.SerializedName

data class Reactions(
    @SerializedName("+1")
    val plusOne: Int,  // "+1"

    @SerializedName("-1")
    val minusOne: Int, // "-1"

    @SerializedName("confused")
    val confused: Int,

    @SerializedName("eyes")
    val eyes: Int,

    @SerializedName("heart")
    val heart: Int,

    @SerializedName("hooray")
    val hooray: Int,

    @SerializedName("laugh")
    val laugh: Int,

    @SerializedName("rocket")
    val rocket: Int,

    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("url")
    val url: String
)
