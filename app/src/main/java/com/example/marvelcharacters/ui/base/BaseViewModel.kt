package com.example.marvelcharacters.ui.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.failure.Failure

abstract class BaseViewModel ():ViewModel(){

    val loadingLD = MutableLiveData<Boolean>()
    val failureLD = MutableLiveData<Failure>()

}