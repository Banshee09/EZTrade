package com.eztrade.viewmodel


import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactViewModel : ViewModel() {

    val asset = MutableLiveData<String>()


    public fun fillOrder() {
        Log.d("TransactViewModel", asset.value)
    }

    public fun placeOrder() {

    }
}