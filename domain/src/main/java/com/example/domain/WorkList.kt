package com.example.domain

data class WorkList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: ArrayList<WorkSummary>?
)