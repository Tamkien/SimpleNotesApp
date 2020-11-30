package com.kienct.simplenotesapp.entities

class Item(
        var id: Int?,
        var title: String,
        var lastEdited: String,
        var content: String,
        var isFavourite: Int = 0
)