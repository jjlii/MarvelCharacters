package com.example.domain.entity

import com.example.domain.entity.Character

data class DataContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: List<Character>?
)