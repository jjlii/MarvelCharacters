package com.example.usecase

import com.example.domain.CharacterDataSource
import com.example.domain.Either
import com.example.domain.entity.Character
import com.example.domain.failure.Failure

class GetAllCharacterUseCase(private val characterDataSource: CharacterDataSource){

    suspend fun invoke(params: Int): Either<Failure, List<Character>?> = characterDataSource.getAllCharacters(params)

}