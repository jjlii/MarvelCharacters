package com.example.domain.entity

import java.util.*

data class Character(
    var id: Int?,
    var name: String?,
    var description: String?,
    var modified: Date?,
    var resourceURI: String?,
    var urls: List<Url>?,
    var thumbnail: Image?,
    var comics: WorkList?,
    var stories: WorkList?,
    var events: WorkList?,
    var series: WorkList?
 )