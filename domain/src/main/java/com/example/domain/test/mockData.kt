package com.example.domain.test

import com.example.domain.entity.Character
import com.example.domain.entity.CharacterResp
import com.example.domain.entity.DataContainer
import java.util.*

val mockedCharacter: Character = Character(
    id = 1, name = "", description = "",
    modified = Date(), resourceURI = "",
    urls = null,
    thumbnail = null,
    comics = null,
    stories = null,
    events = null,
    series = null
)

val mockedDataContainer = DataContainer(
    0, 1, 1,1, arrayListOf(mockedCharacter)
)

val mockedCharacterRespSuccess = CharacterResp(
    200,
    "OK",
    mockedDataContainer
)

val mockedCharacterRespConflict = CharacterResp(
    409,
    "Conflict",
    null
)


