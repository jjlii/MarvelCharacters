package com.example.core.data.series

data class SeriesList (
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var item: ArrayList<SeriesSummary>?
)