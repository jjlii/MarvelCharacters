package com.example.core.data.comic

data class ComicList (
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<ComicList>?
)