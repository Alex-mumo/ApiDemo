package com.example.apidemo.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.apidemo.R
import com.example.apidemo.databinding.FragmentSplashBinding


@Suppress("DEPRECATION")
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        Handler().postDelayed({
            findNavController().navigate(R.id.splashFragment)
        }, 5000)
        return binding.root
    }
}