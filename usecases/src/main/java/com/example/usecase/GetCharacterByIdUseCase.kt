package com.example.usecase

import com.example.data.source.CharacterDataSource

class GetCharacterByIdUseCase(private val characterDataSource: CharacterDataSource) {

    suspend operator fun invoke(characterId: Long)= characterDataSource.getCharacterById(characterId)
}