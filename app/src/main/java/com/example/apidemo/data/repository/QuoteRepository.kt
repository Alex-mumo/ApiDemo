package com.example.apidemo.data.repository

import com.example.apidemo.data.local.dao.QuotesDao
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.data.network.QuoteApi


class QuoteRepository(private val quoteApi: QuoteApi, private val quotesDao: QuotesDao) {

    suspend fun getQuotes() = quoteApi.getAllQuotes()

    suspend fun saveQuote(quote: Quote) = quotesDao.saveQuote(quote)

    suspend fun deleteQuote(quote: Quote) = quotesDao.deleteQuote(quote)

}

