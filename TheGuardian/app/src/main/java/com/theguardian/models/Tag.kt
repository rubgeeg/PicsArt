package com.theguardian.models

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("apiUrl")
    val apiUrl: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String
)