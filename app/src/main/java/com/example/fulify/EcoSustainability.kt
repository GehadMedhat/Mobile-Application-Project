package com.example.fulify

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentEcoSustainabilityBinding
import kotlin.text.replace

class EcoSustainability : Fragment(R.layout.fragment_eco_sustainability) {

    private var _binding: FragmentEcoSustainabilityBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEcoSustainabilityBinding.bind(view)

        setupBackButton()
        setupBottomNavigation()
    }

    private fun setupBackButton() {
        // زرار السهم (رجوع)
        binding.rjc8v410euyc.setOnClickListener {
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

    private fun setupBottomNavigation() {

        binding.rcpxyvmq9d2q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeDietFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.rwdjxk6o2zpe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealDeliveryFragment())
                .addToBackStack(null)
                .commit()
        }

        // switch to recipes fragment
        binding.rs54njblc63i.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipesFragment())
                .addToBackStack(null)
                .commit()
        }


        binding.r7zydsrg3nir.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GroceryFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.r4rs03s1n61q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EcoSustainability())
                .addToBackStack(null)
                .commit()
        }
    }
}