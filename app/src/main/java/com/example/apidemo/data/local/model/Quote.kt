package com.example.apidemo.data.local.model

import androidx.room.Entity

@Entity(tableName = "quotes_table")
data class Quote(
    var id: Int,
    var title: String,
    var author: String
)