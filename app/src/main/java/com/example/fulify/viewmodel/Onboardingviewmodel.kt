package com.example.fulify.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class UserProfile(
    var name: String? = null,
    var age: Int? = null,
    var gender: Gender? = null,
    var weight: Int? = null,        // Changed from Double to Int
    var height: Int? = null,        // Changed from Double to Int
    var mealsPerDay: Int? = null,   // Added mealsPerDay
    var fitnessGoal: String? = null
) {
    enum class Gender {
        MALE,
        FEMALE,
        NONE
    }
}

class OnboardingViewModel : ViewModel() {

    val userProfile = MutableLiveData<UserProfile>(UserProfile())

    fun setName(name: String) {
        userProfile.value = userProfile.value?.apply { this.name = name }
    }

    fun setAge(age: Int) {
        userProfile.value = userProfile.value?.apply { this.age = age }
    }

    fun setGender(gender: UserProfile.Gender) {
        userProfile.value = userProfile.value?.apply { this.gender = gender }
    }

    fun setWeight(weight: Int) {    // Changed from Double to Int
        userProfile.value = userProfile.value?.apply { this.weight = weight }
    }

    fun setHeight(height: Int) {    // Changed from Double to Int
        userProfile.value = userProfile.value?.apply { this.height = height }
    }

    fun setMealsPerDay(meals: Int) {  // Added this method
        userProfile.value = userProfile.value?.apply { this.mealsPerDay = meals }
    }

    fun setFitnessGoal(goal: String) {
        userProfile.value = userProfile.value?.apply { this.fitnessGoal = goal }
    }

    fun getUserProfile(): UserProfile? {
        return userProfile.value
    }
}