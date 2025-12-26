package com.example.fulify

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.fulify.adapter.OnboardingPagerAdapter
import com.example.fulify.fragments.BaseOnboardingFragment
import com.example.fulify.viewmodel.OnboardingViewModel

/**
 * Main Activity that hosts the onboarding flow using ViewPager2
 */
class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: OnboardingPagerAdapter
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setupViewPager()
        observeViewModel()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewPager.currentItem > 0) {
                    viewPager.currentItem = viewPager.currentItem - 1
                } else {
                    finish()
                }
            }
        })
    }

    private fun setupViewPager() {
        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = OnboardingPagerAdapter(this)

        viewPager.adapter = pagerAdapter
        viewPager.isUserInputEnabled = false // Disable swipe navigation

        // Register callback for page changes
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentStep(position)
            }
        })

        // Setup navigation callbacks for fragments
        setupFragmentCallbacks()
    }

    private fun setupFragmentCallbacks() {
        // We need to set callbacks after fragments are created
        // This is handled by attaching callbacks when fragments become visible
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // Get current fragment and set navigation callbacks
                val fragment = supportFragmentManager.findFragmentByTag("f$position")
                if (fragment is BaseOnboardingFragment) {
                    fragment.onNavigateNext = { navigateToNext() }
                    fragment.onNavigateBack = { navigateToPrevious() }
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel.currentStep.observe(this) { step ->
            // Update UI based on current step if needed
            // For example, you could update a custom progress bar
        }

        viewModel.userProfile.observe(this) { profile ->
            // React to profile changes if needed
        }
    }

    private fun navigateToNext() {
        val currentItem = viewPager.currentItem
        if (currentItem < pagerAdapter.itemCount - 1) {
            viewPager.setCurrentItem(currentItem + 1, true)
        }
    }

    private fun navigateToPrevious() {
        val currentItem = viewPager.currentItem
        if (currentItem > 0) {
            viewPager.setCurrentItem(currentItem - 1, true)
        } else {
            // First page - handle back button (exit or show dialog)
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
