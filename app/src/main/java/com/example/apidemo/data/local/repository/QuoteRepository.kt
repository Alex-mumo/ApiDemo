package com.example.apidemo.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.data.network.QuoteApi
import com.example.apidemo.data.network.api.QuoteClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class QuoteRepository {

    private var quoteApi: QuoteApi? = null

    init {
        quoteApi = QuoteClient.getQuoteClient().create(QuoteApi::class.java)
    }

    suspend fun fetchAllQuotes() : LiveData<List<Quote>> {
        val quote =  MutableLiveData<List<Quote>>()

        quoteApi?.getAllQuotes()?.enqueue(object : Callback<List<Quote>>{


            override fun onFailure(call: Call<List<Quote>>, t : Throwable) {
                quote.value = null
            }

            override fun onResponse(
                call: Call<List<Quote>>,
                response: Response<List<Quote>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    quote.value = res
                }else{
                    quote.value = null
                }
            }
        })
        return quote

    }
}

