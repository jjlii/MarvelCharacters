package com.example.marvelcharacters.ui.character_details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.domain.Character
import com.example.domain.failure.Failure
import com.example.marvelcharacters.ui.base.BaseViewModel
import com.example.usecase.GetCharacterByIdUseCase

class CharacterDetailsViewModel(private val getCharacterByIdUseCase: GetCharacterByIdUseCase
): BaseViewModel() {

    val characterLD = MutableLiveData<Character?>()

    fun getCharacterById(characterId: Long){
        loadingLD.postValue(true)
        getCharacterByIdUseCase(characterId){
            it.fold(
                ::handleFailedGetCharactersById,
                ::handleSuccessGetCharacterById
            )
        }
    }

    private fun handleFailedGetCharactersById(failure: Failure) {
        loadingLD.postValue(false)
        failureLD.postValue(failure)
    }
    private fun handleSuccessGetCharacterById(character: Character?) {
        loadingLD.postValue(false)
        characterLD.postValue(character)
    }

}