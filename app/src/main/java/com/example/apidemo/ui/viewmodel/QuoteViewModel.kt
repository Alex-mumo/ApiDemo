package com.example.apidemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.data.repository.QuoteRepository
import com.example.apidemo.util.Internet
import com.example.apidemo.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException


class QuoteViewModel(private val quoteRepository: QuoteRepository, private val internet: Internet) : ViewModel() {

    val quote: MutableLiveData<Resource<Quote>> = MutableLiveData()

    init {
        fetchQuotes()
    }

    fun fetchQuotes() = viewModelScope.launch {
        safeQuoteCall()
    }

    private suspend fun safeQuoteCall() {
        try {
            if (internet.hasInternet()){
                quote.postValue(Resource.Loading())
                val response = quoteRepository.getQuotes()
                quote.postValue(handleResponse(response))
            }
            else {
                quote.postValue(Resource.Error("No internet"))
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> quote.postValue(Resource.Error("Network Failure"))
                else -> quote.postValue(Resource.Error("Another error"))
            }

        }

    }

    private fun handleResponse(response: Response<List<Quote>>): Resource<Quote> {
        if (response.isSuccessful){
            return Resource.Success(response.body()!![0])
        }
        return Resource.Error(response.message())
    }

    fun saveQuote(quote: Quote) = viewModelScope.launch {
        quoteRepository.saveQuote(quote)
    }
    fun deleteQuote(quote: Quote) = viewModelScope.launch {
        quoteRepository.deleteQuote(quote)
    }

}