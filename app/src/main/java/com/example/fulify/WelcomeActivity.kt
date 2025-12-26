package com.example.fulify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fulify.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            // TODO: Navigate to main app as guest
            // For now, just show a toast
            android.widget.Toast.makeText(
                this,
                "Guest mode - To be implemented",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }
}