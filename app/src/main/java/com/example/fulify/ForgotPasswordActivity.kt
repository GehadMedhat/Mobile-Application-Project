package com.example.fulify

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Back arrow (top)
        binding.rca589uz20jt.setOnClickListener {
            finish()
        }

        // Back to login (bottom)
        binding.r005b7s0ekfsit.setOnClickListener {
            finish()
        }

        binding.r7q5ezuyvx7k.setOnClickListener {
            finish()
        }

        // Send Code button
        binding.rtnp02mf2r4g.setOnClickListener {
            validateAndSendCode()
        }
    }

    private fun validateAndSendCode() {
        val email = binding.r7kagbk3dpnj.text.toString().trim()

        // Validation
        when {
            email.isEmpty() -> {
                binding.r7kagbk3dpnj.error = "Email is required"
                binding.r7kagbk3dpnj.requestFocus()
                return
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.r7kagbk3dpnj.error = "Enter a valid email"
                binding.r7kagbk3dpnj.requestFocus()
                return
            }
        }

        // Check if email exists (in real app, check with backend)
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val savedEmail = prefs.getString("email", "")

        if (email == savedEmail) {
            // Generate and "send" verification code (in real app, send via email/SMS)
            val verificationCode = generateVerificationCode()

            // Save code temporarily
            prefs.edit().apply {
                putString("verificationCode", verificationCode)
                putString("resetEmail", email)
                apply()
            }

            Toast.makeText(this, "Verification code sent to $email", Toast.LENGTH_SHORT).show()

            // Navigate to verification
            val intent = Intent(this, VerificationActivity::class.java)
            intent.putExtra("EMAIL", email)
            intent.putExtra("FROM_FORGOT_PASSWORD", true)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateVerificationCode(): String {
        // Generate random 4-digit code
        return (1000..9999).random().toString()
    }
}