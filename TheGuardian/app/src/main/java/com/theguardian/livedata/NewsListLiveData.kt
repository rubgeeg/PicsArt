package com.theguardian.livedata

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.theguardian.models.SearchListModel
import com.theguardian.networking.APIFactory
import com.theguardian.networking.Request
import com.theguardian.utils.BaseViewModel

open class NewsListLiveData(application: Application) : BaseViewModel(application) {

    var baseData = MutableLiveData<SearchListModel>()

    fun searchNews(pageNum: Int = 1) {
        Request<SearchListModel>().apiCall(
            getApplication(), this, APIFactory.getAPI().search(pageNum), baseData
        )
    }

}