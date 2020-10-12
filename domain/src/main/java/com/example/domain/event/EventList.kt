package com.example.core.data.event

data class EventList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<EventSummary>?
)