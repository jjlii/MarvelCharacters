package com.example.marvelcharacters.data

import com.example.domain.Character

data class CharacterResp(
    var code: Int?,
    var status: String?,
    var data: List<Character>?
)