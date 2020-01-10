package com.theguardian.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIFactory {

    fun getAPI(): APIs = Retrofit.Builder()
        .client(getHttpClient())
        .baseUrl(Constants.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(APIs::class.java)

    private fun getHttpClient(): OkHttpClient {
        val authInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.withConnectTimeout(20, TimeUnit.SECONDS)
            chain.withReadTimeout(20, TimeUnit.SECONDS)
            chain.withWriteTimeout(20, TimeUnit.SECONDS)
            chain.proceed(newRequest)
        }
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

}
