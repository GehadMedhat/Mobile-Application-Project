package com.example.fulify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fulify.model.UserProfile

/**
 * ViewModel to manage user data across all onboarding steps
 */
class OnboardingViewModel : ViewModel() {

    // User profile data
    private val _userProfile = MutableLiveData(UserProfile())
    val userProfile: LiveData<UserProfile> = _userProfile

    // Current step tracking
    private val _currentStep = MutableLiveData(0)
    val currentStep: LiveData<Int> = _currentStep

    // Total number of steps
    val totalSteps = 6

    /**
     * Update user name
     */
    fun setName(name: String) {
        _userProfile.value = _userProfile.value?.copy(name = name)
    }

    /**
     * Update user gender
     */
    fun setGender(gender: UserProfile.Gender) {
        _userProfile.value = _userProfile.value?.copy(gender = gender)
    }

    /**
     * Update user age
     */
    fun setAge(age: Int) {
        _userProfile.value = _userProfile.value?.copy(age = age)
    }

    /**
     * Update user height
     */
    fun setHeight(height: Int) {
        _userProfile.value = _userProfile.value?.copy(height = height)
    }

    /**
     * Update user weight
     */
    fun setWeight(weight: Int) {
        _userProfile.value = _userProfile.value?.copy(weight = weight)
    }

    /**
     * Update meals per day
     */
    fun setMealsPerDay(meals: Int) {
        _userProfile.value = _userProfile.value?.copy(mealsPerDay = meals)
    }

    /**
     * Update current step
     */
    fun setCurrentStep(step: Int) {
        _currentStep.value = step
    }

    /**
     * Get current user profile
     */
    fun getUserProfile(): UserProfile? = _userProfile.value

    /**
     * Check if current step can proceed
     */
    fun canProceedFromStep(step: Int): Boolean {
        return _userProfile.value?.isStepComplete(step) ?: false
    }

    /**
     * Calculate progress percentage
     */
    fun getProgressPercentage(): Int {
        val currentStep = _currentStep.value ?: 0
        return ((currentStep + 1) * 100) / totalSteps
    }
}