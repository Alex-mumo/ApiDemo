package com.example.apidemo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.data.repository.QuoteRepository
import com.example.apidemo.util.Resource
import kotlinx.coroutines.launch


class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    val quote: MutableLiveData<Resource<Quote>> = MutableLiveData()

    init {
        fetchQuotes()
    }

    private fun fetchQuotes() = viewModelScope.launch {
        safeQuoteCall()
    }

    private fun safeQuoteCall() {
        try {


        }catch (t: Throwable){

        }

    }

    fun saveQuote(quote: Quote) = viewModelScope.launch {
        quoteRepository.saveQuote(quote)
    }
    fun deleteQuote(quote: Quote) = viewModelScope.launch {
        quoteRepository.deleteQuote(quote)
    }

}