package com.example.fulify

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivityVerificationBinding

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding
    private var email: String = ""
    private var fromForgotPassword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra("EMAIL") ?: ""
        fromForgotPassword = intent.getBooleanExtra("FROM_FORGOT_PASSWORD", false)

        // Update email text
        binding.rozgsyo8bbg9.text = "We sent a code to\n$email"

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Back arrow
        binding.r0rvttdbbaec.setOnClickListener {
            finish()
        }

        // Continue button
        binding.rngayjooszt.setOnClickListener {
            verifyCode()
        }

        // Send Again (resend code)
        binding.rx23q80yl3em.setOnClickListener {
            resendCode()
        }

        // Back to login (bottom)
        binding.rgo75io1sqvj.setOnClickListener {
            finish()
        }

        binding.rfkhgkclngl.setOnClickListener {
            finish()
        }
    }

    private fun verifyCode() {
        // In real app, verify the entered code
        // For demo, we'll accept any input

        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        Toast.makeText(this, "Verification successful!", Toast.LENGTH_SHORT).show()

        if (fromForgotPassword) {
            // Navigate to Set New Password
            val intent = Intent(this, SetNewPasswordActivity::class.java)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
            finish()
        } else {
            // After signup verification -> Mark as logged in and go to onboarding
            prefs.edit().putBoolean("isLoggedIn", true).apply()

            Toast.makeText(this, "Account verified! Let's set up your profile", Toast.LENGTH_SHORT).show()

            // Navigate to Onboarding (Step1NameFragment)
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun resendCode() {
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val newCode = (1000..9999).random().toString()
        prefs.edit().putString("verificationCode", newCode).apply()

        Toast.makeText(this, "New code sent to $email", Toast.LENGTH_SHORT).show()
    }
}