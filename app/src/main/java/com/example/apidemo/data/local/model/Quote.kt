package com.example.apidemo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_table")
data class Quote(
    @PrimaryKey
    var id: Int,
    var title: String,
    var author: String
)