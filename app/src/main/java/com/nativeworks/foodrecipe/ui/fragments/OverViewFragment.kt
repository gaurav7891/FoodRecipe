package com.nativeworks.foodrecipe.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.nativeworks.foodrecipe.R
import com.nativeworks.foodrecipe.databinding.FragmentOverViewBinding
import com.nativeworks.foodrecipe.model.Result
import org.jsoup.Jsoup


class OverViewFragment : Fragment() {

    private var _binding: FragmentOverViewBinding? = null
    private val binding get() = _binding

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOverViewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipesBundle")
        binding?.let {
            with(it) {
                mainImageView.load(myBundle?.image)
                txtTitle.text = myBundle?.title
                txtLikes.text = myBundle?.aggregateLikes.toString()
                txtTime.text = myBundle?.readyInMinutes.toString()
                myBundle?.summary?.let {
                    val summary = Jsoup.parse(it).text()
                    txtSummary.text = summary
                }

                if (myBundle?.vegetarian == true) {
                    imgCheckVegetarian.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    txtVeg.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                }

                if (myBundle?.vegan == true) {
                    imgCheckVegan.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    txtVegan.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                }

                if (myBundle?.glutenFree == true) {
                    imgGlutenFree.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    txtGlutenFree.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                if (myBundle?.dairyFree == true) {
                    imgDairyFree.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    txtDiaryFree.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                if (myBundle?.cheap == true) {
                    imgCheap.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                    txtCheap.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                }
            }
        }


        return binding?.root
    }

}