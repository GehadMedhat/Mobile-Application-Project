package com.example.fulify

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentMealSchedulingBinding

class MealSchedulingFragment : Fragment(R.layout.fragment_meal_scheduling) {

    private var _binding: FragmentMealSchedulingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMealSchedulingBinding.bind(view)

        setupBackButton()
    }

    private fun setupBackButton() {
        // زرار السهم (رجوع)
        binding.ra4o1abjiisq.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun openHome() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeDietFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
