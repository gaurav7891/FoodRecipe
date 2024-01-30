package com.nativeworks.foodrecipe.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.nativeworks.foodrecipe.databinding.FragmentInstructionBinding
import com.nativeworks.foodrecipe.model.Result

class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionBinding? = null
    private val binding get() = _binding

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstructionBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipesBundle")
        setupWebView(myBundle)
        return binding?.root
    }

    private fun setupWebView(myBundle: Result?) {
        binding?.instructionsWebView?.webViewClient = object : WebViewClient() {}
        val websiteUrl: String = myBundle!!.sourceUrl
        binding?.instructionsWebView?.loadUrl(websiteUrl)
    }
}