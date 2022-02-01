package com.example.apidemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.apidemo.R
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.databinding.FragmentQoutesBinding
import com.example.apidemo.ui.viewmodel.QuoteViewModel
import com.example.apidemo.util.Resource

class QoutesFragment : Fragment(R.layout.fragment_qoutes) {

    private lateinit var binding: FragmentQoutesBinding
    private var quote: Quote? = null
    private var showQuote = false
    private val viewModel: QuoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

        viewModel.quote.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Loading -> {
                    showProgressBar()
                    binding.noQuote.visibility = View.GONE
                    showQuote = false
                    quote = null

                }
                is Resource.Success -> {
                    hideProgressBar()
                    response.data.let { qouteResponse ->
                        quote = qouteResponse!!
                        binding.noQuote.visibility = View.GONE
                        dispayData()
                    }
                    showQuote = true
                }
                is Resource.Error -> {
                    hideProgressBar()
                    hideData()
                    binding.noQuote.visibility = View.VISIBLE
                    response.message.let {
                        binding.noQuote.text = it
                    }
                    showQuote = false
                    quote = null

                }
            }

        })
    }


    private fun hideData() {
        binding.authorTv.visibility = View.GONE
        binding.quoteTv.visibility = View.GONE
    }

    private fun dispayData() {
        binding.authorTv.visibility = View.VISIBLE
        binding.quoteTv.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.loadingQt.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.loadingQt.visibility = View.VISIBLE
        
    }
}