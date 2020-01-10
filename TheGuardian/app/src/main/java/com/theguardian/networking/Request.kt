package com.theguardian.networking

import android.content.Context
import androidx.lifecycle.MutableLiveData

import com.theguardian.utils.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class Request<T> {

    lateinit var call: Call<T>

    fun apiCall(
        context: Context,
        baseViewModel: BaseViewModel,
        call: Call<T>,
        baseJsonMutable: MutableLiveData<T>,
        loadProgress: Boolean = true
    ): Request<T> {

        this.call = call


        if (loadProgress)
            baseViewModel.apiRunning.postValue(true)

        try {

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    baseViewModel.apiRunning.postValue(false)
               //     showToast(context, context.getString(R.string.something_went_wrong))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        baseJsonMutable.postValue(response.body())
                    }
                    baseViewModel.apiRunning.postValue(false)
                }

            })
        } catch (e: Exception) {
            baseViewModel.apiRunning.postValue(false)
        }
        return this
    }

    fun cancel() {
        if (::call.isInitialized) {
            call.cancel()
        }
    }
}