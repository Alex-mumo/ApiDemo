package com.example.apidemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.data.local.repository.QuoteRepository

class QuoteViewModel(application: Application) : AndroidViewModel(application) {

    private var quoteRepository: QuoteRepository? = null
    private var qouteListLiveData: LiveData<List<Quote>>? = null

    init {
        quoteRepository = QuoteRepository()
        qouteListLiveData = MutableLiveData()
    }

    fun fetchAllQuotes() {
        qouteListLiveData = quoteRepository?.fetchAllQuotes()
    }

}