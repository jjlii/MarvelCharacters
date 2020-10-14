package com.example.usecase

import com.example.data.source.CharacterDataSource
import com.example.domain.Character
import com.example.domain.Either
import com.example.domain.failure.Failure

class GetAllCharacterUseCase(private val characterDataSource: CharacterDataSource):  UseCase<Failure,List<Character>?,Unit>(){

    override suspend fun run(params: Unit): Either<Failure, List<Character>?> = characterDataSource.getAllCharacters()

}