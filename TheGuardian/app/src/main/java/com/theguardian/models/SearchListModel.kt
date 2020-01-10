package com.theguardian.models

import com.google.gson.annotations.SerializedName

class SearchListModel(
    @SerializedName("response")
    val response: Response
)