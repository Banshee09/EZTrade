package com.eztrade.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Research"
    }
    val text: LiveData<String> = _text

    private val _url = MutableLiveData<String>().apply {
        value = ""
    }
    val url: LiveData<String> = _url


    public fun setMarketURL() {
        _url.value =
            "https://api.stockdio.com/visualization/financial/charts/v1/MarketOverview?app-key=3D38B8238ACB43749DD683FE6768A909&stockExchange=ASX"
    }

    public fun setShareURL(share: String) {
        _url.value =
            "https://api.stockdio.com/visualization/financial/charts/v1/HistoricalPrices?app-key=3D38B8238ACB43749DD683FE6768A909&stockExchange=ASX&symbol=" + share
    }

}