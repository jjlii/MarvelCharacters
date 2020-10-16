package com.example.domain

import com.example.domain.entity.Character
import com.example.domain.failure.Failure

interface CharacterDataSource {

    suspend fun getAllCharacters(offset: Int) : Either<Failure, List<Character>?>

    suspend fun getCharacterById(characterId: Long) : Either<Failure, Character?>

}