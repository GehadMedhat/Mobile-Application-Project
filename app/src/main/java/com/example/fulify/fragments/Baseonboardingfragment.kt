package com.example.fulify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fulify.viewmodel.OnboardingViewModel

/**
 * Base fragment for all onboarding steps
 * Provides common functionality like navigation and ViewModel access
 */
abstract class BaseOnboardingFragment : Fragment() {

    // Shared ViewModel across all fragments
    protected val viewModel: OnboardingViewModel by activityViewModels()

    // Callback for navigation
    var onNavigateNext: (() -> Unit)? = null
    var onNavigateBack: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners(view)
        observeViewModel()
    }

    /**
     * Return the layout resource ID for this fragment
     */
    abstract fun getLayoutResId(): Int

    /**
     * Initialize views
     */
    abstract fun setupViews(view: View)

    /**
     * Setup click listeners and other event handlers
     */
    abstract fun setupListeners(view: View)

    /**
     * Observe ViewModel LiveData
     */
    open fun observeViewModel() {
        // Override in child fragments if needed
    }

    /**
     * Validate current step before proceeding
     */
    open fun validateStep(): Boolean = true

    /**
     * Navigate to next step
     */
    protected fun navigateNext() {
        if (validateStep()) {
            onNavigateNext?.invoke()
        }
    }

    /**
     * Navigate to previous step
     */
    protected fun navigateBack() {
        onNavigateBack?.invoke()
    }
}