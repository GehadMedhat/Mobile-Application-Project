package com.example.fulify.fragments


import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.fulify.R

/**
 * Step 1: Name Input Fragment
 */
class Step1NameFragment : BaseOnboardingFragment() {

    private lateinit var etName: EditText
    private lateinit var btnContinue: Button
    private lateinit var btnBack: Button
    private lateinit var ivBack: ImageView

    override fun getLayoutResId(): Int = R.layout.step1_name

    override fun setupViews(view: View) {
        etName = view.findViewById(R.id.etName)
        btnContinue = view.findViewById(R.id.btnContinue)
        btnBack = view.findViewById(R.id.btnBack)
        ivBack = view.findViewById(R.id.ivBack)

        // Load existing name if available
        viewModel.userProfile.value?.name?.let {
            if (it.isNotBlank()) {
                etName.setText(it)
            }
        }
    }

    override fun setupListeners(view: View) {
        btnContinue.setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isNotBlank()) {
                viewModel.setName(name)
                navigateNext()
            } else {
                Toast.makeText(requireContext(), "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            navigateBack()
        }

        ivBack.setOnClickListener {
            navigateBack()
        }
    }

    override fun validateStep(): Boolean {
        val name = etName.text.toString().trim()
        if (name.isBlank()) {
            Toast.makeText(requireContext(), "Please enter your name", Toast.LENGTH_SHORT).show()
            return false
        }
        viewModel.setName(name)
        return true
    }

    companion object {
        fun newInstance() = Step1NameFragment()
    }
}