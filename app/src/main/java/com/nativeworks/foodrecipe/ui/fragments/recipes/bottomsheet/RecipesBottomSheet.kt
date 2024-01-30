package com.nativeworks.foodrecipe.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nativeworks.foodrecipe.R
import com.nativeworks.foodrecipe.databinding.RecipesBottomSheetBinding
import com.nativeworks.foodrecipe.util.Constants.DEFAULT_DIET_TYPE
import com.nativeworks.foodrecipe.util.Constants.DEFAULT_MEAL_TYPE
import com.nativeworks.foodrecipe.viewmodels.RecipesViewModel
import java.lang.Exception
import java.util.Locale

class RecipesBottomSheet : BottomSheetDialogFragment() {

    private val TAG = RecipesBottomSheet::class.simpleName

    private var _binding: RecipesBottomSheetBinding? = null
    private val binding get() = _binding

    private lateinit var recipesViewModel: RecipesViewModel
    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecipesBottomSheetBinding.inflate(inflater, container, false)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId, binding?.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, binding?.dietTypeChipGroup)
        }

        @Suppress("DEPRECATION")
        binding?.mealTypeChipGroup?.setOnCheckedChangeListener { group, checkedIds ->
            val chip = group.findViewById<Chip>(checkedIds)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = checkedIds
        }

        @Suppress("DEPRECATION")
        binding?.dietTypeChipGroup?.setOnCheckedChangeListener { group, checkedIds ->
            val chip = group.findViewById<Chip>(checkedIds)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = checkedIds
        }

        binding?.btnApply?.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )
            val action = RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment()
            findNavController().navigate(action)
        }

        return binding?.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup?) {
        if (chipId != 0) {
            try {
                chipGroup?.findViewById<Chip>(chipId)?.isChecked = true
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}