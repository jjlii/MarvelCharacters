package com.example.marvelcharacters.ui.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.failure.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel ():ViewModel(){

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val loadingLD = MutableLiveData<Boolean>()
    val failureLD = MutableLiveData<Failure>()
}