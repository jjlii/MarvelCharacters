package com.example.data.source

import com.example.domain.Character
import com.example.domain.Either
import com.example.domain.failure.Failure

interface CharacterDataSource {

    suspend fun getAllCharacters() : Either<Failure, List<Character>>

    suspend fun getCharacterById(characterId: Long) : Either<Failure, Character>

}