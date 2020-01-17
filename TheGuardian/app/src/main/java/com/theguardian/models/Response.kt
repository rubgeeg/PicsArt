package com.theguardian.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Response(
    @ColumnInfo(name = "currentPage")
    @SerializedName("currentPage")
    val currentPage: Int,
    @ColumnInfo(name = "orderBy")
    @SerializedName("orderBy")
    val orderBy: String,
    @ColumnInfo(name = "pageSize")
    @SerializedName("pageSize")
    val pageSize: Int,
    @ColumnInfo(name = "pages")
    @SerializedName("pages")
    val pages: Int,
    @ColumnInfo(name = "results")
    @SerializedName("results")
    val results: ArrayList<Result>,
    @ColumnInfo(name = "startIndex")
    @SerializedName("startIndex")
    val startIndex: Int,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    val status: String,
    @PrimaryKey
    @ColumnInfo(name = "total")
    @SerializedName("total")
    val total: Long,
    @ColumnInfo(name = "userTier")
    @SerializedName("userTier")
    val userTier: String

)
