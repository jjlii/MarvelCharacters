package com.example.core.data.story

data class StoryList (
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<StorySummary>?
)