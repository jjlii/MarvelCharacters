package com.example.domain

data class DataContainer(
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: List<Character>?
)