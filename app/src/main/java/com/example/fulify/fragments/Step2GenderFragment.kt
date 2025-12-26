package com.example.fulify.fragments

import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.fulify.R
import com.example.fulify.viewmodel.UserProfile  // Changed from model to viewmodel

/**
 * Step 2: Gender Selection Fragment
 */
class Step2GenderFragment : BaseOnboardingFragment() {

    private lateinit var flMale: FrameLayout
    private lateinit var flFemale: FrameLayout
    private lateinit var btnContinue: Button
    private lateinit var btnBack: Button
    private lateinit var ivBack: ImageView

    private var selectedGender: UserProfile.Gender = UserProfile.Gender.NONE

    override fun getLayoutResId(): Int = R.layout.step2_gender

    override fun setupViews(view: View) {
        flMale = view.findViewById(R.id.flMale)
        flFemale = view.findViewById(R.id.flFemale)
        btnContinue = view.findViewById(R.id.btnContinue)
        btnBack = view.findViewById(R.id.btnBack)
        ivBack = view.findViewById(R.id.ivBack)

        // Load existing selection if available
        viewModel.userProfile.value?.gender?.let {
            selectedGender = it
            updateGenderSelection()
        }
    }

    override fun setupListeners(view: View) {
        flMale.setOnClickListener {
            selectedGender = UserProfile.Gender.MALE
            viewModel.setGender(selectedGender)
            updateGenderSelection()
        }

        flFemale.setOnClickListener {
            selectedGender = UserProfile.Gender.FEMALE
            viewModel.setGender(selectedGender)
            updateGenderSelection()
        }

        btnContinue.setOnClickListener {
            if (selectedGender != UserProfile.Gender.NONE) {
                navigateNext()
            } else {
                Toast.makeText(requireContext(), "Please select your gender", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            navigateBack()
        }

        ivBack.setOnClickListener {
            navigateBack()
        }
    }

    private fun updateGenderSelection() {
        // Get ImageViews from the FrameLayouts
        val maleIcon = flMale.findViewById<ImageView>(R.id.ivMaleIcon)
        val femaleIcon = flFemale.findViewById<ImageView>(R.id.ivFemaleIcon)

        when (selectedGender) {
            UserProfile.Gender.MALE -> {
                flMale.setBackgroundResource(R.drawable.bg_gender_male_selected)
                flFemale.setBackgroundResource(R.drawable.bg_gender_female_unselected)
                maleIcon?.setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.black),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                femaleIcon?.setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
            UserProfile.Gender.FEMALE -> {
                flMale.setBackgroundResource(R.drawable.bg_gender_male_unselected)
                flFemale.setBackgroundResource(R.drawable.bg_gender_female_selected)
                maleIcon?.setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.black),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                femaleIcon?.setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
            UserProfile.Gender.NONE -> {
                flMale.setBackgroundResource(R.drawable.bg_gender_male_unselected)
                flFemale.setBackgroundResource(R.drawable.bg_gender_female_unselected)
                maleIcon?.setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.black),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                femaleIcon?.setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    override fun validateStep(): Boolean {
        if (selectedGender == UserProfile.Gender.NONE) {
            Toast.makeText(requireContext(), "Please select your gender", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    companion object {
        fun newInstance() = Step2GenderFragment()
    }
}