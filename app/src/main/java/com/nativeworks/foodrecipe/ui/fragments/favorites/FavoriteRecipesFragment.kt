package com.nativeworks.foodrecipe.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nativeworks.foodrecipe.R
import com.nativeworks.foodrecipe.adapters.FavoritesRecipesAdapter
import com.nativeworks.foodrecipe.databinding.FragmentFavoriteRecipesBinding
import com.nativeworks.foodrecipe.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding

    private val mAdapter: FavoritesRecipesAdapter by lazy { FavoritesRecipesAdapter(requireActivity(),mainViewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = this
        binding?.mainViewModel = mainViewModel
        binding?.mAdapter = mAdapter

        setHasOptionsMenu(true)

        setupRecyclerView()
//        mainViewModel.readFavoriteRecipes.observe(viewLifecycleOwner) { favoritesEntity ->
//            mAdapter.setData(favoritesEntity)
//        }
        return binding?.root
    }

    private fun setupRecyclerView() {
        binding?.favoritesRecyclerView?.adapter = mAdapter
        binding?.favoritesRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorites_recipes_menu,menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId ==  R.id.deleteAll){
            mainViewModel.deleteAllFavoriteRecipe()
            showSnackBar()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showSnackBar() {
        Snackbar.make(binding!!.root, "All Recipes Removed", Snackbar.LENGTH_SHORT).setAction("Okay", {}).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mAdapter.clearContextualActionMode()
    }

}