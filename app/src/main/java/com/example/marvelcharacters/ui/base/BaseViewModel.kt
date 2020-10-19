package com.example.marvelcharacters.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel ():ViewModel(){

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val loadingLD = MutableLiveData<Boolean>()
}