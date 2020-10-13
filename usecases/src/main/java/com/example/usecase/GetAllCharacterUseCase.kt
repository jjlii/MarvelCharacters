package com.example.usecase

import com.example.data.source.CharacterDataSource

class GetAllCharacterUseCase(private val characterDataSource: CharacterDataSource) {

    suspend operator fun invoke()= characterDataSource.getAllCharacters()

}