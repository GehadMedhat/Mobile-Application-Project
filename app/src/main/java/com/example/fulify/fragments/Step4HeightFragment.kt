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
 * Step 4: Height Selection Fragment
 */
class Step4HeightFragment : BaseOnboardingFragment() {

    private lateinit var btnContinue: Button
    private lateinit var btnBack: Button
    private lateinit var ivBack: ImageView
    private lateinit var recyclerWheelHeight: RecyclerView
    private lateinit var wheelAdapter: WheelPickerAdapter

    private var selectedHeight: Int = 170

    override fun getLayoutResId(): Int = R.layout.step4_height

    override fun setupViews(view: View) {
        btnContinue = view.findViewById(R.id.btnContinue)
        btnBack = view.findViewById(R.id.btnBack)
        ivBack = view.findViewById(R.id.ivBack)
        recyclerWheelHeight = view.findViewById(R.id.recyclerWheelHeight)

        // Create list of height values (100-250 cm)
        val heightValues = (100..250).toList()

        // Load existing height if available
        viewModel.userProfile.value?.height?.let {
            selectedHeight = it
        }

        // Find initial position
        val initialPosition = heightValues.indexOf(selectedHeight).takeIf { it >= 0 } ?: 70 // Default to 170 (index 70)

        // Setup adapter
        wheelAdapter = WheelPickerAdapter(
            values = heightValues,
            unit = "cm",
            selectedPosition = initialPosition
        ) { position, value ->
            selectedHeight = value
            viewModel.setHeight(selectedHeight)
        }

        // Setup RecyclerView
        recyclerWheelHeight.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = wheelAdapter

            // Add snap helper to snap items to center
            val snapHelper = CenterSnapHelper { position ->
                wheelAdapter.setSelectedPosition(position)
                selectedHeight = heightValues[position]
                viewModel.setHeight(selectedHeight)
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
            viewModel.setHeight(selectedHeight)
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
        viewModel.setHeight(selectedHeight)
        return true
    }

    companion object {
        fun newInstance() = Step4HeightFragment()
    }
}