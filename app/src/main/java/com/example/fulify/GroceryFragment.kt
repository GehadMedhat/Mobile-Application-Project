package com.example.fulify

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentGroceryBinding

class GroceryFragment : Fragment(R.layout.fragment_grocery) {

    private var _binding: FragmentGroceryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGroceryBinding.bind(view)

        setupTopButtons()
        setupBottomNavigation()
    }

    private fun setupTopButtons() {

        // Smart Pantry
        binding.rea7m8r11qal.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SmartPantryFragment())
                .addToBackStack(null)
                .commit()
        }

        // Grocery List
        binding.rea7m8r11ql.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GroceryListFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBottomNavigation() {

        //switch to home fragment
        binding.rcpxyvmq9d2q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeDietFragment())
                .commit()
        }

        binding.rwdjxk6o2zpe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealDeliveryFragment())
                .commit()
        }

        binding.rs54njblc63i.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipesFragment())
                .commit()
        }

        binding.r7zydsrg3nir.setOnClickListener {
        }

        binding.r4rs03s1n61q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EcoSustainability())
                .addToBackStack(null)
                .commit()
        }
    }

}
