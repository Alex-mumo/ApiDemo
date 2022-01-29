package com.example.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.databinding.ActivityMainBinding
import com.example.apidemo.ui.QuoteAdapter
import com.example.apidemo.viewmodel.QuoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var quoteViewModel: QuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)

        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]

        initAdapter()
        quoteViewModel.fetchAllQuotes()

        quoteViewModel.qouteListLiveData?.observe(this, Observer {
            if (it!=null){
                binding.recyclerView.visibility = View.VISIBLE
                quoteAdapter.setData(it as ArrayList<Quote>)
            }else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        })

    }

    private fun initAdapter() {
        quoteAdapter = QuoteAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = quoteAdapter
    }
}