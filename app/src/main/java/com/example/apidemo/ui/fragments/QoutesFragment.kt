package com.example.apidemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.apidemo.R
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.databinding.FragmentQoutesBinding
import com.example.apidemo.ui.viewmodel.QuoteViewModel
import com.example.apidemo.util.Resource
import kotlin.text.Typography.quote

class QoutesFragment : Fragment(R.layout.fragment_qoutes) {

    private lateinit var binding: FragmentQoutesBinding
    private var quote: Quote? = null

    private val viewModel by activityViewModels<QuoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.quote.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Loading -> {
                    showProgressBar()
                    binding.noQuote.visibility = View.GONE

                }
                is Resource.Success -> {
                    hideProgressBar()
                    response.data.let { qouteResponse ->
                        quote = qouteResponse
                        dispayData()
                    }

                }
                is Resource.Error -> {
                    hideProgressBar()

                }
            }

        })
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