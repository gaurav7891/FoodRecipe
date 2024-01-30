package com.nativeworks.foodrecipe.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nativeworks.foodrecipe.R
import com.nativeworks.foodrecipe.adapters.IngredientsAdapter
import com.nativeworks.foodrecipe.databinding.FragmentIngredientBinding
import com.nativeworks.foodrecipe.model.Result


class IngredientFragment : Fragment() {

    private var _binding: FragmentIngredientBinding? = null
    private val binding get() = _binding

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipesBundle")

        setupRecyclerView()
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }
        return binding?.root
    }

    private fun setupRecyclerView() {
        binding?.recyclerViewIngredients?.adapter = mAdapter
        binding?.recyclerViewIngredients?.layoutManager = LinearLayoutManager(requireContext())

    }


}