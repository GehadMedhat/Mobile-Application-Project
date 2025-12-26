package com.example.fulify.fragments

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulify.MainActivity
import com.example.fulify.R
import com.example.fulify.adapter.WheelPickerAdapter
import com.example.fulify.utils.CenterSnapHelper

/**
 * Step 6: Meals Per Day Selection Fragment
 */
class Step6MealsFragment : BaseOnboardingFragment() {

    private lateinit var btnContinue: Button
    private lateinit var btnBack: Button
    private lateinit var ivBack: ImageView
    private lateinit var recyclerWheelMeals: RecyclerView
    private lateinit var wheelAdapter: WheelPickerAdapter

    private var selectedMeals: Int = 4

    override fun getLayoutResId(): Int = R.layout.step6_meals

    override fun setupViews(view: View) {
        btnContinue = view.findViewById(R.id.btnContinue)
        btnBack = view.findViewById(R.id.btnBack)
        ivBack = view.findViewById(R.id.ivBack)
        recyclerWheelMeals = view.findViewById(R.id.recyclerWheelMeals)

        // Create list of meals values (1-10)
        val mealsValues = (1..10).toList()

        // Load existing meals selection if available
        viewModel.userProfile.value?.mealsPerDay?.let {
            selectedMeals = it
        }

        // Find initial position
        val initialPosition = mealsValues.indexOf(selectedMeals).takeIf { it >= 0 } ?: 3 // Default to 4 (index 3)

        // Setup adapter
        wheelAdapter = WheelPickerAdapter(
            values = mealsValues,
            unit = "Meals",
            selectedPosition = initialPosition
        ) { position, value ->
            selectedMeals = value
            viewModel.setMealsPerDay(selectedMeals)
        }

        // Setup RecyclerView
        recyclerWheelMeals.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = wheelAdapter

            // Add snap helper to snap items to center
            val snapHelper = CenterSnapHelper { position ->
                wheelAdapter.setSelectedPosition(position)
                selectedMeals = mealsValues[position]
                viewModel.setMealsPerDay(selectedMeals)
            }
            snapHelper.attachToRecyclerView(this)

            // Scroll to initial position
            post {
                scrollToPosition(initialPosition)
            }
        }
    }

    override fun setupListeners(view: View) {
        btnContinue.setOnClickListener {
            viewModel.setMealsPerDay(selectedMeals)
            // This is the last step, so complete the onboarding
            completeOnboarding()
        }

        btnBack.setOnClickListener {
            navigateBack()
        }

        ivBack.setOnClickListener {
            navigateBack()
        }
    }

    private fun completeOnboarding() {
        val userProfile = viewModel.getUserProfile()

        // Save data to SharedPreferences
        saveUserProfile()

        // Show completion message
        Toast.makeText(
            requireContext(),
            "Onboarding Complete!\nWelcome, ${userProfile?.name}!",
            Toast.LENGTH_SHORT
        ).show()

        // Navigate to MainActivity with HomeDietFragment
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            // Add flag to clear the onboarding activity from back stack
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            // Optional: pass a flag to indicate we should show HomeDietFragment
            putExtra("SHOW_HOME_DIET", true)
        }
        startActivity(intent)
        requireActivity().finish()
    }

    private fun saveUserProfile() {
        val prefs = requireContext().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
        val editor = prefs.edit()

        // Mark onboarding as completed
        editor.putBoolean("onboardingCompleted", true)

        viewModel.userProfile.value?.let { profile ->
            profile.name?.let { editor.putString("userName", it) }
            profile.age?.let { editor.putInt("userAge", it) }
            profile.gender?.let { editor.putString("userGender", it.name) }
            profile.weight?.let { editor.putInt("userWeight", it) }
            profile.height?.let { editor.putInt("userHeight", it) }
            profile.mealsPerDay?.let { editor.putInt("userMeals", it) }
            profile.fitnessGoal?.let { editor.putString("userGoal", it) }
        }

        editor.apply()
    }

    override fun validateStep(): Boolean {
        viewModel.setMealsPerDay(selectedMeals)
        return true
    }

    companion object {
        fun newInstance() = Step6MealsFragment()
    }
}