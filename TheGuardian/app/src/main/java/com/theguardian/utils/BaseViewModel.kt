package com.theguardian.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var apiRunning = MutableLiveData<Boolean>()
    var logout = MutableLiveData<Boolean>()
}