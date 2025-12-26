package com.example.fulify.fragments

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulify.R
import com.example.fulify.adapter.WheelPickerAdapter
import com.example.fulify.utils.CenterSnapHelper

/**
 * Step 3: Age Selection Fragment
 */
class Step3AgeFragment : BaseOnboardingFragment() {

    private lateinit var btnContinue: Button
    private lateinit var btnBack: Button
    private lateinit var ivBack: ImageView
    private lateinit var recyclerWheelAge: RecyclerView
    private lateinit var wheelAdapter: WheelPickerAdapter

    private var selectedAge: Int = 27

    override fun getLayoutResId(): Int = R.layout.step3_age

    override fun setupViews(view: View) {
        btnContinue = view.findViewById(R.id.btnContinue)
        btnBack = view.findViewById(R.id.btnBack)
        ivBack = view.findViewById(R.id.ivBack)
        recyclerWheelAge = view.findViewById(R.id.recyclerWheelAge)

        // Create list of age values (18-100)
        val ageValues = (18..100).toList()

        // Load existing age if available
        viewModel.userProfile.value?.age?.let {
            selectedAge = it
        }

        // Find initial position
        val initialPosition = ageValues.indexOf(selectedAge).takeIf { it >= 0 } ?: 9 // Default to 27 (index 9)

        // Setup adapter
        wheelAdapter = WheelPickerAdapter(
            values = ageValues,
            unit = "years",
            selectedPosition = initialPosition
        ) { position, value ->
            selectedAge = value
            viewModel.setAge(selectedAge)
        }

        // Setup RecyclerView
        recyclerWheelAge.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = wheelAdapter

            // Add snap helper to snap items to center
            val snapHelper = CenterSnapHelper { position ->
                wheelAdapter.setSelectedPosition(position)
                selectedAge = ageValues[position]
                viewModel.setAge(selectedAge)
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
            viewModel.setAge(selectedAge)
            navigateNext()
        }

        btnBack.setOnClickListener {
            navigateBack()
        }

        ivBack.setOnClickListener {
            navigateBack()
        }
    }

    override fun validateStep(): Boolean {
        viewModel.setAge(selectedAge)
        return true
    }

    companion object {
        fun newInstance() = Step3AgeFragment()
    }
}