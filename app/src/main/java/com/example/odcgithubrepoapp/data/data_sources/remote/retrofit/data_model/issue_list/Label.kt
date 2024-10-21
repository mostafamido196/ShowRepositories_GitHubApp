package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.issue_list

import com.google.gson.annotations.SerializedName

data class Label(
    @SerializedName("color")
    val color: String,

    @SerializedName("default")
    val default: Boolean,

    @SerializedName("description")
    val description: String,

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("url")
    val url: String
)
