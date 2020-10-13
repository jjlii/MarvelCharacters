package com.example.domain

import com.example.core.data.comic.ComicList
import com.example.core.data.event.EventList
import com.example.core.data.series.SeriesList
import com.example.core.data.story.StoryList
import java.util.*

data class Character(
    var id: Int?,
    var name: String?,
    var description: String?,
    var modified: Date?,
    var resourceURI: String?,
    var urls: Url?,
    var thumbnail: Image?,
    var comicList: ComicList?,
    var stories: StoryList?,
    var events: EventList?,
    var series: SeriesList?
 )