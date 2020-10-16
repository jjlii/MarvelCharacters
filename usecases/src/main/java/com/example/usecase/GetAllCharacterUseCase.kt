package com.example.usecase

import com.example.domain.entity.Character
import com.example.domain.CharacterDataSource
import com.example.domain.Either
import com.example.domain.failure.Failure

class GetAllCharacterUseCase(private val characterDataSource: CharacterDataSource):  UseCase<Failure,List<Character>?,Int>(){

    override suspend fun run(params: Int): Either<Failure, List<Character>?> = characterDataSource.getAllCharacters(params)


}