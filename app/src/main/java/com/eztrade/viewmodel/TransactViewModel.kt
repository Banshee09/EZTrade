package com.eztrade.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is transact Fragment"
    }
    val text: LiveData<String> = _text
}