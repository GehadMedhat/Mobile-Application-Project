package com.example.fulify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fulify.fragments.*
import com.example.fulify.viewmodel.OnboardingViewModel

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewModel: OnboardingViewModel
    private val fragments = mutableListOf<BaseOnboardingFragment>()
    private var currentStep = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]

        // Add all your step fragments
        initializeFragments()

        // Show first fragment
        if (savedInstanceState == null) {
            showFragment(0)
        }
    }

    private fun initializeFragments() {
        // Add Step1NameFragment
        fragments.add(Step1NameFragment.newInstance())

        // TODO: Add other steps when you create them:
         fragments.add(Step2GenderFragment.newInstance())
         fragments.add(Step3AgeFragment.newInstance())
         fragments.add(Step4HeightFragment.newInstance())
         fragments.add(Step5WeightFragment.newInstance())
         fragments.add(Step6MealsFragment.newInstance())
    }

    private fun showFragment(position: Int) {
        if (position < 0 || position >= fragments.size) return

        currentStep = position
        val fragment = fragments[position]

        // Set navigation callbacks
        fragment.onNavigateNext = { navigateToNext() }
        fragment.onNavigateBack = { navigateToPrevious() }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun navigateToNext() {
        if (currentStep < fragments.size - 1) {
            showFragment(currentStep + 1)
        } else {
            // Last step completed - save and navigate to home
            completeOnboarding()
        }
    }

    fun navigateToPrevious() {
        if (currentStep > 0) {
            showFragment(currentStep - 1)
        } else {
            // If on first step, go back to welcome
            finish()
        }
    }

    private fun completeOnboarding() {
        // Save that onboarding is complete
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        prefs.edit().putBoolean("onboardingCompleted", true).apply()

        // Save user profile data
        saveUserProfile()

        // Navigate to MainActivity (HomeDietFragment)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveUserProfile() {
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = prefs.edit()

        viewModel.userProfile.value?.let { profile ->
            profile.name?.let { editor.putString("userName", it) }
            profile.age?.let { editor.putInt("userAge", it) }
            profile.gender?.let { editor.putString("userGender", it.name) }  // Save enum as string
            profile.weight?.let { editor.putFloat("userWeight", it.toFloat()) }
            profile.height?.let { editor.putFloat("userHeight", it.toFloat()) }
            profile.fitnessGoal?.let { editor.putString("userGoal", it) }
        }

        editor.apply()
    }

    override fun onBackPressed() {
        navigateToPrevious()
    }
}