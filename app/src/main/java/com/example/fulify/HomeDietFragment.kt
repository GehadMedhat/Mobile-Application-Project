package com.example.fulify

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentHomeDietBinding

class HomeDietFragment : Fragment(R.layout.fragment_home_diet) {

    private var _binding: FragmentHomeDietBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeDietBinding.bind(view)

        setupHeader()
        setupCalories()
        setupMealsClicks()
        setupBottomNavigation()
    }

    // ---------------- HEADER ----------------
    private fun setupHeader() {
        binding.r7v3hvv6kpdi.text = "Alex"
        binding.rlhjz96h8gfh.text = "Sunday, December 28, 2025"
    }

    // ---------------- CALORIES ----------------
    private fun setupCalories() {
        val currentCalories = 1230
        val maxCalories = 2000

        binding.rkwwdn4qfhsb.text = currentCalories.toString()
        binding.rvjuzccl8zhf.text = maxCalories.toString()

        val remaining = maxCalories - currentCalories
        binding.rlxv41uusw2c.text = "$remaining cal remaining"
    }

    // ---------------- MEALS ----------------
    private fun setupMealsClicks() {

        binding.r0leip507xz1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealSchedulingFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.rmb90ugjikg.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealSchedulingFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.rmu3lkkhru8.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealSchedulingFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.rlayo637ow8.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MealSchedulingFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    // ---------------- BOTTOM NAV ----------------
    private fun setupBottomNavigation() {

        binding.rcpxyvmq9d2q.setOnClickListener {
            showToast("Home")
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

    // ---------------- UTIL ----------------
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
