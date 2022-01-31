package com.example.apidemo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apidemo.data.local.dao.QuotesDao
import com.example.apidemo.data.local.model.Quote


@Database(entities = [Quote::class], version = 1)
abstract class QuotesDb : RoomDatabase() {

    abstract fun getQuoteDao(): QuotesDao

}