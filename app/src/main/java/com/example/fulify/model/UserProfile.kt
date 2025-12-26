package com.example.fulify.model

/**
 * Data class to hold all user information collected during onboarding
 */
data class UserProfile(
    var name: String = "",
    var gender: Gender = Gender.NONE,
    var age: Int = 27,
    var height: Int = 170, // in cm
    var weight: Int = 82, // in kg
    var mealsPerDay: Int = 4
) {
    enum class Gender {
        NONE, MALE, FEMALE
    }

    /**
     * Validates if all required fields are filled
     */
    fun isComplete(): Boolean {
        return name.isNotBlank() && gender != Gender.NONE
    }

    /**
     * Validates individual step completion
     */
    fun isStepComplete(step: Int): Boolean {
        return when (step) {
            0 -> name.isNotBlank()
            1 -> gender != Gender.NONE
            2 -> age > 0
            3 -> height > 0
            4 -> weight > 0
            5 -> mealsPerDay > 0
            else -> false
        }
    }
}