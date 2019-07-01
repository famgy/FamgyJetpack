package com.famgy.famgyjetpack.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var isLogined = MutableLiveData<Boolean>()

    fun getLoginCatch() = isLogined.value == true
}