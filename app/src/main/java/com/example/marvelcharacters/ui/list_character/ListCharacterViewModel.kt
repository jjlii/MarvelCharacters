package com.example.marvelcharacters.ui.list_character

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.domain.Character
import com.example.domain.failure.Failure
import com.example.marvelcharacters.ui.base.BaseViewModel
import com.example.usecase.GetAllCharacterUseCase

class ListCharacterViewModel(private val getAllCharacterUseCase: GetAllCharacterUseCase)
    : BaseViewModel() {


    val charactersListLD = MutableLiveData<List<Character>?>()

    fun getAllCharacters(){
        loadingLD.postValue(true)
        getAllCharacterUseCase(Unit){
            it.fold(
                ::handleFailedGetAllCharacters,
                ::handleSuccessGetAllCharacters
            )
        }
    }

    private fun handleFailedGetAllCharacters(failure: Failure)  {
        loadingLD.postValue(false)
        failureLD.postValue(failure)
    }

    private fun handleSuccessGetAllCharacters(allCharacters: List<Character>?) {
        loadingLD.postValue(false)
        charactersListLD.postValue(allCharacters)
    }


}