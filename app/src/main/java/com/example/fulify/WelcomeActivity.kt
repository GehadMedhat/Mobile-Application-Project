package com.example.fulify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is already logged in and completed onboarding
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)
        val hasCompletedOnboarding = prefs.getBoolean("onboardingCompleted", false)

        if (isLoggedIn) {
            if (hasCompletedOnboarding) {
                // User already logged in and completed onboarding -> Go to Home
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                return
            } else {
                // User logged in but didn't complete onboarding -> Go to Onboarding
                val intent = Intent(this, OnboardingActivity::class.java)
                startActivity(intent)
                finish()
                return
            }
        }

        // First time user - show welcome screen
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Create Account button
        binding.rh64s5rbxntn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Already have an account button
        binding.rqtw80eh3x9.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Continue as guest (optional - you can implement later)
        binding.rtcilofvmf29.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}