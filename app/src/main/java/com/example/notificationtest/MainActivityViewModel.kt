package com.example.notificationtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var message1 = MutableLiveData<String>()
}