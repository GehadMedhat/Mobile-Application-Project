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
 * Step 5: Weight Selection Fragment
 */
class Step5WeightFragment : BaseOnboardingFragment() {

    private lateinit var btnContinue: Button
    private lateinit var btnBack: Button
    private lateinit var ivBack: ImageView
    private lateinit var recyclerWheelWeight: RecyclerView
    private lateinit var wheelAdapter: WheelPickerAdapter

    private var selectedWeight: Int = 82

    override fun getLayoutResId(): Int = R.layout.step5_weight

    override fun setupViews(view: View) {
        btnContinue = view.findViewById(R.id.btnContinue)
        btnBack = view.findViewById(R.id.btnBack)
        ivBack = view.findViewById(R.id.ivBack)
        recyclerWheelWeight = view.findViewById(R.id.recyclerWheelWeight)

        // Create list of weight values (30-200 kg)
        val weightValues = (30..200).toList()

        // Load existing weight if available
        viewModel.userProfile.value?.weight?.let {
            selectedWeight = it
        }

        // Find initial position
        val initialPosition = weightValues.indexOf(selectedWeight).takeIf { it >= 0 } ?: 52 // Default to 82 (index 52)

        // Setup adapter
        wheelAdapter = WheelPickerAdapter(
            values = weightValues,
            unit = "kg",
            selectedPosition = initialPosition
        ) { position, value ->
            selectedWeight = value
            viewModel.setWeight(selectedWeight)
        }

        // Setup RecyclerView
        recyclerWheelWeight.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = wheelAdapter

            // Add snap helper to snap items to center
            val snapHelper = CenterSnapHelper { position ->
                wheelAdapter.setSelectedPosition(position)
                selectedWeight = weightValues[position]
                viewModel.setWeight(selectedWeight)
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
            viewModel.setWeight(selectedWeight)
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
        viewModel.setWeight(selectedWeight)
        return true
    }

    companion object {
        fun newInstance() = Step5WeightFragment()
    }
}