package com.example.marvelcharacters.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel (application: Application):AndroidViewModel(application){

    val loadingLD = MutableLiveData<Boolean>()

}