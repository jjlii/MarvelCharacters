package com.example.marvelcharacters.ui.list_character

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.domain.Character
import com.example.domain.failure.Failure
import com.example.marvelcharacters.ui.base.BaseViewModel
import com.example.usecase.GetAllCharacterUseCase

class ListCharacterViewModel(application: Application,
                             private val getAllCharacterUseCase: GetAllCharacterUseCase): BaseViewModel(application) {


    val charactersListLD = MutableLiveData<List<Character>?>()

    val failureLD = MutableLiveData<Failure>()

    fun getAllCharacters(){
        getAllCharacterUseCase(Unit){
            it.fold(
                ::handleFailedGetAllCharacters,
                ::handleSuccessGetAllCharacters
            )
        }
    }

    private fun handleFailedGetAllCharacters(failure: Failure)  {
        failureLD.postValue(failure)
    }

    private fun handleSuccessGetAllCharacters(allCharacters: List<Character>?) {
        charactersListLD.postValue(allCharacters)
    }


}