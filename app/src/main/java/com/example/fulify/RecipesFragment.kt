package com.example.fulify

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fulify.databinding.FragmentRecipesBinding
import androidx.core.widget.addTextChangedListener
import android.content.Intent


class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecipesBinding.bind(view)

        setupSearch()
        setupRecipeClicks()
        setupBottomNavigation()
    }

    // ---------------- SEARCH ----------------
    private fun setupSearch() {
        binding.rdzbu5yq70u5.addTextChangedListener { text ->
            val query = text.toString().trim().lowercase()
            filterRecipes(query)
        }
    }

    private fun filterRecipes(query: String) {

        // recipes names
        val recipe1 = "grilled chicken vegetables"
        val recipe2 = "quinoa buddha bowl"
        val recipe3 = "baked salmon asparagus"
        val recipe4 = "Turkey & Sweet Potato Skillet"

        // if search is empty
        if (query.isEmpty()) {
            binding.r8x6br0kiu52.visibility = View.VISIBLE
            binding.rkp045rx2gtm.visibility = View.VISIBLE
            binding.rel0h2dj3m6r.visibility = View.VISIBLE
            return
        }

        // Recipe 1
        binding.r8x6br0kiu52.visibility =
            if (recipe1.contains(query)) View.VISIBLE else View.GONE

        // Recipe 2
        binding.rkp045rx2gtm.visibility =
            if (recipe2.contains(query)) View.VISIBLE else View.GONE

        // Recipe 3
        binding.rel0h2dj3m6r.visibility =
            if (recipe3.contains(query)) View.VISIBLE else View.GONE

        // Recipe 4
        binding.rtm33ydqn62a.visibility =
            if (recipe4.contains(query)) View.VISIBLE else View.GONE
    }

    // ---------------- RECIPES ----------------
    private fun setupRecipeClicks() {

        // Recipe 1
        binding.r8x6br0kiu52.setOnClickListener {
            openMealDetails(
                title = "Grilled Chicken with Vegetables",
                image = R.drawable.meal,
                time = "25 min",
                level = "Easy",
                video = "https://youtu.be/U8l37HAGhDA"
            )
        }


        // Recipe 2
        binding.rkp045rx2gtm.setOnClickListener {
            openMealDetails(
                title = "Quinoa Buddha Bowl",
                image = R.drawable.meal2,
                time = "20 min",
                level = "Medium",
                video = "https://youtu.be/uK4LibynqSk?si=coBvUN5z2TlVwBSW"
            )
        }



        // Recipe 3
        binding.rel0h2dj3m6r.setOnClickListener {
            openMealDetails(
                title = "Baked Salmon with Asparagus",
                image = R.drawable.meal3,
                time = "30 min",
                level = "Hard",
                video = "https://youtu.be/Kdq3khk_8n0?si=gqkrCByb2c64b-i_"
            )
        }

        // Recipe 4
        binding.rtm33ydqn62a.setOnClickListener {
            openMealDetails(
                title = "Turkey & Sweet Potato Skillet",
                image = R.drawable.meal4,
                time = "35 min",
                level = "Medium",
                video = "https://youtu.be/W5ejVjg7LPI?si=ysJpcZMFyHSUExNg"
            )
        }
    }

    private fun openMealDetails(
        title: String,
        image: Int,
        time: String,
        level: String,
        video: String
    ) {
        val fragment = MealDetailsFragment().apply {
            arguments = Bundle().apply {
                putString("TITLE", title)
                putInt("IMAGE", image)
                putString("TIME", time)
                putString("LEVEL", level)
                putString("VIDEO_URL", video)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


    // ---------------- BOTTOM NAV ----------------
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
                .commit()        }

        binding.rs54njblc63i.setOnClickListener {

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
