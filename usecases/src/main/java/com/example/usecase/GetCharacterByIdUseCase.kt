package com.example.usecase

import com.example.data.source.CharacterDataSource
import com.example.domain.Character
import com.example.domain.Either
import com.example.domain.failure.Failure

class GetCharacterByIdUseCase(private val characterDataSource: CharacterDataSource) : UseCase<Failure, Character?,Long>(){

    override suspend fun run(params: Long): Either<Failure, Character?>
            = characterDataSource.getCharacterById(params)
}