package com.example.marvelcharacters.ui.character_details

import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.Character
import com.example.domain.failure.Failure
import com.example.marvelcharacters.ui.base.BaseViewModel
import com.example.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(private val getCharacterByIdUseCase: GetCharacterByIdUseCase
): BaseViewModel() {

    val characterLD = MutableLiveData<Character?>()
    val failureLD = MutableLiveData<Failure>()


    fun getCharacterById(characterId: Long){
        loadingLD.postValue(true)
        coroutineScope.launch {
            getCharacterByIdUseCase.invoke(characterId).fold(
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