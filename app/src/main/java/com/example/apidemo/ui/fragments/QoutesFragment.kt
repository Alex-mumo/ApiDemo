package com.example.apidemo.ui.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.apidemo.R
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.databinding.FragmentQoutesBinding
import com.example.apidemo.ui.viewmodel.QuoteViewModel
import com.example.apidemo.util.Constants
import com.example.apidemo.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min

class QoutesFragment : Fragment(R.layout.fragment_qoutes) {
    private lateinit var binding: FragmentQoutesBinding
    private var quote: Quote? = null
    private var showQuote = false
    val viewModel: QuoteViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQoutesBinding.inflate(inflater, container, false)
        binding.cardQuote.setOnTouchListener(
            View.OnTouchListener { view, event ->
                val displayMetrics = resources.displayMetrics
                val cardWidth = binding.cardQuote.width
                val cardStart = (displayMetrics.widthPixels.toFloat() / 2) - (cardWidth / 2)

                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        //hold the current position of the
                        var currentX = binding.cardQuote.x
                        binding.cardQuote.animate().x(cardStart)
                            .setDuration(150)
                            .setListener(object : AnimatorListenerAdapter()  {
                                override fun onAnimationEnd(animation: Animator?) {
                                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
                                        delay(100)
                                        if (currentX < Constants.SWIPE_DISTANCE){
                                            viewModel.fetchQuotes()
                                            currentX = 0f
                                        }
                                    }
                                    //super.onAnimationEnd(animation)
                                }
                            }).start()
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val newX = event.rawX
                        if(newX - cardWidth < cardStart) {
                            binding.cardQuote.animate().x(min(cardStart, newX - (cardWidth / 2))).setDuration(0).start()
                            if (binding.cardQuote.x < Constants.SWIPE_DISTANCE){
                                TODO("DO something")
                            }else {
                                TODO("Do something else")
                            }
                        }
                    }

                }
                view.performClick()
                return@OnTouchListener true

            })
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }


    private fun setUpUI() {
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