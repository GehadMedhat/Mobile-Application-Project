package com.example.fulify

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentMealDeliveryBinding
import androidx.core.widget.addTextChangedListener

class MealDeliveryFragment : Fragment(R.layout.fragment_meal_delivery) {

    private var _binding: FragmentMealDeliveryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMealDeliveryBinding.bind(view)

        setupBackButton()
        setupFilters()
        setupOrderButtons()
        setupBottomNavigation()
    }

    // ---------------- BACK ----------------
    private fun setupBackButton() {
        binding.ri3vqmu5evzn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    // ---------------- FILTERS ----------------
    private fun setupFilters() {

        // All
        binding.rqzvr5mb7pqd.setOnClickListener {
            selectFilter("ALL")
        }

        // High Protein
        binding.r2vrzok9jg72.setOnClickListener {
            selectFilter("PROTEIN")
        }

        // Vegan
        binding.raac2c9iy5p.setOnClickListener {
            selectFilter("VEGAN")
        }

        // Low Carb
        binding.roji2pdnwgd.setOnClickListener {
            selectFilter("CARB")
        }
    }

    private fun selectFilter(type: String) {

        resetFiltersUI()

        when (type) {
            "ALL" -> {
                binding.rqzvr5mb7pqd.setBackgroundResource(R.drawable.orange_rectangle)
                showAllMeals()
            }

            "PROTEIN" -> {
                binding.r2vrzok9jg72.setBackgroundResource(R.drawable.orange_rectangle)
                showProteinMeals()
            }

            "VEGAN" -> {
                binding.raac2c9iy5p.setBackgroundResource(R.drawable.orange_rectangle)
                showVeganMeals()
            }

            "CARB" -> {
                binding.roji2pdnwgd.setBackgroundResource(R.drawable.orange_rectangle)
                showLowCarbMeals()
            }
        }
    }

    private fun resetFiltersUI() {
        binding.rqzvr5mb7pqd.setBackgroundResource(R.drawable.pink_rectangle)
        binding.r2vrzok9jg72.setBackgroundResource(R.drawable.pink_rectangle)
        binding.raac2c9iy5p.setBackgroundResource(R.drawable.pink_rectangle)
        binding.roji2pdnwgd.setBackgroundResource(R.drawable.pink_rectangle)
    }

    // ---------------- FILTER LOGIC ----------------
    private fun showAllMeals() {
        binding.rmwoehcoo6i.visibility = View.VISIBLE //protein
        binding.rn6dzhmln2h.visibility = View.VISIBLE //vegan
        binding.rm2ftrxn9lu.visibility = View.VISIBLE //carb
        binding.rr9hqvd8o38g.visibility = View.VISIBLE //protein
        binding.rq7z07mv4en8.visibility = View.VISIBLE //vegan
        binding.r5x5mu9grl1.visibility = View.VISIBLE //carb
    }

    private fun showProteinMeals() {
        binding.rmwoehcoo6i.visibility = View.VISIBLE //protein
        binding.rn6dzhmln2h.visibility = View.GONE //vegan
        binding.rm2ftrxn9lu.visibility = View.GONE //carb
        binding.rr9hqvd8o38g.visibility = View.VISIBLE //protein
        binding.rq7z07mv4en8.visibility = View.GONE //vegan
        binding.r5x5mu9grl1.visibility = View.GONE //carb
    }

    private fun showVeganMeals() {
        binding.rmwoehcoo6i.visibility = View.GONE //protein
        binding.rn6dzhmln2h.visibility = View.VISIBLE //vegan
        binding.rm2ftrxn9lu.visibility = View.GONE //carb
        binding.rr9hqvd8o38g.visibility = View.GONE //protein
        binding.rq7z07mv4en8.visibility = View.VISIBLE //vegan
        binding.r5x5mu9grl1.visibility = View.GONE //carb
    }

    private fun showLowCarbMeals() {
        binding.rmwoehcoo6i.visibility = View.GONE //protein
        binding.rn6dzhmln2h.visibility = View.GONE //vegan
        binding.rm2ftrxn9lu.visibility = View.VISIBLE //carb
        binding.rr9hqvd8o38g.visibility = View.GONE //protein
        binding.rq7z07mv4en8.visibility = View.GONE //vegan
        binding.r5x5mu9grl1.visibility = View.VISIBLE //carb
    }

    // ---------------- ORDER ----------------
    private fun setupOrderButtons() {

        // Order Now1
        binding.rapqnvrbmd8b.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Grilled Chicken Bowl ordered successfully 🍽️",
                Toast.LENGTH_SHORT
            ).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CloudKitchenFragment())
                .commit()
        }

        // Order Now2
        binding.r8tukjnakl5c.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Vegan Buddha Bowl ordered successfully 🍽️",
                Toast.LENGTH_SHORT
            ).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CloudKitchenFragment())
                .commit()
        }

        // Order Now3
        binding.rjf11lt6rd59.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Keto Salmon Plate ordered successfully 🍽️",
                Toast.LENGTH_SHORT
            ).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CloudKitchenFragment())
                    .commit()

        }

        // Order Now4
        binding.ra04wydnt4jv.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Protein Power Bowl ordered successfully 🍽️",
                Toast.LENGTH_SHORT
            ).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CloudKitchenFragment())
                .commit()
        }

        // Order Now5
        binding.rb082mbwy8t8.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Vegan Curry ordered successfully 🍽️",
                Toast.LENGTH_SHORT
            ).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CloudKitchenFragment())
                .commit()
        }

        // Order Now6
        binding.rafmm4v8e21.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Low Crab Steak ordered successfully 🍽️",
                Toast.LENGTH_SHORT
            ).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CloudKitchenFragment())
                .commit()
        }
    }

    private fun setupBottomNavigation() {

        //switch to home fragment
        binding.rcpxyvmq9d2q.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeDietFragment())
                .commit()
        }

        binding.rwdjxk6o2zpe.setOnClickListener {

        }

        //switch to recipes fragment
        binding.rs54njblc63i.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipesFragment())
                .commit()
        }

        //switch to recipes fragment
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
