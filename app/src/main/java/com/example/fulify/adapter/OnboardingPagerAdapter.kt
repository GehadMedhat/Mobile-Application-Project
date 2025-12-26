package com.example.fulify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fulify.fragments.*

/**
 * Adapter for ViewPager2 to manage onboarding fragments
 */
class OnboardingPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        Step1NameFragment.newInstance(),
        Step2GenderFragment.newInstance(),
        Step3AgeFragment.newInstance(),
        Step4HeightFragment.newInstance(),
        Step5WeightFragment.newInstance(),
        Step6MealsFragment.newInstance()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    /**
     * Get fragment at specific position
     */
    fun getFragmentAt(position: Int): BaseOnboardingFragment? {
        return if (position in fragments.indices) {
            fragments[position] as? BaseOnboardingFragment
        } else null
    }
}