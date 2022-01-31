package com.example.apidemo.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.apidemo.data.local.model.Quote

@Dao
interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuote(quote: Quote): Long


    @Query("SELECT * FROM quotes_table")
    fun getQuotes(): LiveData<List<Quote>>

    @Delete
    suspend fun deleteQuote(quote: Quote)


}