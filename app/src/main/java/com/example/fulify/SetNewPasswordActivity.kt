package com.example.fulify

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivitySetNewPasswordBinding

class SetNewPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetNewPasswordBinding
    private var isNewPasswordVisible = false
    private var isConfirmPasswordVisible = false
    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra("EMAIL") ?: ""

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Back arrow (top)
        binding.rgbkz6k1gjao.setOnClickListener {
            finish()
        }

        // New Password visibility toggle
        binding.rti8b274681a.setOnClickListener {
            toggleNewPasswordVisibility()
        }

        // Confirm Password visibility toggle
        binding.rew5jukqcgn4.setOnClickListener {
            toggleConfirmPasswordVisibility()
        }

        // Reset Password button
        binding.r6yvcdehx5wg.setOnClickListener {
            validateAndResetPassword()
        }

        // Back to login (bottom)
        binding.rldumgthoik.setOnClickListener {
            navigateToLogin()
        }

        binding.rx5c7ew7ay8.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun toggleNewPasswordVisibility() {
        isNewPasswordVisible = !isNewPasswordVisible
        if (isNewPasswordVisible) {
            binding.rehhqp4u7ksb.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.rti8b274681a.setImageResource(R.drawable.eye_off)
        } else {
            binding.rehhqp4u7ksb.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.rti8b274681a.setImageResource(R.drawable.eye1)
        }
        binding.rehhqp4u7ksb.setSelection(binding.rehhqp4u7ksb.text?.length ?: 0)
    }

    private fun toggleConfirmPasswordVisibility() {
        isConfirmPasswordVisible = !isConfirmPasswordVisible
        if (isConfirmPasswordVisible) {
            binding.rqtdpu3cg5vd.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.rew5jukqcgn4.setImageResource(R.drawable.eye_off)
        } else {
            binding.rqtdpu3cg5vd.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.rew5jukqcgn4.setImageResource(R.drawable.eye1)
        }
        binding.rqtdpu3cg5vd.setSelection(binding.rqtdpu3cg5vd.text?.length ?: 0)
    }

    private fun validateAndResetPassword() {
        val newPassword = binding.rehhqp4u7ksb.text.toString()
        val confirmPassword = binding.rqtdpu3cg5vd.text.toString()

        // Validation
        when {
            newPassword.isEmpty() -> {
                binding.rehhqp4u7ksb.error = "Password is required"
                binding.rehhqp4u7ksb.requestFocus()
                return
            }
            newPassword.length < 6 -> {
                binding.rehhqp4u7ksb.error = "Password must be at least 6 characters"
                binding.rehhqp4u7ksb.requestFocus()
                return
            }
            confirmPassword != newPassword -> {
                binding.rqtdpu3cg5vd.error = "Passwords do not match"
                binding.rqtdpu3cg5vd.requestFocus()
                return
            }
        }

        // Update password (in real app, update via backend)
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        prefs.edit().apply {
            putString("password", newPassword)
            remove("verificationCode")
            remove("resetEmail")
            apply()
        }

        Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_SHORT).show()

        // Navigate to login
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}