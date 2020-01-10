package com.theguardian.networking

import com.theguardian.models.SearchListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {


    companion object {

        // API Endpoints
        const val API_SEARCH =
            "search?q=12%20years%20a%20slave&format=json&from-date=2010-01-01&show-tags=contributor&show-fields=starRating,headline,thumbnail,bodyText,short-url&order-by=relevance&api-key=1e36789d-9486-4628-8ec3-5538f3433a97"

    }

    @GET(API_SEARCH)
    fun search(
        @Query("page") pageNum: Int
    ): Call<SearchListModel>

}
