package com.example.marvelcharacters.data

import com.example.domain.CharacterDataContainer

data class CharacterDataWrapper(
    var code: Int?,
    var status: String?,
    var data: List<CharacterDataContainer>?
)